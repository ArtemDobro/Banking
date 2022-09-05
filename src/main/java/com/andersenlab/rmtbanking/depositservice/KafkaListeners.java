package com.andersenlab.rmtbanking.depositservice;

import com.andersenlab.rmtbanking.depositservice.topicKafkaRequest.CardOrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private final Logger log = LoggerFactory.getLogger(KafkaListeners.class);

    @KafkaListener(
            topics = {
                    "deposit_to_master_card_order"
            },
            containerFactory = "cardOrderContainerFactory"
    )
    public void balanceChangeEventListener(CardOrderRequest request) {
        log.info("Listener received: " + request);
    }


}
