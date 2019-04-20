package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.MessageService;
import com.example.hugh.life.api.result.MessageResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.PageResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.commmon.util.BeanUtil;
import com.example.hugh.life.commmon.util.UUIDUtil;
import com.example.hugh.life.controller.arg.SendMessageArg;
import com.example.hugh.life.dao.api.MessageDao;
import com.example.hugh.life.dao.entity.MessageEntity;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDao messageDao;

    @Override
    public ModelResult add(SendMessageArg arg) {
        MessageEntity newMessage = BeanUtil.copy(arg, MessageEntity.class);
        newMessage.setId(UUIDUtil.getUUID());
        newMessage.setUid("0a6a4fac0f2845708c5bfc1be8a25b7b");

        if(!messageDao.addMessage(newMessage)){
            log.warn("MessageServiceImpl.add arg:{} newMessage:{}", arg, newMessage);
            return new ModelResult(SHErrorCode.ADD_MESSAGE_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult<PageResult<MessageResult>> list() {

        List<MessageEntity> messageList = messageDao.listMessages();
        List<MessageResult> messageResults = Lists.newArrayList();
        PageResult pageResult = new PageResult();
        if(CollectionUtils.isNotEmpty(messageList)) {
            for (MessageEntity message : messageList) {
                MessageResult result = BeanUtil.copy(message, MessageResult.class);
                messageResults.add(result);
            }
            pageResult.setPageNum(1);
            pageResult.setPageSize(10);
        }else {
            pageResult.setPageNum(1);
            pageResult.setPageSize(0);
        }
        pageResult.setResult(messageResults);
        return new ModelResult<PageResult<MessageResult>>(SHErrorCode.SUCCESS, pageResult);
    }
}
