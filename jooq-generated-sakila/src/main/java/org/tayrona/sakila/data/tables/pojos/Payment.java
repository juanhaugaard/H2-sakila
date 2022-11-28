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

import javax.annotation.Nullable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


/**
 * Payment details table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "PAYMENT",
    schema = "PUBLIC"
)
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long paymentId;
    private Long customerId;
    private Long staffId;
    private Long rentalId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private OffsetDateTime lastUpdate;

    public Payment() {}

    public Payment(Payment value) {
        this.paymentId = value.paymentId;
        this.customerId = value.customerId;
        this.staffId = value.staffId;
        this.rentalId = value.rentalId;
        this.amount = value.amount;
        this.paymentDate = value.paymentDate;
        this.lastUpdate = value.lastUpdate;
    }

    public Payment(
        Long paymentId,
        Long customerId,
        Long staffId,
        @Nullable Long rentalId,
        BigDecimal amount,
        LocalDateTime paymentDate,
        OffsetDateTime lastUpdate
    ) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.staffId = staffId;
        this.rentalId = rentalId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.PAYMENT_ID</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_ID", nullable = false)
    public Long getPaymentId() {
        return this.paymentId;
    }

    /**
     * Setter for <code>PUBLIC.PAYMENT.PAYMENT_ID</code>.
     */
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.CUSTOMER_ID</code>.
     */
    @Column(name = "CUSTOMER_ID", nullable = false)
    @NotNull
    public Long getCustomerId() {
        return this.customerId;
    }

    /**
     * Setter for <code>PUBLIC.PAYMENT.CUSTOMER_ID</code>.
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.STAFF_ID</code>.
     */
    @Column(name = "STAFF_ID", nullable = false)
    @NotNull
    public Long getStaffId() {
        return this.staffId;
    }

    /**
     * Setter for <code>PUBLIC.PAYMENT.STAFF_ID</code>.
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.RENTAL_ID</code>.
     */
    @Column(name = "RENTAL_ID")
    @Nullable
    public Long getRentalId() {
        return this.rentalId;
    }

    /**
     * Setter for <code>PUBLIC.PAYMENT.RENTAL_ID</code>.
     */
    public void setRentalId(@Nullable Long rentalId) {
        this.rentalId = rentalId;
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.AMOUNT</code>.
     */
    @Column(name = "AMOUNT", nullable = false, precision = 5, scale = 2)
    @NotNull
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Setter for <code>PUBLIC.PAYMENT.AMOUNT</code>.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.PAYMENT_DATE</code>.
     */
    @Column(name = "PAYMENT_DATE", nullable = false, precision = 6)
    @NotNull
    public LocalDateTime getPaymentDate() {
        return this.paymentDate;
    }

    /**
     * Setter for <code>PUBLIC.PAYMENT.PAYMENT_DATE</code>.
     */
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.LAST_UPDATE</code>.
     */
    @Column(name = "LAST_UPDATE", nullable = false, precision = 6)
    public OffsetDateTime getLastUpdate() {
        return this.lastUpdate;
    }

    /**
     * Setter for <code>PUBLIC.PAYMENT.LAST_UPDATE</code>.
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
        final Payment other = (Payment) obj;
        if (this.paymentId == null) {
            if (other.paymentId != null)
                return false;
        }
        else if (!this.paymentId.equals(other.paymentId))
            return false;
        if (this.customerId == null) {
            if (other.customerId != null)
                return false;
        }
        else if (!this.customerId.equals(other.customerId))
            return false;
        if (this.staffId == null) {
            if (other.staffId != null)
                return false;
        }
        else if (!this.staffId.equals(other.staffId))
            return false;
        if (this.rentalId == null) {
            if (other.rentalId != null)
                return false;
        }
        else if (!this.rentalId.equals(other.rentalId))
            return false;
        if (this.amount == null) {
            if (other.amount != null)
                return false;
        }
        else if (!this.amount.equals(other.amount))
            return false;
        if (this.paymentDate == null) {
            if (other.paymentDate != null)
                return false;
        }
        else if (!this.paymentDate.equals(other.paymentDate))
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
        result = prime * result + ((this.paymentId == null) ? 0 : this.paymentId.hashCode());
        result = prime * result + ((this.customerId == null) ? 0 : this.customerId.hashCode());
        result = prime * result + ((this.staffId == null) ? 0 : this.staffId.hashCode());
        result = prime * result + ((this.rentalId == null) ? 0 : this.rentalId.hashCode());
        result = prime * result + ((this.amount == null) ? 0 : this.amount.hashCode());
        result = prime * result + ((this.paymentDate == null) ? 0 : this.paymentDate.hashCode());
        result = prime * result + ((this.lastUpdate == null) ? 0 : this.lastUpdate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Payment (");

        sb.append(paymentId);
        sb.append(", ").append(customerId);
        sb.append(", ").append(staffId);
        sb.append(", ").append(rentalId);
        sb.append(", ").append(amount);
        sb.append(", ").append(paymentDate);
        sb.append(", ").append(lastUpdate);

        sb.append(")");
        return sb.toString();
    }
}
