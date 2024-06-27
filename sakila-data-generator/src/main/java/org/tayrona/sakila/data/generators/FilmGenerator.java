package org.tayrona.sakila.data.generators;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.jooq.AggregateFunction;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;
import org.tayrona.sakila.data.Tables;
import org.tayrona.sakila.data.enums.MpaaRating;
import org.tayrona.sakila.data.tables.Language;
import org.tayrona.sakila.data.tables.records.*;
import org.tayrona.sakila.data.utils.ResourceReader;
import org.tayrona.sakila.data.utils.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class FilmGenerator {
    private final DSLContext dslContext;

    private final Faker faker;

    public FilmGenerator(DSLContext dslContext, Faker faker) {
        this.dslContext = dslContext;
        this.faker = faker;
    }

    public long populateActors() throws IOException {
        log.info("Starting Actor population");
        ResourceReader resourceReader = new ResourceReader("actor_names.text.gz");
        ResourceActorGenerator resourceActorGenerator = new ResourceActorGenerator();
        resourceReader.processLines(resourceActorGenerator::persistOneActor);
        log.info("Done. {} Actors added out of {} names checked",
                resourceActorGenerator.getNewRows(), resourceActorGenerator.getTotalRows());
        return resourceActorGenerator.getNewRows();
    }

    public long populateFilms() throws IOException {
        log.info("Starting Film population");
        ResourceReader resourceReader = new ResourceReader("movie_titles.text.gz");
        ResourceFilmGenerator resourceFilmGenerator = new ResourceFilmGenerator();
        resourceReader.processLines(resourceFilmGenerator::persistOneFilm);
        log.info("Done. {} Films added out of {} Titles checked",
                resourceFilmGenerator.getNewRows(), resourceFilmGenerator.getTotalRows());
        return resourceFilmGenerator.getNewRows();
    }

    public Result<FilmRecord> getFilmByTitle(String title) {
        return dslContext.selectFrom(Tables.FILM).where(Tables.FILM.TITLE.eq(title)).fetch();
    }

    public Result<ActorRecord> getActorByNames(String firsName, String lastName) {
        return dslContext.selectFrom(Tables.ACTOR)
                .where(Tables.ACTOR.FIRST_NAME.eq(firsName)
                        .and(Tables.ACTOR.LAST_NAME.eq(lastName)))
                .fetch();
    }

    public Result<FilmRecord> getFilmByBarcode(String barcode) {
        return dslContext.selectFrom(Tables.FILM).where(Tables.FILM.BARCODE.eq(barcode)).fetch();
    }

    public boolean filmByTitleExists(final String title) {
        Result<Record1<Long>> result = dslContext.select(Tables.FILM.FILM_ID)
                .from(Tables.FILM)
                .where(Tables.FILM.TITLE.eq(title))
                .limit(1)
                .fetch();
        return result.isNotEmpty();
    }

    public boolean filmByBarcodeExists(final String barcode) {
        Result<Record1<Long>> result = dslContext.select(Tables.FILM.FILM_ID)
                .from(Tables.FILM)
                .where(Tables.FILM.BARCODE.eq(barcode))
                .limit(1)
                .fetch();
        return result.isNotEmpty();
    }

    public boolean actorByNamesExists(String firsName, String lastName) {
        Result<Record1<Long>> result = dslContext.select(Tables.ACTOR.ACTOR_ID).from(Tables.ACTOR)
                .where(Tables.ACTOR.FIRST_NAME.eq(firsName)
                        .and(Tables.ACTOR.LAST_NAME.eq(lastName)))
                .limit(1)
                .fetch();
        return result.isNotEmpty();
    }

    public Result<LanguageRecord> existingLanguages() {
        return dslContext.selectFrom(Language.LANGUAGE).fetch();
    }

    public ActorRecord generateOneActor(String firsName, String lastName) {
        ActorRecord actorRecord = dslContext.newRecord(Tables.ACTOR);
        actorRecord.setFirstName(firsName);
        actorRecord.setLastName(lastName);
        return actorRecord;
    }

    public FilmRecord generateOneFilm(String filmTitle, String filmDescription, Result<LanguageRecord> existingLanguages) {
        if (null == filmDescription) {
            filmDescription = faker.lorem().paragraph(faker.random().nextInt(1, 3));
        }
        FilmRecord filmRecord = dslContext.newRecord(Tables.FILM);
        filmRecord.setTitle(filmTitle);
        filmRecord.setDescription(filmDescription);
        filmRecord.setLanguageId(randomLanguage(existingLanguages).getLanguageId());
        filmRecord.setOriginalLanguageId(randomLanguage(existingLanguages).getLanguageId());
        filmRecord.setBarcode(generateUniqueBarcode(12));
        filmRecord.setReleaseYear(generateReleaseYear());
        filmRecord.setRentalDuration(faker.random().nextInt(2, 10).byteValue());
        filmRecord.setRentalRate(BigDecimal.valueOf(faker.random().nextDouble() * 10 + 5));
        filmRecord.setLength((short) (faker.random().nextInt(45, 180).intValue()));
        filmRecord.setReplacementCost(BigDecimal.valueOf(filmRecord.getRentalRate().doubleValue() * 9.0));
        filmRecord.setRating(MpaaRating.values()[faker.random().nextInt(0, 4)]);
        return filmRecord;
    }

    public LanguageRecord randomLanguage(List<LanguageRecord> existingLanguages) {
        int languageIndex = faker.random().nextInt(0, existingLanguages.size() - 1);
        return existingLanguages.get(languageIndex);
    }

    public String generateUniqueBarcode(int width) {
        if (width < 1) {
            throw new IllegalArgumentException("Barcode width must be positive: " + width);
        }
        int retry = 0;
        long upperLimit = (long) Math.ceil(Math.pow(10, width));
        String barcode = Long.toString(RandomUtils.nextLong(1, upperLimit));
        while (filmByBarcodeExists(barcode) && (retry < 5)) {
            barcode = Long.toString(RandomUtils.nextLong(1, upperLimit));
            retry += 1;
        }
        return barcode;
    }

    public short generateReleaseYear() {
        int year = LocalDate.now().get(ChronoField.YEAR);
        return (short) (year - faker.random().nextInt(1, 15));
    }

    private class ResourceActorGenerator {
        private long newRows;
        private long totalRows;

        public ResourceActorGenerator() {
            this.newRows = 0;
            this.totalRows = 0;
        }

        public void persistOneActor(final String textLine) {
            totalRows += 1;
            if (StringUtils.isAcceptableFullName(textLine)) {
                String[] fragments = StringUtils.split(textLine, ' ');
                if ((fragments.length > 2) || (fragments.length == textLine.length())) {
                    return;
                }
                if (fragments.length == 2) {
                    String firsName = fragments[0];
                    String lastName = fragments[1];
                    if (!actorByNamesExists(firsName, lastName)) {
                        ActorRecord actorRecord = generateOneActor(firsName, lastName);
                        actorRecord.store();
                        newRows += 1;
                    }
                }
            }
            if ((totalRows % 10000) == 0) {
                log.info("{} Actors added out of {} Names checked", newRows, totalRows);
            }
        }

        public long getNewRows() {
            return newRows;
        }

        public long getTotalRows() {
            return totalRows;
        }
    }

    private class ResourceFilmGenerator {
        private long newRows;
        private long totalRows;
        private final List<Long> categoryIds;
        private final long maxActorId;
        private final Result<LanguageRecord> existingLanguages;

        private ResourceFilmGenerator() {
            this.newRows = 0;
            this.totalRows = 0;
            this.categoryIds = setOfCategoryIds();
            if (this.categoryIds.isEmpty()) {
                throw new IllegalStateException("Categories table is empty");
            }
            this.maxActorId = getMaxActorId();
            this.existingLanguages = existingLanguages();
            if (this.existingLanguages.isEmpty()) {
                throw new IllegalStateException("Language table is empty");
            }
        }

        public void persistOneFilm(final String textLine) {
            totalRows += 1;
            String[] fragments = StringUtils.split(textLine, '|');
            if ((fragments.length > 2) || (fragments.length == textLine.length())) {
                return;
            }
            if (fragments.length > 0) {
                String title = fragments[0];
                if (StringUtils.isAcceptableFilmTitle(title)) {
                    String description = null;
                    if (fragments.length > 1) {
                        description = fragments[1];
                    }
                    if (!filmByTitleExists(title)) {
                        FilmRecord filmRecord = generateOneFilm(title, description, existingLanguages);
                        filmRecord.store();
                        refreshActorsForFilm(filmRecord, maxActorId);
                        refreshCategoriesForFilm(filmRecord.getFilmId(), categoryIds);
                        newRows += 1;
                    }
                }
            }
            if ((totalRows % 10000) == 0) {
                log.info("{} Films added out of {} Titles checked", newRows, totalRows);
            }
        }

        public long getNewRows() {
            return newRows;
        }

        public long getTotalRows() {
            return totalRows;
        }
    }

    public long getMaxFilmId() {
        Result<Record1<Long>> queryResult = dslContext
                .select(DSL.max(Tables.FILM.FILM_ID))
                .from(Tables.FILM)
                .fetch();
        if (queryResult.isNotEmpty()) {
            return queryResult.stream().findAny().orElseThrow().value1();
        }
        return 0L;
    }

    public boolean filmExistsById(long filmId) {
        return dslContext.
                select(Tables.FILM.FILM_ID)
                .from(Tables.FILM)
                .where(Tables.FILM.FILM_ID.eq(filmId))
                .fetch()
                .isNotEmpty();
    }

    public boolean actorExistsById(long actorId) {
        return dslContext.
                select(Tables.ACTOR.ACTOR_ID)
                .from(Tables.ACTOR)
                .where(Tables.ACTOR.ACTOR_ID.eq(actorId))
                .fetch()
                .isNotEmpty();
    }

    public long getMaxActorId() {
        Result<Record1<Long>> queryResult = dslContext
                .select(DSL.max(Tables.ACTOR.ACTOR_ID))
                .from(Tables.ACTOR)
                .fetch();
        if (queryResult.isNotEmpty()) {
            return queryResult.stream().findAny().orElseThrow().value1();
        }
        return 0L;
    }

    public boolean filmByIdHasCategories(long filmId) {
        return dslContext
                .selectFrom(Tables.FILM_CATEGORY)
                .where(Tables.FILM_CATEGORY.FILM_ID.eq(filmId))
                .limit(1)
                .fetch()
                .isNotEmpty();
    }

    public boolean filmByIdHasActors(long filmId) {
        return dslContext
                .selectFrom(Tables.FILM_ACTOR)
                .where(Tables.FILM_ACTOR.FILM_ID.eq(filmId))
                .limit(1)
                .fetch()
                .isNotEmpty();
    }

    public void refreshCategoriesForFilm(long filmId, List<Long> categoryIds) {
        if (filmExistsById(filmId) && !filmByIdHasCategories(filmId)) {
            int howManyCategories = faker.random().nextInt(1, Math.min(5, categoryIds.size()));
            Set<Long> categoriesForFilm = new HashSet<>(howManyCategories);
            while (categoriesForFilm.size() < howManyCategories) {
                int whichCategory = faker.random().nextInt(0, categoryIds.size() - 1);
                categoriesForFilm.add(categoryIds.get(whichCategory));
            }
            categoriesForFilm.forEach(categoryId -> {
                FilmCategoryRecord filmCategoryRecord = dslContext.newRecord(Tables.FILM_CATEGORY);
                filmCategoryRecord.setFilmId(filmId);
                filmCategoryRecord.setCategoryId(categoryId);
                filmCategoryRecord.store();
            });
        }
    }

    public void refreshActorsForFilm(long filmId, long maxActorId) {
        if (filmExistsById(filmId) && !filmByIdHasActors(filmId)) {
            // between 5 and 15 actors per film
            refreshActorsForFilmById(filmId, maxActorId);
        }
    }

    public void refreshActorsForFilm(FilmRecord filmRecord, long maxActorId) {
        if ((null != filmRecord) && (null != filmRecord.getFilmId()) && (0 < filmRecord.getFilmId())) {
            if (!filmByIdHasActors(filmRecord.getFilmId())) {
                refreshActorsForFilmById(filmRecord.getFilmId(), maxActorId);
            }
        }
    }

    private void refreshActorsForFilmById(long filmId, long maxActorId) {
        // between 5 and 15 actors per film
        int howManyActors = faker.random().nextInt(0, (int) Math.min(10L, maxActorId)) + 5;
        Set<Long> actorsForFilm = new HashSet<>(howManyActors);
        while (actorsForFilm.size() < howManyActors) {
            long whichActor = faker.random().nextLong(maxActorId - 1);
            if (actorExistsById(whichActor)) {
                actorsForFilm.add(whichActor);
            }
        }
        actorsForFilm.forEach(actorId -> {
            FilmActorRecord filmActorRecord = dslContext.newRecord(Tables.FILM_ACTOR);
            filmActorRecord.setActorId(actorId);
            filmActorRecord.setFilmId(filmId);
            filmActorRecord.store();
        });
    }

    public List<Long> setOfCategoryIds() {
        return dslContext.
                select(Tables.CATEGORY.CATEGORY_ID)
                .from(Tables.CATEGORY)
                .fetch()
                .stream()
                .map(Record1::value1)
                .toList();
    }

    public long refreshCategoriesForAllFilms() {
        log.info("Refreshing Categories for all Films");
        long count = 0;
        AggregateFunction<Integer> filmIdCount = DSL.count(Tables.FILM.FILM_ID);
        long totalNumberOfFilms = dslContext
                .select(filmIdCount)
                .from(Tables.FILM)
                .fetch().stream()
                .findFirst()
                .orElseThrow()
                .value1();
        if (totalNumberOfFilms > 0) {
            long maxFilmId = getMaxFilmId();
            List<Long> categoryIds = setOfCategoryIds();
            for (long i = 1; i <= maxFilmId; i++) {
                refreshCategoriesForFilm(i, categoryIds);
                count += 1;
                if ((count % 10000) == 0) {
                    log.info("{} out of {} Films had their categories refreshed", count, totalNumberOfFilms);
                }
            }
        }
        log.info("Done. {} out of {} Films had their categories refreshed", count, totalNumberOfFilms);
        return count;
    }

    public long refreshActorsForAllFilms() {
        log.info("Refreshing Actors for all Films");
        long count = 0;
        AggregateFunction<Integer> filmIdCount = DSL.count(Tables.FILM.FILM_ID);
        long totalNumberOfFilms = dslContext
                .select(filmIdCount)
                .from(Tables.FILM)
                .fetch().stream()
                .findFirst()
                .orElseThrow()
                .value1();
        if (totalNumberOfFilms > 0) {
            long maxFilmId = getMaxFilmId();
            long maxActorId = getMaxActorId();
            for (long i = 1; i <= maxFilmId; i++) {
                refreshActorsForFilm(i, maxActorId);
                count += 1;
                if ((count % 10000) == 0) {
                    log.info("{} out of {} Films had their Actors refreshed", count, totalNumberOfFilms);
                }
            }
        }
        log.info("Done. {} out of {} Films had their Actors refreshed", count, totalNumberOfFilms);
        return count;
    }
}