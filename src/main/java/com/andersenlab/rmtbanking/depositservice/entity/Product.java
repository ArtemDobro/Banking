package com.andersenlab.rmtbanking.depositservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "schema_name")
    private String schemaName;

    @Column(name = "interest_rate_early")
    private BigDecimal interestRateEarly;

    @Column(name = "is_capitalization")
    private boolean capitalization;

    @Column(name = "amount_min")
    private BigDecimal amountMin;

    @Column(name = "amount_max")
    private BigDecimal amountMax;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "is_revocable")
    private boolean revocable;

    @Column(name = "min_duration_months")
    private Integer minDurationMonths;

    @Column(name = "max_duration_months")
    private Integer maxDurationMonths;

    @Column(name = "min_interest_rate")
    private BigDecimal minInterestRate;

    @Column(name = "max_interest_rate")
    private BigDecimal maxInterestRate;

    @Column(name = "active_since")
    private LocalDate activeSince;

    @Column(name = "active_until")
    private LocalDate activeUntil;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(this.name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", schemaName='" + schemaName + '\'' +
                ", interestRateEarly=" + interestRateEarly +
                ", isCapitalization=" + capitalization +
                ", amountMin=" + amountMin +
                ", amountMax=" + amountMax +
                ", currencyCode=" + currencyCode +
                ", isActive=" + active +
                ", isRevocable=" + revocable +
                ", minDurationMonths=" + minDurationMonths +
                ", maxDurationMonths=" + maxDurationMonths +
                ", minInterestRate=" + minInterestRate +
                ", maxInterestRate=" + maxInterestRate +
                ", activeSince=" + activeSince +
                ", activeUntil=" + activeUntil +
                '}';
    }
}