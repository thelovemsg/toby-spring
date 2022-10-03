package tobystudyproject.tobystudyproject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobystudyproject.tobystudyproject.dao.UserDao;
import tobystudyproject.tobystudyproject.service.UserService;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class) // (JUnit5)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserDaoTest {

    @Autowired
    ApplicationContext context;
    @Autowired
    UserService userService;
    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp(){
        this.dao = context.getBean("userDao", UserDao.class);
        this.user1 = new User("thelovemsg1","thelovemsg1","thelovemsg1", Level.BASIC, 1,0);
        this.user2 = new User("thelovemsg2","thelovemsg2","thelovemsg2", Level.SILVER, 55,10);
        this.user3 = new User("thelovemsg3","thelovemsg3","thelovemsg3", Level.GOLD, 100,40);
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        System.out.println(user1.getId());
        User userget1 = dao.get(user1.getId());
        checkSameUser(userget1, user1);

        User userget2 = dao.get(user2.getId());
        checkSameUser(userget2, user2);
    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        dao.add(user3);
        assertThat(dao.getCount()).isEqualTo(3);
    }

    @Test
    public void getAll() throws SQLException, ClassNotFoundException {
        dao.deleteAll();

        dao.add(user1);
        List<User> users1 = dao.getAll();
        Assertions.assertThat(users1.size()).isEqualTo(1);
        checkSameUser(this.user1, users1.get(0));

        dao.add(this.user2);
        List<User> users2 = dao.getAll();
        Assertions.assertThat(users2.size()).isEqualTo(2);
        checkSameUser(this.user1, users2.get(0));
        checkSameUser(this.user2, users2.get(1));

        dao.add(user3);
        List<User> users3 = dao.getAll();
        Assertions.assertThat(users3.size()).isEqualTo(3);
        checkSameUser(this.user1, users3.get(0));
        checkSameUser(this.user2, users3.get(1));
        checkSameUser(this.user3, users3.get(2));
    }

    @Test
    public void update() {
        dao.deleteAll();

        dao.add(user1);
        dao.add(user2);

        user1.setName("shame");
        user1.setPassword("shame");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);
        dao.update(user1);

        User user1update = dao.get(user1.getId());
        checkSameUser(user1,user1update);

        User user2same = dao.get(user2.getId());
        checkSameUser(user2, user2same);
    }

    @Test
    public void bean() {
        assertThat(this.userService).isNotNull();
    }

    private void checkSameUser(User user1, User user) {
        assertThat(user1.getId()).isEqualTo(user.getId());
        assertThat(user1.getName()).isEqualTo(user.getName());
        assertThat(user1.getPassword()).isEqualTo(user.getPassword());
        assertThat(user1.getLevel()).isEqualTo(user.getLevel());
        assertThat(user1.getLogin()).isEqualTo(user.getLogin());
        assertThat(user1.getRecommend()).isEqualTo(user.getRecommend());
    }


}
