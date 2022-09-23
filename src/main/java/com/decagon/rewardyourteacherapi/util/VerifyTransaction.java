package com.decagon.rewardyourteacherapi.util;

import com.decagon.rewardyourteacherapi.payload.FundWalletRequestDTO;
import com.decagon.rewardyourteacherapi.service.WalletService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@AllArgsConstructor
public class VerifyTransaction implements Runnable{
    String reference;
    String email;
    BigDecimal amount;

    WalletService walletService;
    int count = 0;
    public VerifyTransaction(String reference, String email, BigDecimal amount, WalletService walletService) {
        this.reference = reference;
        this.email = email;
        this.amount = amount;
        this.walletService = walletService;
    }

    @Override
    public void run() {
        check();
    }

    public void check(){
        VerifyTransactionResponse vert = verifyTransact();
        while(vert  == null && count < 10){
            try{
                System.out.println("its null and i dont have strength...make i sleep");
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            vert  = verifyTransact();
        }
        if(vert !=  null){
            System.out.println("transaction verified");
            FundWalletRequestDTO  fundWalletRequestDTO = new FundWalletRequestDTO(email, amount );
            walletService.fundStudentWallet(fundWalletRequestDTO);
        }
        else{
            System.out.println("no dice");
        }
    }
    VerifyTransactionResponse verifyTransact(){

        VerifyTransactionResponse payStackResponse = null;

        try {

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("https://api.paystack.co/transaction/verify/" + reference);
            request.addHeader("Content-type", "application/json");
            request.addHeader("Authorization", "Bearer sk_test_4eca7780fd55f4831d30f722d67e03ee49e8278d");

            StringBuilder result = new StringBuilder();

            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            } else {
                throw new Exception("Error Occurred while connecting to pay-stack url");
            }

            ObjectMapper mapper = new ObjectMapper();

            try {
                payStackResponse = mapper.readValue(result.toString(), VerifyTransactionResponse.class);
                System.out.println(payStackResponse.getData());

                if (payStackResponse.getData().getStatus().equals("Successful")){
                    return payStackResponse;
                }
                payStackResponse = null;

            } catch (JsonProcessingException e) {
                System.err.println("You've already made payment or An error occurred while verifying payment ");
                return null;
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return payStackResponse;
    }
}
