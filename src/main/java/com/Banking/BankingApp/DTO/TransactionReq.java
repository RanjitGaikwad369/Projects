package com.Banking.BankingApp.DTO;

import lombok.Data;

@Data
public class TransactionReq {

    private String custName;

    private String accNO;

    private String bankName;

    private String branch;

    private String debitAmt;
}
