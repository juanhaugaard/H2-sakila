/*
 * This file is generated by jOOQ.
 */
package org.tayrona.sakila.data.tables.pojos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


/**
 * Rental details table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "RENTAL",
    schema = "PUBLIC",
    indexes = {
        @Index(name = "IDX_RENTAL_DATE_INVENTORY_ID_CUSTOMER_ID", unique = true, columnList = "RENTAL_DATE ASC, INVENTORY_ID ASC, CUSTOMER_ID ASC")
    }
)
public class Rental implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long rentalId;
    private LocalDateTime rentalDate;
    private Long inventoryId;
    private Long customerId;
    private LocalDateTime returnDate;
    private Long staffId;
    private OffsetDateTime lastUpdate;

    public Rental() {}

    public Rental(Rental value) {
        this.rentalId = value.rentalId;
        this.rentalDate = value.rentalDate;
        this.inventoryId = value.inventoryId;
        this.customerId = value.customerId;
        this.returnDate = value.returnDate;
        this.staffId = value.staffId;
        this.lastUpdate = value.lastUpdate;
    }

    public Rental(
        Long rentalId,
        LocalDateTime rentalDate,
        Long inventoryId,
        Long customerId,
        @Nullable LocalDateTime returnDate,
        Long staffId,
        OffsetDateTime lastUpdate
    ) {
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        this.inventoryId = inventoryId;
        this.customerId = customerId;
        this.returnDate = returnDate;
        this.staffId = staffId;
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter for <code>PUBLIC.RENTAL.RENTAL_ID</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RENTAL_ID", nullable = false)
    public Long getRentalId() {
        return this.rentalId;
    }

    /**
     * Setter for <code>PUBLIC.RENTAL.RENTAL_ID</code>.
     */
    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    /**
     * Getter for <code>PUBLIC.RENTAL.RENTAL_DATE</code>.
     */
    @Column(name = "RENTAL_DATE", nullable = false, precision = 6)
    @NotNull
    public LocalDateTime getRentalDate() {
        return this.rentalDate;
    }

    /**
     * Setter for <code>PUBLIC.RENTAL.RENTAL_DATE</code>.
     */
    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * Getter for <code>PUBLIC.RENTAL.INVENTORY_ID</code>.
     */
    @Column(name = "INVENTORY_ID", nullable = false)
    @NotNull
    public Long getInventoryId() {
        return this.inventoryId;
    }

    /**
     * Setter for <code>PUBLIC.RENTAL.INVENTORY_ID</code>.
     */
    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    /**
     * Getter for <code>PUBLIC.RENTAL.CUSTOMER_ID</code>.
     */
    @Column(name = "CUSTOMER_ID", nullable = false)
    @NotNull
    public Long getCustomerId() {
        return this.customerId;
    }

    /**
     * Setter for <code>PUBLIC.RENTAL.CUSTOMER_ID</code>.
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * Getter for <code>PUBLIC.RENTAL.RETURN_DATE</code>.
     */
    @Column(name = "RETURN_DATE", precision = 6)
    @Nullable
    public LocalDateTime getReturnDate() {
        return this.returnDate;
    }

    /**
     * Setter for <code>PUBLIC.RENTAL.RETURN_DATE</code>.
     */
    public void setReturnDate(@Nullable LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Getter for <code>PUBLIC.RENTAL.STAFF_ID</code>.
     */
    @Column(name = "STAFF_ID", nullable = false)
    @NotNull
    public Long getStaffId() {
        return this.staffId;
    }

    /**
     * Setter for <code>PUBLIC.RENTAL.STAFF_ID</code>.
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * Getter for <code>PUBLIC.RENTAL.LAST_UPDATE</code>.
     */
    @Column(name = "LAST_UPDATE", nullable = false, precision = 6)
    public OffsetDateTime getLastUpdate() {
        return this.lastUpdate;
    }

    /**
     * Setter for <code>PUBLIC.RENTAL.LAST_UPDATE</code>.
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
        final Rental other = (Rental) obj;
        if (this.rentalId == null) {
            if (other.rentalId != null)
                return false;
        }
        else if (!this.rentalId.equals(other.rentalId))
            return false;
        if (this.rentalDate == null) {
            if (other.rentalDate != null)
                return false;
        }
        else if (!this.rentalDate.equals(other.rentalDate))
            return false;
        if (this.inventoryId == null) {
            if (other.inventoryId != null)
                return false;
        }
        else if (!this.inventoryId.equals(other.inventoryId))
            return false;
        if (this.customerId == null) {
            if (other.customerId != null)
                return false;
        }
        else if (!this.customerId.equals(other.customerId))
            return false;
        if (this.returnDate == null) {
            if (other.returnDate != null)
                return false;
        }
        else if (!this.returnDate.equals(other.returnDate))
            return false;
        if (this.staffId == null) {
            if (other.staffId != null)
                return false;
        }
        else if (!this.staffId.equals(other.staffId))
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
        result = prime * result + ((this.rentalId == null) ? 0 : this.rentalId.hashCode());
        result = prime * result + ((this.rentalDate == null) ? 0 : this.rentalDate.hashCode());
        result = prime * result + ((this.inventoryId == null) ? 0 : this.inventoryId.hashCode());
        result = prime * result + ((this.customerId == null) ? 0 : this.customerId.hashCode());
        result = prime * result + ((this.returnDate == null) ? 0 : this.returnDate.hashCode());
        result = prime * result + ((this.staffId == null) ? 0 : this.staffId.hashCode());
        result = prime * result + ((this.lastUpdate == null) ? 0 : this.lastUpdate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Rental (");

        sb.append(rentalId);
        sb.append(", ").append(rentalDate);
        sb.append(", ").append(inventoryId);
        sb.append(", ").append(customerId);
        sb.append(", ").append(returnDate);
        sb.append(", ").append(staffId);
        sb.append(", ").append(lastUpdate);

        sb.append(")");
        return sb.toString();
    }
}
