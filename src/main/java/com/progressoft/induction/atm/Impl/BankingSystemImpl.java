package com.progressoft.induction.atm.Impl;

import com.progressoft.induction.atm.BankingSystem;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class BankingSystemImpl implements BankingSystem {
   Map<String, BigDecimal> accountBalanceMap = new HashMap<String, BigDecimal>();
   EnumMap<Banknote,Integer> atmCashMap = new EnumMap<>(Banknote.class);

    public BankingSystemImpl() {
        atmCashMap.put(Banknote.FIFTY_JOD,10);
        atmCashMap.put(Banknote.TWENTY_JOD,20);
        atmCashMap.put(Banknote.TEN_JOD,100);
        atmCashMap.put(Banknote.FIVE_JOD,100);

        accountBalanceMap.put("123456789", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("111111111", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("222222222", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("333333333", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("444444444", BigDecimal.valueOf(1000.0));
    }

    public BigDecimal sumOfMoneyInAtm(){
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal balance : accountBalanceMap.values()) {
            total = total.add(balance);
        }
        return total;
    }


    @Override
    public BigDecimal getAccountBalance(String accountNumber){
        if(accountBalanceMap.containsKey(accountNumber)){
            return accountBalanceMap.get(accountNumber);
        }
        else throw new AccountNotFoundException("Account not found");
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {
            if(accountBalanceMap.containsKey(accountNumber)){
                BigDecimal Newbalance = accountBalanceMap.get(accountNumber).subtract(amount);
                accountBalanceMap.put(accountNumber,Newbalance);
            }else {
                throw new AccountNotFoundException("Account not found");
            }
        }
    }

