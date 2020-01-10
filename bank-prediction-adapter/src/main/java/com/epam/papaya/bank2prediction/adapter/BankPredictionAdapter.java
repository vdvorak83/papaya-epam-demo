package com.epam.papaya.bank2prediction.adapter;

import com.epam.papaya.bank.services.domain.BankClient;
import com.epam.papaya.bank.services.domain.Prediction;
import com.epam.papaya.bank.services.port.out.PredictionServicePort;
import com.epam.papaya.prediction.domain.Person;
import com.epam.papaya.prediction.domain.Predict;
import com.epam.papaya.prediction.port.in.PredictionService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Evgeny Borisov
 */
//@Component
public class BankPredictionAdapter implements PredictionServicePort {
    @Autowired
    private PredictionService predictionService;

    @Override
    public Prediction predict(BankClient bankClient) {

        Person person = Person.builder().name(bankClient.getName()).family(bankClient.getFamily()).build();
//        return Prediction.builder().willSurvive(predictionService.predict(person).isWillSurvive()).build();
        return Prediction.builder().willSurvive(true).build();
    }

//    public static void main(String[] args) {
//        ObjectMapper mapper = new ObjectMapper();
//        Person person = Person.builder().age(2).name("Gena").family("Zaytcev").build();
//        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_ABSENT);
//        BankClient bankClient = mapper.convertValue(person, BankClient.class);
//        System.out.println("bankClient = " + bankClient);
//    }
}
