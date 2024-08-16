package com.sbjprogramming.BankManagementApplication.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbjprogramming.BankManagementApplication.entity.Account;
import com.sbjprogramming.BankManagementApplication.repo.AccountRepository;
@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository repo;
	
	@Override
	public Account createAccount(Account account) {
		Account account_saved = repo.save(account);
		return account_saved;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account does not exist! ");
		}
		Account account_found = account.get();
		return account_found;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		List<Account> listOfAccounts = repo.findAll();
		return listOfAccounts;
	}

	@Override
	public Account depositAmount(Long accountNumber, Double amount) {
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account Does not Exist!");
		}
		Account accountValue = account.get();
		Double totalBalance = accountValue.getAccount_balance() + amount;
		accountValue.setAccount_balance(totalBalance);
		repo.save(accountValue);
		
		return accountValue;
	}

	@Override
	public Account withdrawAmount(Long accountNumber, Double amount) {
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account Does not Exist!");
		}
		
		Account accountValue = account.get();
		Double totalBalance = accountValue.getAccount_balance();
		if (amount > totalBalance) {
			throw new RuntimeException("Insifficient Funds. Transaction Cannot be Completed!");
		}
		Double newBalance = totalBalance - amount;
		accountValue.setAccount_balance(newBalance);
		repo.save(accountValue);
		
		return accountValue;
	}

	@Override
	public void closeAccount(Long accountNumber) {
		getAccountDetailsByAccountNumber(accountNumber);
		repo.deleteById(accountNumber);
		
	}
	

}
