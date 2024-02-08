package asw.ordermanager.ordervalidationservice.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "order-created-topic", groupId = "my-group-id")
    public void listen(Object message) {
        System.out.println("Received message: " + message);
    }

}
