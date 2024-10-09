package com.br.challenge.ubuntu.helpers;

import com.starkbank.*;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.nio.file.Files;
import java.nio.file.Paths;

@Startup
@ApplicationScoped
public class Authentication {

    @ConfigProperty(name = "stark.account.id")
    String accountId;

    @ConfigProperty(name = "stark.account.environment")
    String zone;

    @ConfigProperty(name = "stark.account.key")
    String key;

    @PostConstruct
    void execute() throws Exception {
        System.out.println("Set auth");
        String privateKeyContent = Files.readString(Paths.get(key));

        Settings.user = new Project(
                zone,
                accountId,
                privateKeyContent
        );
    }


}
