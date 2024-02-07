package com.Banking.BankingApp.Utils;

import com.Banking.BankingApp.DTO.Customers;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Data
public class MockData {

public static List<Customers> custInfo()
{
        List<Customers> custList = new ArrayList<>();
        custList.add(new Customers("Ranjit","12345","SBI","kharghar","23000"));
        custList.add(new Customers("Gauarav","12343","KOTAK","Kurla","2500"));
        custList.add(new Customers("Sandesh","223355","SBI","Nerul","19800"));
        custList.add(new Customers("Appa","1243","HDFC","Pune","23876"));

        return custList;
 }
}
