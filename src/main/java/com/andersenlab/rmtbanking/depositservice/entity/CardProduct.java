package com.andersenlab.rmtbanking.depositservice.entity;

import com.andersenlab.rmtbanking.depositservice.entity.enums.PaymentSystem;
import com.andersenlab.rmtbanking.depositservice.entity.enums.PremiumStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card_products")
public class CardProduct {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "payment_system")
    @Enumerated(EnumType.STRING)
    private PaymentSystem paymentSystem;

    @Column(name = "cash_back")
    private BigDecimal cashBack;

    @Column(name = "co_brand")
    private String coBrand;

    @Column(name = "is_virtual")
    private boolean virtual;

    @Column(name = "premium_status")
    @Enumerated(EnumType.STRING)
    private PremiumStatus premiumStatus;

    @Column(name = "service_price")
    private BigDecimal servicePrice;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "card_duration")
    private Integer cardDuration;

    @OneToMany(mappedBy = "cardProduct",
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private List<Card> cards;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardProduct that = (CardProduct) o;
        return Objects.equals(this.cardName, that.cardName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.cardName);
    }

    @Override
    public String toString() {
        return "CardProduct{" +
                "id=" + id +
                ", cardName='" + cardName + '\'' +
                ", paymentSystem=" + paymentSystem +
                ", cashBack=" + cashBack +
                ", coBrand='" + coBrand + '\'' +
                ", isVirtual=" + virtual +
                ", premiumStatus=" + premiumStatus +
                ", servicePrice=" + servicePrice +
                ", productPrice=" + productPrice +
                ", currencyCode=" + currencyCode +
                ", isActive=" + active +
                ", cardDuration=" + cardDuration +
                '}';
    }
}