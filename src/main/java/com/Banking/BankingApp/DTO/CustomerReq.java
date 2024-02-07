package com.Banking.BankingApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReq {


    private String custName;

    private String accNO;

    private String bankName;

    private String branch;


}
