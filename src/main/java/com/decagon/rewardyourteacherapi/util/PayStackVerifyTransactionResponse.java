//package com.decagon.rewardyourteacherapi.util;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.springframework.stereotype.Service;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//@Service
//public class PayStackVerifyTransactionResponse extends VerifyTransactionResponse {
//
//    public PayStackVerifyTransactionResponse verifyTransaction(String reference) throws Exception {
//
//        PayStackVerifyTransactionResponse payStackResponse;
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
//
//            } else {
//                throw new Exception("Error Occurred while connecting to pay-stack url");
//            }
//
//            ObjectMapper mapper = new ObjectMapper();
//
//            try {
//                payStackResponse = mapper.readValue(result.toString(), PayStackVerifyTransactionResponse.class);
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
//}