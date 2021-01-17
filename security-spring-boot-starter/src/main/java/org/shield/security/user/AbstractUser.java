package org.shield.security.user;

/**
 * @author zacksleo@gmail.com
 */
public class AbstractUser implements User {

    private Long uid;

    private String username;

    private String userId;

    private String name;

    private String catalog;

    public Long getUid() {
      return uid;
    }

    public void setUid(Long uid) {
      this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalog() {
        return catalog;
    }
}
