package com.epam.papaya.bank.services.port.out;

import com.epam.papaya.bank.services.domain.BankClient;
import com.epam.papaya.bank.services.domain.Prediction;
import com.epam.papaya.rrmistarter.Adapter;
import com.epam.papaya.rrmistarter.annotations.AdaptTo;
import com.epam.papaya.rrmistarter.annotations.AdaptToRemote;
import org.springframework.http.HttpMethod;

/**
 * @author Evgeny Borisov
 */
public interface PredictionServicePort extends Adapter {

//        @AdaptTo(ifc="predictionServiceImpl",methodName="predict")
    @AdaptToRemote(method = HttpMethod.POST, endpoint = "predict", serviceName = "it will be relevant only with eurika, for now this parameter ignored")
    Prediction predict(BankClient bankClient);
}


