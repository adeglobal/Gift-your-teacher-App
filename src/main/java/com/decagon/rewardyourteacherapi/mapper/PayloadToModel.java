package com.decagon.rewardyourteacherapi.mapper;

import com.decagon.rewardyourteacherapi.model.Notification;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.NotificationDTO;
import com.decagon.rewardyourteacherapi.payload.TransactionDTO;
import com.decagon.rewardyourteacherapi.payload.UserDTO;

public class PayloadToModel {

    public static User mapRequestToUser(UserDTO request){
       User user  = new User();
       if(request.getName() != null){
           user.setName(request.getName());
       }

       if(request.getEmail() != null){
           user.setEmail(request.getEmail());
       }
       if(request.getPassword() != null){
           user.setPassword(request.getPassword());
       }
       if(request.getRole() != null){
           user.setRole(request.getRole());
       }
       if(request.getImageUrl() != null){
           user.setProfileImage(request.getImageUrl());
       }
//        if(request.getPhoneNumber() != null){
//            user.setPhoneNumber(request.getPhoneNumber());
//        }
       return user;
    }

    public static User mapDTOToUser(UserDTO userDTO){

       return new User( userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
    }

    public static  UserDTO mapUserToDTO(User user){
        System.out.println(user.getId());
        return  new UserDTO(user.getId(),user.getName(),user.getProfileImage(),user.getWallet());
    }

    public static NotificationDTO mapNotToDTO(Notification notification){
        return  new NotificationDTO(notification.getId(), notification.getMessage(), notification.getCreatedAt());
    }

    public static TransactionDTO mapTransactToDTO(Transaction transaction){
        return  new TransactionDTO(mapUserToDTO(transaction.getSender()), mapUserToDTO(transaction.getRecipient()),
                transaction.getAmount(), transaction.getTransactionDate());
    }

    public static Notification  NotificationMapper(Notification notification){
        Notification notification1 = new Notification();
        if (notification != null){
            notification1.setMessage(notification.getMessage());
        }

        return  notification1;
    }
}


