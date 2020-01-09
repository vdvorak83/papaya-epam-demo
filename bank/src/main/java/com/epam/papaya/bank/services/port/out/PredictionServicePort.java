package com.epam.papaya.bank.services.port.out;

import com.epam.papaya.bank.services.domain.BankClient;
import com.epam.papaya.bank.services.domain.Prediction;
import com.epam.papaya.rrmistarter.Adapter;
import com.epam.papaya.rrmistarter.annotations.AdaptTo;

/**
 * @author Evgeny Borisov
 */
public interface PredictionServicePort extends Adapter {

    @AdaptTo(ifc="predictionServiceImpl",methodName="predict")
    Prediction predict(BankClient bankClient);
}


