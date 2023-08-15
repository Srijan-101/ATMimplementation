package com.progressoft.induction.atm.Impl;


import com.progressoft.induction.atm.ATM;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.BankingSystem.*;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ATMImpl implements ATM {
    private final BankingSystemImpl bankingSystem=new BankingSystemImpl();
    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {
        if (bankingSystem.sumOfMoneyInAtm().compareTo(amount) < 0) {
            throw new NotEnoughMoneyInATMException("ATM does not have enough money.");
        } else {
            BigDecimal getBalance = checkBalance(accountNumber);
            if (getBalance.compareTo(amount) >= 0) {
                bankingSystem.debitAccount(accountNumber, amount);
                getBalance = checkBalance(accountNumber);
                List<Banknote> banknotes = calculateBanknotes(amount);
                return banknotes;
            } else {
                throw new InsufficientFundsException("Insufficient balance");
            }
        }
    }


    private List<Banknote> calculateBanknotes(BigDecimal amount) {
        List<Banknote> banknotesToDispense = new ArrayList<>();
        BigDecimal remainingAmount = amount;
        while (remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
            if (remainingAmount.compareTo(new BigDecimal("50")) >= 0) {
                banknotesToDispense.add(Banknote.FIFTY_JOD);
                remainingAmount = remainingAmount.subtract(new BigDecimal("50"));
            } else if (remainingAmount.compareTo(new BigDecimal("20")) >= 0) {
                banknotesToDispense.add(Banknote.TWENTY_JOD);
                remainingAmount = remainingAmount.subtract(new BigDecimal("20"));
            } else if (remainingAmount.compareTo(new BigDecimal("10")) >= 0) {
                banknotesToDispense.add(Banknote.TEN_JOD);
                remainingAmount = remainingAmount.subtract(new BigDecimal("10"));
            } else if (remainingAmount.compareTo(new BigDecimal("5")) >= 0) {
                banknotesToDispense.add(Banknote.FIVE_JOD);
                remainingAmount = remainingAmount.subtract(new BigDecimal("5"));
            } else {
                break;
            }
        }
        return banknotesToDispense;
    }


    @Override
    public BigDecimal checkBalance(String accountNumber) {
        return bankingSystem.getAccountBalance(accountNumber);
    }
}
