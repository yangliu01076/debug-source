package org.example;

import org.example.entity.TUserDO;
import org.example.mapper.TUserMapper;
import org.example.service.TUserService;
import org.example.spring.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author duoyian
 * @date 2026/6/17
 */
@SpringBootTest(classes = SpringBootApp.class)
public class TUserServiceTest {
    @Resource
    private TUserService tUserService;

    @Resource
    private TUserMapper tUserMapper;

    @Resource
    private DataSource dataSource;

    @Resource
    private HelloService helloService;

    @Test
    public void testQuery() {
        // ⭐ 断点 1：打在这里，F8 单步走，看 Mapper 代理对象如何工作
        TUserDO user = tUserService.getUser(1L);
        System.out.println("查询结果: " + user);
    }

    @Test
    public void testInsertWithTransaction() {
        TUserDO newUser = new TUserDO();
        newUser.setName("王五");
        newUser.setAge(-1); // 故意制造异常数据

        try {
            // ⭐ 断点 2：打在这里，深入看 @Transactional 如何开启事务，异常时如何回滚
            tUserService.createUser(newUser);
        } catch (Exception e) {
            System.out.println("捕获到异常: " + e.getMessage());
        }
    }

    @Test
    public void testConnection() throws SQLException {
        System.out.println("数据源类型: " + dataSource.getClass());
        Connection conn = dataSource.getConnection();
        System.out.println("数据库连接成功: " + conn);
        conn.close();
    }

    @Test
    public void testHelloService() {
        System.out.println(helloService.sayHello());
    }
}
