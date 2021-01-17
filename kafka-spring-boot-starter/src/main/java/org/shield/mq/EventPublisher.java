package org.shield.mq;

/**
 * 发布事件
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
public interface EventPublisher {

    /**
     * 发布事件
     *
     * @param event
     */
    public void publish(Object event);

    /**
     * 发布事件
     *
     * @param topic
     * @param event
     */
    public void publish(String topic, Object event);
}
