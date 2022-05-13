package com.nubank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.model.Share;
import com.nubank.model.Tax;
import com.nubank.service.OperationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Service
public class Controller {

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    private final OperationFacade operationFacade;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public Controller(OperationFacade operationFacade) {
        this.operationFacade = operationFacade;
    }

    @PostConstruct
    private void calculate() {

        if (activeProfile.equals("dev")) {
            return;
        }

        Scanner sc = new Scanner(System.in);

        log.info("Informe o Json que deseja processar.");
        while (sc.hasNext()) {

            String strInput = sc.nextLine();

            exit(strInput);

            List<Share> shares = stringToShares(strInput);

            if (!shares.isEmpty()) {
                List<Tax> running = operationFacade.running(shares);
                String jsonOut = taxesToJson(running);
                log.info(jsonOut);
            }

            log.info("Informe outro Json que deseja processar.");
        }

    }

    private void exit(String text) {
        if (text.equalsIgnoreCase("exit")) {
            System.exit(-1);
        }
    }

    private List<Share> stringToShares(String jsonIn) {
        Share[] shares = new Share[0];

        try {
            shares = objectMapper.readValue(jsonIn, Share[].class);
        } catch (Exception e) {
            log.error("Não foi possível processar o JSON. Tente novamente.");
        }

        return shares.length > 0 ? Arrays.asList(shares) : Collections.emptyList();
    }

    private String taxesToJson(List<Tax> shares) {
        String jsonOut = "";

        try {
            jsonOut = objectMapper.writeValueAsString(shares);
        } catch (Exception e) {
            log.error("Não foi possível converter o Objeto Tax para JSON. Tente novamente.");
        }

        return jsonOut;
    }
}