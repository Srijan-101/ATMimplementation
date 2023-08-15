package com.progressoft.induction.atm;

import com.progressoft.induction.atm.Impl.ATMImpl;
import com.progressoft.induction.atm.Impl.BankingSystemImpl;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;

import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String args[]){
       ATMImpl obj = new ATMImpl();
       obj.withdraw("123456789",BigDecimal.valueOf(150));
    }
}
