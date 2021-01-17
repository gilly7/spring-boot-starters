package org.shield.security.user;

/**
 * @author zacksleo@gmail.com
 */
public interface User {

    /**
     * 用户主键
     *
     * @return
     */
    public Long getUid();

    /**
     * 设置主键
     *
     * @param uid
     */
    public void setUid(Long uid);

    /**
     * 用户名
     *
     * @return
     */
    public String getUsername();

    /**
     * 设置用户名
     *
     * @param username
     */
    public void setUsername(String username);

    /**
     * 名称
     *
     * @return
     */
    public String getName();

    /**
     * 设置名称
     *
     * @param name
     */
    public void setName(String name);

    /**
     * 用户唯一编号
     *
     * @return
     */
    public String getUserId();

    /**
     * 设置用户编号
     *
     * @param userId
     */
    public void setUserId(String userId);

    /**
     * 用户类别
     *
     * @return
     */
    public String getCatalog();
}
