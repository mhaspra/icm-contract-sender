package ch.mhaspra.icm.contract.sender;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
public class ContractDataSender {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ContractClient contractClient;

    @Inject
    public ContractDataSender(ContractClient contractClient) {
        this.contractClient = contractClient;
    }

    @Scheduled(initialDelay = "3s", fixedRate = "200ms")
    void send(){
        var partnerNr = ThreadLocalRandom.current().nextLong(1, 21);
        var contractNr = partnerNr * 100 + ThreadLocalRandom.current().nextLong(1, 6);
        var active = ThreadLocalRandom.current().nextBoolean();

        var contract = new Contract(contractNr, partnerNr, "Policy-" + contractNr, active);

        LOG.info("sending: " + contract);
        contractClient.sendContract(contract.contractNr().toString(), contract);
    }
}
