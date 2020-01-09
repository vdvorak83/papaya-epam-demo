package com.epam.papaya.bank.services;

import com.epam.papaya.bank.services.domain.BankClient;
import org.springframework.stereotype.Service;

/**
 * @author Evgeny Borisov
 */
public interface BankService {
    String loan(BankClient client, int amount);
}
