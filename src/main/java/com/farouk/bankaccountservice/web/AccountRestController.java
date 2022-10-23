package com.farouk.bankaccountservice.web;

import com.farouk.bankaccountservice.dtos.BankAccountRequestDTO;
import com.farouk.bankaccountservice.dtos.BankAccountResponseDTO;
import com.farouk.bankaccountservice.entities.BankAccount;
import com.farouk.bankaccountservice.mappers.AccountMapper;
import com.farouk.bankaccountservice.repositories.BankAccountRepository;
import com.farouk.bankaccountservice.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private final BankAccountRepository bankAccountRepository;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
        return accountService.addAccount(requestDTO);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found",id)));
        if(bankAccount.getBalance()!=null)account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedAt()!=null)account.setCreatedAt(bankAccount.getCreatedAt());
        if(bankAccount.getType()!=null)account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null)account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public void delete(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
