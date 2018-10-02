import entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by Lembre on 2018.10.2
 */
public class FirstMyBatis {

    private SqlSessionFactory sqlSqlSessFactory;

    //创建工厂
    @Before
    public void init() throws IOException {
        //配置文件
        String resource = "Configuration.xml";
        //加载配置文件到输入流中
        //InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂
        sqlSqlSessFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    //根据id查找用户
    @Test
    public void testFindUserById(){
        //通过sqlSqlSessFactory创建sqlSession
        SqlSession sqlSession = sqlSqlSessFactory.openSession();
        User user = null;
        //通过sqlSession操作数据库
        //第一个参数为statement的位置，等于namespace+statement的id
        //第二个参数，传给占位符大的参数
        user = sqlSession.selectOne("test.findUserById", 1);
        System.out.println(user);
        sqlSession.close();
    }

    //测试根据name查询用户（得到多条记录）
    @Test
    public void testFindUserByName(){

        //通过sqlSqlSessFactory创建sqlSession
        SqlSession sqlSession = sqlSqlSessFactory.openSession();

        List<User> list = null;
        list = sqlSession.selectList("test.findUserByName","y");
        sqlSession.close();
        System.out.println(list.get(0).getUsername());

    }

    @Test
    public void testInsertUser(){
        SqlSession sqlSession = sqlSqlSessFactory.openSession();

        User user = new User();
        user.setUsername("codingLembre");
        user.setAddress("hubei");
        user.setBirthday(new Date());
        user.setSex("1");

        sqlSession.insert("test.insertUser",user);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(user.getId());
    }

    @Test//测试自增主键返回
    public void testInsertUser1(){
        SqlSession sqlSession = sqlSqlSessFactory.openSession();

        User user = new User();
        user.setUsername("coding");
        user.setAddress("hunan");
        user.setBirthday(new Date());
        user.setSex("2");

        sqlSession.insert("test.insertUserByKey",user);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(user.getId());
    }

    /*@Test//测试UUID,无法演示
    public void testInsertUser2(){
        SqlSession sqlSession = sqlSqlSessFactory.openSession();

        User user = new User();
        user.setUsername("coding");
        user.setAddress("shanxi");
        user.setBirthday(new Date());
        user.setSex("3");

        sqlSession.insert("test.insertUserByUUID",user);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(user.getId());
    }*/

}