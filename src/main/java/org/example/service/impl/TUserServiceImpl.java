package org.example.service.impl;

import org.example.entity.TUserDO;
import org.example.mapper.TUserMapper;
import org.example.service.TUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author duoyian
 * @date 2026/6/17
 */
@Service("tUserService")
public class TUserServiceImpl implements TUserService {
    @Resource
    private TUserMapper tUserMapper;

    @Override
    public TUserDO getUser(Long id) {
        return tUserMapper.selectById(id);
    }

    @Override
    @Transactional
    public void createUser(TUserDO user) {
        tUserMapper.insert(user);
    }
}
