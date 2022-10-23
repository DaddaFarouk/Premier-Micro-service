package com.farouk.bankaccountservice.services;

import com.farouk.bankaccountservice.dtos.BankAccountRequestDTO;
import com.farouk.bankaccountservice.dtos.BankAccountResponseDTO;
import com.farouk.bankaccountservice.entities.BankAccount;
import com.farouk.bankaccountservice.enums.AccountType;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
