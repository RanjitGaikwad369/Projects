package com.Banking.BankingApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchBalInfo {

    String custName;

    String bankName;

    String branch;

    String avlBal;

}
