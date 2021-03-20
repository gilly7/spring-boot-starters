package org.shield.admin.service;

import org.shield.admin.vo.TokenVo;

/**
 * @author zacksleo@gmail.com
 */
public interface TokenService<T> {

    /**
     * 创建 Token
     *
     * @param form
     * @return
     * @throws Exception
     */
    TokenVo create(T form) throws Exception;
}
