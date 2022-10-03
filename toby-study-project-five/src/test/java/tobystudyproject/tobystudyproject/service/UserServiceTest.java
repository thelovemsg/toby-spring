package tobystudyproject.tobystudyproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobystudyproject.tobystudyproject.Level;
import tobystudyproject.tobystudyproject.User;
import tobystudyproject.tobystudyproject.dao.UserDao;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static tobystudyproject.tobystudyproject.service.UserService.*;
import static tobystudyproject.tobystudyproject.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static tobystudyproject.tobystudyproject.service.UserService.MIN_RECOMMEND_FOR_GOLD;

@ExtendWith(SpringExtension.class) // (JUnit5)
@ContextConfiguration(locations="/applicationContext.xml")
class UserServiceTest {

    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;
    List<User> users;

    @BeforeEach
    public void setUp(){
        users = Arrays.asList(
                new User("thelovemsg1","thelovemsg1","thelovemsg1", Level.BASIC,  0,MIN_LOGCOUNT_FOR_SILVER-1),
                new User("thelovemsg2","thelovemsg2","thelovemsg2", Level.BASIC,  0,MIN_LOGCOUNT_FOR_SILVER),
                new User("thelovemsg3","thelovemsg3","thelovemsg3", Level.SILVER,  MIN_RECOMMEND_FOR_GOLD-1, 60),
                new User("thelovemsg4","thelovemsg4","thelovemsg4", Level.SILVER,  MIN_RECOMMEND_FOR_GOLD, 60),
                new User("thelovemsg5","thelovemsg5","thelovemsg5", Level.GOLD,  MIN_RECOMMEND_FOR_GOLD, Integer.MAX_VALUE)
        );
    }

    private boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch(currentLevel) {
            case BASIC: return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
            case SILVER: return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
            case GOLD : return false;
            default : throw new IllegalStateException("unknown level : "+ currentLevel);
        }
    }

    @Test
    public void add() {
        userDao.deleteAll();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertThat(userWithLevelRead.getLevel()).isEqualTo(userWithLevelRead.getLevel());
        assertThat(userWithoutLevelRead.getLevel()).isEqualTo(Level.BASIC);


    }

    @Test
    public void upgradeLevels() {
        userDao.deleteAll();
        for (User user : users) {
            userDao.add(user);
        }
        userService.upgradeLevels();

        checkLevelUpgrade(users.get(0), false);
        checkLevelUpgrade(users.get(1), true);
        checkLevelUpgrade(users.get(2), false);
        checkLevelUpgrade(users.get(3), true);
        checkLevelUpgrade(users.get(4), false);
    }

    @Test
    public void upgradeAllOrNothing() {
        UserService testUserService = new TestUserService(users.get(3).getId());
        testUserService.setUserDao(this.userDao);
        userDao.deleteAll();
        for (User user : users) {
            userDao.add(user);
        }
        try {
            testUserService.upgradeLevels();
            fail("TestUserSErviceException expected");
        } catch(UserService.TestUserService.TestUserServiceException e){
        }

        checkLevelUpgrade(users.get(1), false);
    }

    private void checkLevelUpgrade(User user, boolean upgraded) {
        User userUpdate = userDao.get(user.getId());
        if (upgraded) {
            assertThat(userUpdate.getLevel()).isEqualTo(user.getLevel().nextLevel());
        }else{
            assertThat(userUpdate.getLevel()).isEqualTo(user.getLevel());
        }
    }

    private void checkLevel(User user, Level expectedLevel) {
        User userUpdate = userDao.get(user.getId());
        assertThat(userUpdate.getLevel()).isEqualTo(expectedLevel);
    }

}