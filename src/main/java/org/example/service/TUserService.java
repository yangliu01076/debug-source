package org.example.service;

import org.example.entity.TUserDO;

/**
 * @author duoyian
 * @date 2026/6/17
 */
public interface TUserService {
    TUserDO getUser(Long id);
    void createUser(TUserDO user);
}
