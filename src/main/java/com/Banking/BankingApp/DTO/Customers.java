package com.Banking.BankingApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customers {

    String custName;

    String accNO;

    String bankName;

    String branch;

    String avlBal;
}
