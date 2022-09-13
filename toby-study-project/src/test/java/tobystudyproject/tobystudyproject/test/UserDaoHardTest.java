package tobystudyproject.tobystudyproject.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tobystudyproject.testrefactoring.dao.first.UserDao;
import tobystudyproject.tobystudyproject.objectanddependency.dao.User;

import java.sql.SQLException;

public class UserDaoHardTest {
    @Test
    public void commonUserTest() throws SQLException, ClassNotFoundException {

        UserDao userDao = new UserDao();
        User build = User.builder().id("test").password("test").name("test").build();
        userDao.add(build);

        User build2 = User.builder().id("test").password("test1").name("test").build();
        userDao.get(build2.getId());

        Assertions.assertEquals(build.getId(), build2.getId());
//        Assertions.assasertEquals(build.getPassword(), build2.getPassword()).a;
        Assertions.assertThrows(ClassNotFoundException.class, () -> build.getPassword().equals(build2.getPassword()));
    }
}
