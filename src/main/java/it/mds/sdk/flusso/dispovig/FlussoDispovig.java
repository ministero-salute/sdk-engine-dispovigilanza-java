/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@ComponentScan({"it.mds.sdk.flusso.dispovig.controller", "it.mds.sdk.flusso.dispovig", "it.mds.sdk.rest.persistence.entity",
        "it.mds.sdk.libreriaregole.validator",
        "it.mds.sdk.flusso.dispovig.service", "it.mds.sdk.flusso.dispovigold.tracciato",
        "it.mds.sdk.gestoreesiti", "it.mds.sdk.flusso.dispovigold.parser.regole", "it.mds.sdk.flusso.dispovigold.parser.regole.conf",
        "it.mds.sdk.connettoremds"})

@OpenAPIDefinition(info = @Info(title = "SDK Ministero Della Salute - Flusso DISPOVIG", version = "0.0.5-SNAPSHOT", description = "Flusso DISPOVIG"))
public class FlussoDispovig {
    public static void main(String[] args) {
        SpringApplication.run(FlussoDispovig.class, args);
    }
}
