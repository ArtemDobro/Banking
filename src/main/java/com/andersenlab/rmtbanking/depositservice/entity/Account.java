package com.andersenlab.rmtbanking.depositservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.andersenlab.rmtbanking.depositservice.generator.UuidTimeSequenceGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    @Column(name = "open_date")
    private Date openDate;

    @Column(name = "close_date")
    private Date closeDate;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "salary_project")
    private String salaryProject;

    @Column(name = "blocked_sum")
    private BigDecimal blockedSum;

    @OneToMany(mappedBy = "account",
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private List<Operation> operations;

    @OneToMany(mappedBy = "account",
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private List<Card> cards;

    @OneToMany(mappedBy = "account",
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private List<Agreement> agreements;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account that = (Account) o;
        return Objects.equals(this.accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", currencyCode=" + currencyCode +
                ", currentBalance=" + currentBalance +
                ", openDate=" + openDate +
                ", closeDate=" + closeDate +
                ", isActive=" + active +
                ", salaryProject='" + salaryProject + '\'' +
                ", blockedSum=" + blockedSum +
                ", operations=" + operations +
                ", cards=" + cards +
                '}';
    }
}