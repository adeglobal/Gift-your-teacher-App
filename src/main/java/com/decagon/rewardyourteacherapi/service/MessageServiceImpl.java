package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.Message;
import com.decagon.rewardyourteacherapi.repository.MessageRepository;

public class MessageServiceImpl implements MessageService{
    private MessageRepository messageRepository;

    @Override
    public void messageNotification(Message message){
    message.setRead(false);
        messageRepository.save(message);
    }


}
