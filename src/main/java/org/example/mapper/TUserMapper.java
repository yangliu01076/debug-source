package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.TUserDO;

import java.util.List;

/**
 * @author duoyian
 * @date 2026/6/17
 */

public interface TUserMapper {
    TUserDO selectById(Long id);
    void insert(TUserDO user);
    List<TUserDO> selectListByName(String name);
    List<TUserDO> selectListByNameList(List<String> names);
}
