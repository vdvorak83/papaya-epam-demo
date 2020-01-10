package com.epam.papaya.prediction.controllers;

import com.epam.papaya.prediction.domain.Person;
import com.epam.papaya.prediction.domain.Predict;
import com.epam.papaya.prediction.port.in.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Evgeny Borisov
 */
@RestController
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @PostMapping("/predict")
    public Predict predict(@RequestBody Person person) {
        return predictionService.predict(person);
    }
}
