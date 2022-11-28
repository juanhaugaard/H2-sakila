/*
 * This file is generated by jOOQ.
 */
package org.tayrona.sakila.data.tables.pojos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.OffsetDateTime;


/**
 * Language details table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "LANGUAGE",
    schema = "PUBLIC"
)
public class Language implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long languageId;
    private String name;
    private OffsetDateTime lastUpdate;

    public Language() {}

    public Language(Language value) {
        this.languageId = value.languageId;
        this.name = value.name;
        this.lastUpdate = value.lastUpdate;
    }

    public Language(
        Long languageId,
        String name,
        OffsetDateTime lastUpdate
    ) {
        this.languageId = languageId;
        this.name = name;
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter for <code>PUBLIC.LANGUAGE.LANGUAGE_ID</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LANGUAGE_ID", nullable = false)
    public Long getLanguageId() {
        return this.languageId;
    }

    /**
     * Setter for <code>PUBLIC.LANGUAGE.LANGUAGE_ID</code>.
     */
    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    /**
     * Getter for <code>PUBLIC.LANGUAGE.NAME</code>.
     */
    @Column(name = "NAME", nullable = false, length = 20)
    @NotNull
    @Size(max = 20)
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>PUBLIC.LANGUAGE.NAME</code>.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>PUBLIC.LANGUAGE.LAST_UPDATE</code>.
     */
    @Column(name = "LAST_UPDATE", nullable = false, precision = 6)
    public OffsetDateTime getLastUpdate() {
        return this.lastUpdate;
    }

    /**
     * Setter for <code>PUBLIC.LANGUAGE.LAST_UPDATE</code>.
     */
    public void setLastUpdate(OffsetDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Language other = (Language) obj;
        if (this.languageId == null) {
            if (other.languageId != null)
                return false;
        }
        else if (!this.languageId.equals(other.languageId))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.lastUpdate == null) {
            if (other.lastUpdate != null)
                return false;
        }
        else if (!this.lastUpdate.equals(other.lastUpdate))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.languageId == null) ? 0 : this.languageId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.lastUpdate == null) ? 0 : this.lastUpdate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Language (");

        sb.append(languageId);
        sb.append(", ").append(name);
        sb.append(", ").append(lastUpdate);

        sb.append(")");
        return sb.toString();
    }
}