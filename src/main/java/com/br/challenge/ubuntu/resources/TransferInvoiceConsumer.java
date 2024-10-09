package com.br.challenge.ubuntu.resources;

import com.br.challenge.ubuntu.entities.Paid;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.starkbank.Transfer;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class TransferInvoiceConsumer {

    @Incoming("challenge")
    public void consume(String message) throws Exception {
        JsonElement jsonElement = JsonParser.parseString(message);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String key = jsonObject.keySet().iterator().next();
        JsonObject details = jsonObject.getAsJsonObject(key);


        List<Transfer> transfers = new ArrayList<>();

        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", details.get("amount").getAsBigDecimal());
        data.put("bankCode", "20018183");
        data.put("branchCode", "0001");
        data.put("accountNumber", "6341320293482496");
        data.put("taxId", "20.018.183/0001-80");
        data.put("accountType", "payment");
        data.put("name", "Stark Bank S.A");

        transfers.add(new Transfer(data));

        Transfer.create(transfers);

        Paid paid = new Paid();
        paid.setAmount(details.get("amount").getAsBigDecimal());
        paid.setInTaxId("20.018.183/0001-80");
        paid.setOutTaxId(details.get("outTaxId").getAsString());
        paid.setStatus("Success");
    }

}
