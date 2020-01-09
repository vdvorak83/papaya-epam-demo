package com.epam.papaya.prediction.port.in;

import com.epam.papaya.prediction.domain.Person;
import com.epam.papaya.prediction.domain.Predict;

/**
 * @author Evgeny Borisov
 */
public interface PredictionService {
    Predict predict(Person person);
}
