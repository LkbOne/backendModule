package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.MessageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {
    boolean addMessage(MessageEntity messageEntity);

    List<MessageEntity> listMessages();
}
