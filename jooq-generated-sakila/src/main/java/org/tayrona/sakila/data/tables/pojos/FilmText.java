/*
 * This file is generated by jOOQ.
 */
package org.tayrona.sakila.data.tables.pojos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

import javax.annotation.Nullable;


/**
 * Film title and description table for fulltext search
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "FILM_TEXT",
    schema = "PUBLIC",
    indexes = {
        @Index(name = "IDX_TITLE_DESCRIPTION", unique = true, columnList = "TITLE ASC, DESCRIPTION ASC")
    }
)
public class FilmText implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long filmId;
    private String title;
    private String description;

    public FilmText() {}

    public FilmText(FilmText value) {
        this.filmId = value.filmId;
        this.title = value.title;
        this.description = value.description;
    }

    public FilmText(
        Long filmId,
        String title,
        @Nullable String description
    ) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
    }

    /**
     * Getter for <code>PUBLIC.FILM_TEXT.FILM_ID</code>.
     */
    @Id
    @Column(name = "FILM_ID", nullable = false)
    @NotNull
    public Long getFilmId() {
        return this.filmId;
    }

    /**
     * Setter for <code>PUBLIC.FILM_TEXT.FILM_ID</code>.
     */
    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    /**
     * Getter for <code>PUBLIC.FILM_TEXT.TITLE</code>.
     */
    @Column(name = "TITLE", nullable = false, length = 255)
    @NotNull
    @Size(max = 255)
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter for <code>PUBLIC.FILM_TEXT.TITLE</code>.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for <code>PUBLIC.FILM_TEXT.DESCRIPTION</code>.
     */
    @Column(name = "DESCRIPTION", length = 1000000000)
    @Size(max = 1000000000)
    @Nullable
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>PUBLIC.FILM_TEXT.DESCRIPTION</code>.
     */
    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FilmText other = (FilmText) obj;
        if (this.filmId == null) {
            if (other.filmId != null)
                return false;
        }
        else if (!this.filmId.equals(other.filmId))
            return false;
        if (this.title == null) {
            if (other.title != null)
                return false;
        }
        else if (!this.title.equals(other.title))
            return false;
        if (this.description == null) {
            if (other.description != null)
                return false;
        }
        else if (!this.description.equals(other.description))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.filmId == null) ? 0 : this.filmId.hashCode());
        result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FilmText (");

        sb.append(filmId);
        sb.append(", ").append(title);
        sb.append(", ").append(description);

        sb.append(")");
        return sb.toString();
    }
}
