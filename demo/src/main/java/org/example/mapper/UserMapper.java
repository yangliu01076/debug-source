package org.example.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.User;

/**
 * @author duoyian
 * @date 2026/3/20
 */
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User selectById(Integer id);
}
