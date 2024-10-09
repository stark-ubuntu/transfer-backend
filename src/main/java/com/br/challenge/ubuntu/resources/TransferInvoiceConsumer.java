package com.br.challenge.ubuntu.resources;

import com.br.challenge.ubuntu.entities.Paid;
import com.starkbank.Transfer;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class TransferInvoiceConsumer {

    @Incoming("challenge")
    public void consume(HashMap<String, BigDecimal> message) throws Exception {
        Map.Entry<String, BigDecimal> firstEntry = message.entrySet().iterator().next();
        List<Transfer> transfers = new ArrayList<>();

        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", firstEntry.getValue());
        data.put("bankCode", "20018183");
        data.put("branchCode", "0001");
        data.put("accountNumber", "6341320293482496");
        data.put("taxId", "20.018.183/0001-80");
        data.put("accountType", "payment");

        transfers.add(new Transfer(data));

        Transfer.create(transfers);

        Paid paid = new Paid();
        paid.setAmount(firstEntry.getValue());
        paid.setInTaxId("20.018.183/0001-80");
        paid.setOutTaxId(firstEntry.getKey());
        paid.setStatus("Success");
    }

}
