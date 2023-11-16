package org.tayrona.sakila.data.utils;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CsvTranscoder {
    private final DSLContext dslContext;

    public CsvTranscoder(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

//    public long exportTitles(String fileName) throws IOException {
//        long rowCount = 0;
//        try (PrintStream ps = new PrintStream(fileName, StandardCharsets.UTF_8)) {
//            Result<MovieTitlesRecord> result = dslContext.selectFrom(Tables.MOVIE_TITLES).fetch();
//            for (MovieTitlesRecord movieTitlesRecord : result) {
//                String title = movieTitlesRecord.getTitle();
//                if (StringUtils.isAcceptableFilmTitle(tile)) {
//                    String desc = movieTitlesRecord.getDescription();
//                    if (null != desc) {
//                        ps.println(title + "|" + desc);
//                    } else {
//                        ps.println(title);
//                    }
//                    rowCount += 1;
//                }
//                if ((rowCount % 10000)==0) {
//                    log.info("{} imdb titles exported", rowCount);
//                }
//            }
//        } catch (IOException e) {
//            throw new IOException(e);
//        }
//        return rowCount;
//    }

//    public long exportNames(String fileName) throws IOException {
//        long rowCount = 0;
//        long totalCount = 0;
//        try (PrintStream ps = new PrintStream(fileName, StandardCharsets.UTF_8)) {
//            Result<Record1<String>> result = dslContext.select(Tables.ACTOR_NAMES.PRIMARYNAME).from(Tables.ACTOR_NAMES).fetch();
//            for (Record1<String> primaryNameRecord : result) {
//                String primaryName = primaryNameRecord.get(0, String.class);
//                if (StringUtils.isAcceptableFullName(primaryName)) {
//                    ps.println(primaryName);
//                    rowCount += 1;
//                }
//                totalCount += 1;
//                if ((totalCount % 10000) == 0) {
//                    log.info("{} of {} Actor names exported", rowCount, totalCount);
//                }
//            }
//        } catch (IOException e) {
//            throw new IOException(e);
//        }
//        log.info("Done. {} of {} Actor names exported", rowCount, totalCount);
//        return rowCount;
//    }
}
