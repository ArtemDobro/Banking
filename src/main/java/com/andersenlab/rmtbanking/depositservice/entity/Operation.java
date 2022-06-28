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
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.andersenlab.rmtbanking.depositservice.generator.UuidTimeSequenceGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "completed_at")
    private Timestamp completedAt;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "details")
    private String detail;

    @Column(name = "currency_code")
    private String currencyCode;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_type_id", referencedColumnName = "id")
    private OperationType operationType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(account, operation.account)
                && Objects.equals(operationType, operation.operationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, operationType);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", completedAt=" + completedAt +
                ", sum=" + sum +
                ", detail='" + detail + '\'' +
                ", currencyCode=" + currencyCode +
                ", operation type" + operationType +
                '}';
    }
}