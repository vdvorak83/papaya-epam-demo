package com.epam.papaya.prediction.port.in;

import com.epam.papaya.prediction.domain.Person;
import com.epam.papaya.prediction.domain.Predict;
import org.springframework.stereotype.Service;

/**
 * @author Evgeny Borisov
 */
@Service
public class PredictionServiceImpl implements PredictionService {
    @Override
    public Predict predict(Person person) {
        return Predict.builder().willSurvive(!person.getFamily().toLowerCase().contains("start")).build();
    }
}
