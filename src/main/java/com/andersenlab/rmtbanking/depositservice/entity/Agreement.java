package com.andersenlab.rmtbanking.depositservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agreements")
public class Agreement {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.andersenlab.rmtbanking.depositservice.generator.UuidTimeSequenceGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "agreement_number")
    private String agreementNumber;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "initial_amount")
    private BigDecimal initialAmount;

    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "auto_renewal")
    private boolean autoRenewal;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement agreement = (Agreement) o;
        return Objects.equals(agreementNumber, agreement.agreementNumber)
                && Objects.equals(product, agreement.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agreementNumber, product);
    }

    @Override
    public String toString() {
        return "Agreement{" +
                "id=" + id +
                ", agreementNumber='" + agreementNumber + '\'' +
                ", interestRate=" + interestRate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", initialAmount=" + initialAmount +
                ", currentBalance=" + currentBalance +
                ", isActive=" + active +
                ", autoRenewal=" + autoRenewal +
                ", account=" + account +
                '}';
    }
}