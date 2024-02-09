package com.Banking.BankingApp.Controller;

import com.Banking.BankingApp.DTO.CustomerReq;
import com.Banking.BankingApp.DTO.Customers;
import com.Banking.BankingApp.DTO.FetchBalInfo;
import com.Banking.BankingApp.DTO.TransactionReq;
import com.Banking.BankingApp.Service.BankService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@Log4j2
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService)
    {
        this.bankService=bankService;
    }

    @GetMapping (value ="/fetchBalance/{accNO}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FetchBalInfo> fetchBalance(@PathVariable String accNO,
                                                    @RequestBody CustomerReq customerReq)
    {
        return new ResponseEntity<>(bankService.fetchBalInfo(accNO , customerReq),HttpStatus.OK);
    }

    @PostMapping( value ="/newCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customers>> newCustomer(@RequestBody CustomerReq customerReq)
    {
        return new ResponseEntity<>(bankService.createNewCustomer(customerReq),HttpStatus.OK);
    }

    @PostMapping( value ="updateCustomer/{accNO}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customers>> updateCustomer(@PathVariable String accNO,
                                                    @RequestBody CustomerReq customerReq)
    {
        return new ResponseEntity<>(bankService.updateCustomer(accNO , customerReq),HttpStatus.OK);

    }
    @PostMapping( value = "/debitTransaction", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FetchBalInfo> debitTransaction(/*@PathVariable String debitAmt ,*/@RequestBody TransactionReq transactionReq)
    {
        return new ResponseEntity<>(bankService.debitTransaction(transactionReq),HttpStatus.OK);
    }

}
