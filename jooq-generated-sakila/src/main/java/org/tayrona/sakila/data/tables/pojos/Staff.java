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

import javax.annotation.Nullable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;


/**
 * Staff details table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "STAFF",
    schema = "PUBLIC"
)
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long staffId;
    private String firstName;
    private String lastName;
    private Long addressId;
    private byte[] picture;
    private String email;
    private Long storeId;
    private Boolean active;
    private String username;
    private byte[] password;
    private LocalDateTime lastUpdate;

    public Staff() {}

    public Staff(Staff value) {
        this.staffId = value.staffId;
        this.firstName = value.firstName;
        this.lastName = value.lastName;
        this.addressId = value.addressId;
        this.picture = value.picture;
        this.email = value.email;
        this.storeId = value.storeId;
        this.active = value.active;
        this.username = value.username;
        this.password = value.password;
        this.lastUpdate = value.lastUpdate;
    }

    public Staff(
        Long staffId,
        String firstName,
        String lastName,
        Long addressId,
        @Nullable byte[] picture,
        @Nullable String email,
        Long storeId,
        Boolean active,
        String username,
        @Nullable byte[] password,
        LocalDateTime lastUpdate
    ) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = addressId;
        this.picture = picture;
        this.email = email;
        this.storeId = storeId;
        this.active = active;
        this.username = username;
        this.password = password;
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter for <code>PUBLIC.STAFF.STAFF_ID</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STAFF_ID", nullable = false)
    public Long getStaffId() {
        return this.staffId;
    }

    /**
     * Setter for <code>PUBLIC.STAFF.STAFF_ID</code>.
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * Getter for <code>PUBLIC.STAFF.FIRST_NAME</code>.
     */
    @Column(name = "FIRST_NAME", nullable = false, length = 45)
    @NotNull
    @Size(max = 45)
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Setter for <code>PUBLIC.STAFF.FIRST_NAME</code>.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for <code>PUBLIC.STAFF.LAST_NAME</code>.
     */
    @Column(name = "LAST_NAME", nullable = false, length = 45)
    @NotNull
    @Size(max = 45)
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Setter for <code>PUBLIC.STAFF.LAST_NAME</code>.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for <code>PUBLIC.STAFF.ADDRESS_ID</code>.
     */
    @Column(name = "ADDRESS_ID", nullable = false)
    @NotNull
    public Long getAddressId() {
        return this.addressId;
    }

    /**
     * Setter for <code>PUBLIC.STAFF.ADDRESS_ID</code>.
     */
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    /**
     * Getter for <code>PUBLIC.STAFF.PICTURE</code>.
     */
    @Column(name = "PICTURE")
    @Nullable
    public byte[] getPicture() {
        return this.picture;
    }

    /**
     * Setter for <code>PUBLIC.STAFF.PICTURE</code>.
     */
    public void setPicture(@Nullable byte[] picture) {
        this.picture = picture;
    }

    /**
     * Getter for <code>PUBLIC.STAFF.EMAIL</code>.
     */
    @Column(name = "EMAIL", length = 50)
    @Size(max = 50)
    @Nullable
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for <code>PUBLIC.STAFF.EMAIL</code>.
     */
    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    /**
     * Getter for <code>PUBLIC.STAFF.STORE_ID</code>.
     */
    @Column(name = "STORE_ID", nullable = false)
    @NotNull
    public Long getStoreId() {
        return this.storeId;
    }

    /**
     * Setter for <code>PUBLIC.STAFF.STORE_ID</code>.
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * Getter for <code>PUBLIC.STAFF.ACTIVE</code>.
     */
    @Column(name = "ACTIVE", nullable = false)
    public Boolean getActive() {
        return this.active;
    }

    /**
     * Setter for <code>PUBLIC.STAFF.ACTIVE</code>.
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Getter for <code>PUBLIC.STAFF.USERNAME</code>.
     */
    @Column(name = "USERNAME", nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    public String getUsername() {
        return this.username;
    }

    /**
     * Setter for <code>PUBLIC.STAFF.USERNAME</code>.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for <code>PUBLIC.STAFF.PASSWORD</code>.
     */
    @Column(name = "PASSWORD", length = 40)
    @Size(max = 40)
    @Nullable
    public byte[] getPassword() {
        return this.password;
    }

    /**
     * Setter for <code>PUBLIC.STAFF.PASSWORD</code>.
     */
    public void setPassword(@Nullable byte[] password) {
        this.password = password;
    }

    /**
     * Getter for <code>PUBLIC.STAFF.LAST_UPDATE</code>.
     */
    @Column(name = "LAST_UPDATE", nullable = false, precision = 6)
    public LocalDateTime getLastUpdate() {
        return this.lastUpdate;
    }

    /**
     * Setter for <code>PUBLIC.STAFF.LAST_UPDATE</code>.
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
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
        final Staff other = (Staff) obj;
        if (this.staffId == null) {
            if (other.staffId != null)
                return false;
        }
        else if (!this.staffId.equals(other.staffId))
            return false;
        if (this.firstName == null) {
            if (other.firstName != null)
                return false;
        }
        else if (!this.firstName.equals(other.firstName))
            return false;
        if (this.lastName == null) {
            if (other.lastName != null)
                return false;
        }
        else if (!this.lastName.equals(other.lastName))
            return false;
        if (this.addressId == null) {
            if (other.addressId != null)
                return false;
        }
        else if (!this.addressId.equals(other.addressId))
            return false;
        if (this.picture == null) {
            if (other.picture != null)
                return false;
        }
        else if (!Arrays.equals(this.picture, other.picture))
            return false;
        if (this.email == null) {
            if (other.email != null)
                return false;
        }
        else if (!this.email.equals(other.email))
            return false;
        if (this.storeId == null) {
            if (other.storeId != null)
                return false;
        }
        else if (!this.storeId.equals(other.storeId))
            return false;
        if (this.active == null) {
            if (other.active != null)
                return false;
        }
        else if (!this.active.equals(other.active))
            return false;
        if (this.username == null) {
            if (other.username != null)
                return false;
        }
        else if (!this.username.equals(other.username))
            return false;
        if (this.password == null) {
            if (other.password != null)
                return false;
        }
        else if (!Arrays.equals(this.password, other.password))
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
        result = prime * result + ((this.staffId == null) ? 0 : this.staffId.hashCode());
        result = prime * result + ((this.firstName == null) ? 0 : this.firstName.hashCode());
        result = prime * result + ((this.lastName == null) ? 0 : this.lastName.hashCode());
        result = prime * result + ((this.addressId == null) ? 0 : this.addressId.hashCode());
        result = prime * result + ((this.picture == null) ? 0 : Arrays.hashCode(this.picture));
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.storeId == null) ? 0 : this.storeId.hashCode());
        result = prime * result + ((this.active == null) ? 0 : this.active.hashCode());
        result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
        result = prime * result + ((this.password == null) ? 0 : Arrays.hashCode(this.password));
        result = prime * result + ((this.lastUpdate == null) ? 0 : this.lastUpdate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Staff (");

        sb.append(staffId);
        sb.append(", ").append(firstName);
        sb.append(", ").append(lastName);
        sb.append(", ").append(addressId);
        sb.append(", ").append("[binary...]");
        sb.append(", ").append(email);
        sb.append(", ").append(storeId);
        sb.append(", ").append(active);
        sb.append(", ").append(username);
        sb.append(", ").append("[binary...]");
        sb.append(", ").append(lastUpdate);

        sb.append(")");
        return sb.toString();
    }
}
