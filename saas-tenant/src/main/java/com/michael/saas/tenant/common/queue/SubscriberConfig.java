package com.michael.saas.tenant.common.queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@Configuration
public class SubscriberConfig {

    /**
     * 创建连接工厂
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter,new PatternTopic("AdminQueue"));
        return container;
    }
 
    /**
     * 绑定消息监听者和接收监听的方法
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(Receiver receiver){
        return new MessageListenerAdapter(receiver,"objectMessage");
    }
 
    /**
     * 注册订阅者
     * @param latch
     * @return
     */
    @Bean
    public Receiver receiver(CountDownLatch latch){
        return new Receiver(latch);
    }
 
    /**
     * 计数器，用来控制线程
     * @return
     */
    @Bean
    public CountDownLatch latch(){
        return new CountDownLatch(1);//指定了计数的次数 1
    }
}