package org.example;

import org.example.entity.TUserDO;
import org.example.mapper.TUserMapper;
import org.example.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.util.JsonUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // --- 断点打在这里！准备进入源码！ ---
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

//        String param = "[\"1\",\"2\",\"3\"]";;
        String param = "[1,2,3]";
        System.out.println(null instanceof List);
        List cast = List.class.cast(null);
//        List<String> stringList = JsonUtils.fromJson(param, new TypeReference<List<String>>() {});
        List<String> stringList = JsonUtils.fromJson(param, List.class);
        String id = stringList.get(0);
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
//            Class<?> aClass = id.getClass();
            Object o = new Object();
            List<TUserDO> listByName = mapper.selectListByName((String) o);
//            List<TUserDO> listByName = mapper.selectListByName(stringList.get(0));
//            List<TUserDO> listByName = mapper.selectListByNameList(stringList);
            for (TUserDO tUserDO : listByName) {
                System.out.println(tUserDO);
            }
//            TUserDO user = mapper.selectById(id);
//            System.out.println(user);
        }
    }
}
