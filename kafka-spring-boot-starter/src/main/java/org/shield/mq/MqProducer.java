package org.shield.mq;

/**
 * 消息队列发布
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
public interface MqProducer {

    /**
     * 发布消息
     *
     * @param topic
     * @param message
     */
    public void send(String topic, Object message);
}
