package com.michael.saas.admin.common.queue;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QueueService {

    @Resource
    private StringRedisTemplate template;

    /**
     * 发送消息到队列
     * @param method
     * @param object
     */
    @Async
    public void send(String method, Object object){
        QueueTemplate queueTemplate = new QueueTemplate();
        queueTemplate.setMethod(method);
        queueTemplate.setObject(object);
        template.convertAndSend("AdminQueue", JSON.toJSONString(queueTemplate));
    }

}
