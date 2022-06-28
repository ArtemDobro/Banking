package com.andersenlab.rmtbanking.depositservice.entity;

import com.andersenlab.rmtbanking.depositservice.entity.enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.andersenlab.rmtbanking.depositservice.generator.UuidTimeSequenceGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "transaction_limit")
    private BigDecimal transactionLimit;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CardStatus status;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "holder_name")
    private String holderName;

    @Column(name = "virual_card")
    private boolean virtualCard;

    @Column(name = "digital_wallet")
    private String digitalWallet;

    @Column(name = "is_default")
    private boolean defaults;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "card_product_id", referencedColumnName = "id")
    private CardProduct cardProduct;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardNumber, card.cardNumber)
                && Objects.equals(cardProduct, card.cardProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardProduct);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", transactionLimit=" + transactionLimit +
                ", status=" + status +
                ", expirationDate=" + expirationDate +
                ", holderName='" + holderName + '\'' +
                ", virtualCard=" + virtualCard +
                ", digitalWallet='" + digitalWallet + '\'' +
                ", isDefault=" + defaults +
                ", cardProduct=" + cardProduct +
                '}';
    }
}