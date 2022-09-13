package tobystudyproject.tobystudyproject.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobystudyproject.testrefactoring.dao.first.UserDao;
import tobystudyproject.tobystudyproject.objectanddependency.dao.User;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/*
* 처음 add() 에 전달한 User 오브젝트와 get()을 통해 가져오는 User 오브젝트의 값을 비교해서 일치하는지 확인
*
* */
@SpringBootTest
public class UserDaoTest {

    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        this.dao = context.getBean("userDao", UserDao.class);
        this.dao = new UserDao();
        this.user1 = new User("msg1", "msg1", "msg1");
        this.user2 = new User("msg2", "msg2", "msg2");
        this.user3 = new User("msg3", "msg3", "msg3");
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {

        dao.deleteAll();;
        assertEquals(dao.getCount(), 0);

        dao.add(user1);
        dao.add(user2);
        assertEquals(dao.getCount(), 2);

        User userget1 = dao.get(user1.getId());
        assertEquals(userget1.getName(), user1.getName());
        assertEquals(userget1.getPassword(), user1.getPassword());

        User userget2 = dao.get(user2.getId());
        assertEquals(userget2.getName(), user2.getName());
        assertEquals(userget2.getPassword(), user2.getPassword());

    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        // bean으로 등록하고 하려고 하나 테스트로 만든게 너무 많아서 엮여서 번거롭기에, 그냥 직접 호출해서 테스트 중이다.
        // 물론 실전에서는 bean을 등록하고 사용할 것이다.
        User user1 = new User("msg1", "테스트1", "test1");
        User user2 = new User("msg2", "테스트2", "test2");
        User user3 = new User("msg3", "테스트3", "test3");

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        dao.add(user1);
        assertEquals(dao.getCount(), 1);

        dao.add(user2);
        assertEquals(dao.getCount(), 2);

        dao.add(user3);
        assertEquals(dao.getCount(), 3);
    }

    @Test
    public void getUserFailure() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);
        dao.get("test1");

    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);
        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
//        System.out.println(user2.getName());
//        System.out.println(user2.getPassword());
//        System.out.println(user2.getId() + " 조회 성공");
        if(!user.getName().equals(user2.getName())){
            System.out.println("테스트 실패 (name)");
        }else if(!user.getPassword().equals(user2.getPassword())){
            System.out.println("테스트 실패 (password)");
        }else {
            System.out.println("조회 테스트 성공");
        }
    }

}
