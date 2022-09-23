package com.decagon.rewardyourteacherapi.serviceImpl;


import com.decagon.rewardyourteacherapi.service.WalletService;
import com.decagon.rewardyourteacherapi.util.InitializeTransactionRequest;
import com.decagon.rewardyourteacherapi.util.InitializeTransactionResponse;
import com.decagon.rewardyourteacherapi.util.VerifyTransaction;
import com.decagon.rewardyourteacherapi.util.VerifyTransactionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Transactional
@AllArgsConstructor
public class PaystackTransactionService {

    @Autowired
    WalletService walletService;

    public InitializeTransactionResponse initTransaction(InitializeTransactionRequest request) throws Exception {
        InitializeTransactionResponse initializeTransactionResponse;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            // convert transaction to json then use it as a body to post json
            Gson gson = new Gson();
            // add paystack chrges to the amount
            StringEntity postingString = new StringEntity(gson.toJson(request));
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost("https://api.paystack.co/transaction/initialize");
            post.setEntity(postingString);
            post.addHeader("Content-type", "application/json");
            post.addHeader("Authorization", "Bearer sk_test_4eca7780fd55f4831d30f722d67e03ee49e8278d");
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            } else {
                throw new AuthenticationException("Error Occurred while initializing transaction");
            }
            ObjectMapper mapper = new ObjectMapper();
            initializeTransactionResponse = mapper.readValue(result.toString(), InitializeTransactionResponse.class);
            VerifyTransaction verifyTransaction = new VerifyTransaction(initializeTransactionResponse.getData().getReference(),
                    request.getEmail(), request.getAmount(), walletService);
            executor.execute(verifyTransaction);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Failure initializaing paystack transaction");
        }
//        VerifyTransactionResponse payStackVerifyTransactionResponse = verifyTransaction(initializeTransactionResponse.getData().getReference());
//        if(payStackVerifyTransactionResponse == null){
//            throw new Exception("Failure initializaing paystack transaction");
//        }

        return initializeTransactionResponse;
    }

//Verify transaction
//    public static VerifyTransactionResponse verifyTransaction(String reference) throws Exception {
//
//        VerifyTransactionResponse payStackResponse;
//        System.out.println(reference);
//
//        try {
//
//            HttpClient client = HttpClientBuilder.create().build();
//            HttpGet request = new HttpGet("https://api.paystack.co/transaction/verify/" + reference);
//            request.addHeader("Content-type", "application/json");
//            request.addHeader("Authorization", "Bearer sk_test_4eca7780fd55f4831d30f722d67e03ee49e8278d");
//
//            StringBuilder result = new StringBuilder();
//
//            HttpResponse response = client.execute(request);
//
//            if (response.getStatusLine().getStatusCode() == 200) {
//                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//
//                String line;
//                while ((line = rd.readLine()) != null) {
//                    result.append(line);
//                }
//            } else {
//                throw new Exception("Error Occurred while connecting to pay-stack url");
//            }
//
//            ObjectMapper mapper = new ObjectMapper();
//
//            try {
//                payStackResponse = mapper.readValue(result.toString(), VerifyTransactionResponse.class);
//
//
//                if (payStackResponse.getStatus().equals("success"))
//                    return payStackResponse;
//
//            } catch (JsonProcessingException e) {
//                System.err.println("You've already made payment or An error occurred while verifying payment ");
//                return null;
//            }
//
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new Exception("Internal server error");
//        }
//
//        return payStackResponse;
//    }

}
