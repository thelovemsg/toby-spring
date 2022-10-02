package tobystudyproject.tobystudyproject.sectionfive;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import tobystudyproject.testrefactoring.dao.first.UserDao;
import tobystudyproject.tobystudyproject.User;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {
        this.dao = new UserDao();
        this.user1 = new User(Level.BASIC,1, 0, "thelovemsg1", "thelovemsg1", "1234");
        this.user2 = new User(Level.SILVER, 0, 1,"thelovemsg2", "thelovemsg2", "1234");
        this.user3 = new User(Level.GOLD, 0, 1,"thelovemsg3", "thelovemsg3", "1234");
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        dao.add(user1);
        dao.add(user2);
        assertEquals(dao.getCount(), 2);

        User userget1 = dao.get(user1.getId());
        checkSameUser(userget1, user1);

        User userget2 = dao.get(user2.getId());
        checkSameUser(user2, userget2);
    }


    private void checkSameUser(User user1, User user2){
        Assertions.assertThat(user1.getId()).isEqualTo(user2.getId());
        Assertions.assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
        Assertions.assertThat(user1.getName()).isEqualTo(user2.getName());
        Assertions.assertThat(user1.getLevel()).isEqualTo(user2.getLevel());
        Assertions.assertThat(user1.getLogin()).isEqualTo(user2.getLogin());
        Assertions.assertThat(user1.getRecoment()).isEqualTo(user2.getRecoment());
    }
}
