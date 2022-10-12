package ch.mhaspra.icm.contract.sender;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface ContractClient {
    @Topic("contract-topic")
    void sendContract(@KafkaKey String contractNr, Contract contract);
}
