package com.michael.saas.admin.common.queue;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    private static final Logger logger = Logger.getLogger(Receiver.class);

    private CountDownLatch latch;


    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 消息处理
     * @param message
     */
    public void objectMessage(String message) {

        QueueTemplate queueTemplate = JSON.parseObject(message,QueueTemplate.class);


        latch.countDown();
    }


}