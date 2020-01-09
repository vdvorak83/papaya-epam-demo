package com.epam.papaya.bank.services;

import com.epam.papaya.bank.services.domain.BankClient;
import com.epam.papaya.bank.services.domain.Prediction;
import com.epam.papaya.bank.services.port.out.PredictionServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Evgeny Borisov
 */
@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private PredictionServicePort predictionServicePort;

    @Override
    public String loan(BankClient client, int amount) {
        Prediction prediction = predictionServicePort.predict(client);
        if (prediction.isWillSurvive()) {
            return "accepted";
        }
        return "rejected";
    }
}
