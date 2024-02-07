package com.Banking.BankingApp.Service;

import com.Banking.BankingApp.DTO.CustomerReq;
import com.Banking.BankingApp.DTO.Customers;
import com.Banking.BankingApp.DTO.FetchBalInfo;
import com.Banking.BankingApp.DTO.TransactionReq;
import com.Banking.BankingApp.Utils.MockData;
import lombok.extern.log4j.Log4j2;
import org.mockito.Mock;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static com.Banking.BankingApp.Utils.MockData.custInfo;

@Service
@Log4j2
public class BankService {
    public FetchBalInfo fetchBalInfo(String accNo, CustomerReq customerReq) {
        log.info("Request received for balance Enquiry with accountNO{}", accNo);
        List<Customers> custList = custInfo();
        FetchBalInfo fetchBalInfo = new FetchBalInfo();

        Iterator<Customers> iterator = custList.iterator();
        int count = 0;

        while (iterator.hasNext()) {
            Customers cust = iterator.next();
            if (customerReq.getAccNO().equals(cust.getAccNO())) {
                log.info("***Customer Found***");
                log.info("Customers available balance is{}", cust.getAvlBal());
                fetchBalInfo.setAvlBal(cust.getAvlBal());
                fetchBalInfo.setBankName(cust.getBankName());
                fetchBalInfo.setBranch(cust.getBranch());
                fetchBalInfo.setCustName(cust.getCustName());
                count++;
            }
        }
        if (count == 0) {
            log.info("No person found with these Acc no");
        }

        return fetchBalInfo;
    }

    public List<Customers> createNewCustomer(CustomerReq customerReq) {

        log.info("Request received to create new customer");
        List<Customers> custList = custInfo();
        log.info("This is customers List{}", custList);

        Customers customers = new Customers();

        customers.setAccNO(customerReq.getAccNO());
        customers.setBankName(customerReq.getBankName());
        customers.setBranch(customerReq.getBranch());
        customers.setCustName(customerReq.getCustName());
        customers.setAvlBal("8000");
        custList.add(customers);
        log.info("This is New List{}", custList);
        return custList;
    }

    public List<Customers> updateCustomer(String accNO, CustomerReq customerReq) {
        log.info("Account number received for update an customer{} ", accNO);

        List<Customers> custList = custInfo();
        int indextoupdate = -1;
        for (int i = 0; i <= custList.size(); i++) {
            if (custList.get(i).getAccNO().equals(customerReq.getAccNO())) {
                indextoupdate = i;
                break;
            }
        }
        if (indextoupdate != -1) {
            Customers c = new Customers();
            c.setAccNO(customerReq.getAccNO());
            c.setCustName(customerReq.getCustName());
            c.setBankName(customerReq.getBankName());
            c.setBranch(customerReq.getBranch());

            custList.set(indextoupdate, c);
            log.info("This is Updated List{}", custList);
        }
        return custList;
    }

    public FetchBalInfo debitTransaction(TransactionReq transactionReq) {
        FetchBalInfo fetchBalInfo = new FetchBalInfo();
        log.info("Request received for Debit transaction with amount{}", transactionReq.getDebitAmt());
        List<Customers> custData = custInfo();
        int i = 0;
        // Iterator<Customers> custInfo = custData.iterator();
        for (Customers cust : custData) {


            if (transactionReq.getAccNO().equals(cust.getAccNO())) {
                try {
                    int amt = Integer.parseInt(transactionReq.getDebitAmt());
                    int avlBalance = Integer.parseInt(cust.getAvlBal());
                    if (amt <= avlBalance) {
                        log.info("This is customers available balance{}", avlBalance);
                        int finalBal = avlBalance - amt;
                        // log.info("This is Customers Final Balance{} ", finalBal);

                        fetchBalInfo.setCustName(transactionReq.getCustName());
                        fetchBalInfo.setBankName(transactionReq.getBankName());
                        fetchBalInfo.setBranch(transactionReq.getBranch());
                        fetchBalInfo.setAvlBal(String.valueOf(finalBal));

                        log.info("This is customer information after debit{}", fetchBalInfo);
                    }

                    else {
                        log.info("Customer has insufficient balance{}{}", cust.getCustName(), avlBalance);
                    }

                } catch (Exception e) {
                    log.error("Exception caught in debit transaction", e);
                }
                i++;
            }

        }
        if (i == 0){
            log.info("No customer found with this account number");
        }

        return fetchBalInfo;
    }
}
