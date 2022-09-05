package com.andersenlab.rmtbanking.depositservice.topicKafkaRequest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public final class CardOrderRequest {

    @JsonProperty("account_number")
    private final String accountNumber;

    @JsonProperty("card_number")
    private final String cardNumber;

    @JsonProperty("transaction_limit")
    private final String transactionLimit;

    @JsonProperty("status")
    private final String status;

    @JsonProperty("expiration_date")
    private final String expirationDate;

    @JsonProperty("holder_name")
    private final String holderName;

    @JsonProperty("digital_wallet")
    private final String digitalWallet;

    @JsonProperty("card_product_Id")
    private final String cardProductId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CardOrderRequest(@JsonProperty("account_number") String accountNumber,
                            @JsonProperty("card_number") String cardNumber,
                            @JsonProperty("transaction_limit") String transactionLimit,
                            @JsonProperty("status") String status,
                            @JsonProperty("expiration_date") String expirationDate,
                            @JsonProperty("holder_name") String holderName,
                            @JsonProperty("digital_wallet") String digitalWallet,
                            @JsonProperty("card_product_Id") String cardProductId) {
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.transactionLimit = transactionLimit;
        this.status = status;
        this.expirationDate = expirationDate;
        this.holderName = holderName;
        this.digitalWallet = digitalWallet;
        this.cardProductId = cardProductId;
    }

}
