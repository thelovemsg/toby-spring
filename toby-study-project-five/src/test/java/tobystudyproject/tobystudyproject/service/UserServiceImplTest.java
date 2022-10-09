package tobystudyproject.tobystudyproject.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import tobystudyproject.tobystudyproject.Level;
import tobystudyproject.tobystudyproject.MockMailSender;
import tobystudyproject.tobystudyproject.User;
import tobystudyproject.tobystudyproject.beanstudy.TxProxyFactoryBean;
import tobystudyproject.tobystudyproject.dao.MockUserDao;
import tobystudyproject.tobystudyproject.dao.UserDao;
import tobystudyproject.tobystudyproject.proxy.TransactionHandler;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static tobystudyproject.tobystudyproject.service.UserServiceImpl.*;
import static tobystudyproject.tobystudyproject.service.UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER;
import static tobystudyproject.tobystudyproject.service.UserServiceImpl.MIN_RECOMMEND_FOR_GOLD;

@ExtendWith(SpringExtension.class) // (JUnit5)
@ContextConfiguration(locations="/applicationContext.xml")
class UserServiceImplTest {
    @Autowired
    ApplicationContext context;
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;
    @Autowired
    UserServiceImpl userServiceImpl;
    List<User> users;
    @Autowired
    DataSource dataSource;

    @Autowired
    MailSender mailSender;

    @Autowired
    PlatformTransactionManager transactionManager;

    @BeforeEach
    public void setUp(){
        users = Arrays.asList(
                new User("thelovemsg1","thelovemsg1","thelovemsg1", Level.BASIC,  0,MIN_LOGCOUNT_FOR_SILVER-1,"thelovemsg1@naver.com"),
                new User("thelovemsg2","thelovemsg2","thelovemsg2", Level.BASIC,  0,MIN_LOGCOUNT_FOR_SILVER,"thelovemsg3@naver.com"),
                new User("thelovemsg3","thelovemsg3","thelovemsg3", Level.SILVER,  MIN_RECOMMEND_FOR_GOLD-1, 60,"thelovemsg3@naver.com"),
                new User("thelovemsg4","thelovemsg4","thelovemsg4", Level.SILVER,  MIN_RECOMMEND_FOR_GOLD, 60,"thelovemsg4@naver.com"),
                new User("thelovemsg5","thelovemsg5","thelovemsg5", Level.GOLD,  MIN_RECOMMEND_FOR_GOLD, Integer.MAX_VALUE ,"thelovemsg5@naver.com")
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

        userServiceImpl.add(userWithLevel);
        userServiceImpl.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertThat(userWithLevelRead.getLevel()).isEqualTo(userWithLevelRead.getLevel());
        assertThat(userWithoutLevelRead.getLevel()).isEqualTo(Level.BASIC);
    }

    @Test
    public void upgradeLevels() throws Exception {
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        MockUserDao mockUserDao = new MockUserDao(this.users);
        userServiceImpl.setMockUserDao(mockUserDao);

        MockMailSender mockMailSender = new MockMailSender();
        userServiceImpl.setMailSender(mockMailSender);

        userServiceImpl.upgradeLevels();

        List<User> updated = mockUserDao.getUpdated();
        assertThat(updated.size()).isEqualTo(2);
        checkUserAndLevel(updated.get(0), "thelovmsg1", Level.SILVER);
        checkUserAndLevel(updated.get(1), "thelovmsg2", Level.GOLD);

        List<String> request = mockMailSender.getRequests();
        assertThat(request.size()).isEqualTo(2);
        assertThat(request.get(0)).isEqualTo(users.get(1).getEmail());
        assertThat(request.get(1)).isEqualTo(users.get(3).getEmail());
    }

    /*@Test
    public void mockUpgradeLevels() throws Exception {
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        UserDao mockUserDao = mock(UserDao.class);

        mock(mockUserDao.getAll()).thenReturn(this.users);
        userServiceImpl.setUserDao(mockUserDao);

        MailSender mockMailSender = mock(MailSender.class);
        userServiceImpl.upgradeLevels();

        verify(mockUserDao, times(2)).update(any(User.class));
        verify(mockUserDao, times(2)).update(any(User.class));
        verify(mockUserDao).update(users.get(1));
        assertThat(users.get(3).getLevel()).isEqualTo(Level.GOLD);

        ArgumentCaptor<SimpleMailMessage> mailMessageArg = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mockMailSender, times(2)).send(mailMessageArg.capture());
        List<SimpleMailMessage> mailMessages = mailMessageArg.getAllValues();
        assertThat(mailMessages.get(0).getTo()[0]).isEqualTo(users.get(1).getEmail());
        assertThat(mailMessages.get(0).getTo()[0]).isEqualTo(users.get(3).getEmail());
    }*/

    private void checkUserAndLevel(User updated, String expectedId, Level level) {
        assertThat(updated.getId()).isEqualTo(expectedId);
        assertThat(updated.getLevel()).isEqualTo(level);
    }

    @Test
    public void upgradeAllOrNothing() throws Exception{
        TestUserService testUserService = new TestUserService(users.get(3).getId());
        testUserService.setUserDao(userDao);
        testUserService.setMailSender(mailSender );

        UserServiceTx txUserService = new UserServiceTx();
        txUserService.setTransactionManager(transactionManager);
        txUserService.setUserService(testUserService);

        userDao.deleteAll();
        for (User user : users) userDao.add(user);

        try {
            txUserService.upgradeLevels();
            fail("TestUserServiceException expected");
        } catch(UserServiceImpl.TestUserService.TestUserServiceException e){
        }

        checkLevelUpgrade(users.get(1), false);
    }

    @Test
    @DirtiesContext
    public void upgradeAllOrNothingDProxy() throws Exception{
        TestUserService testUserService = new TestUserService(users.get(3).getId());
        testUserService.setUserDao(userDao);
        testUserService.setMailSender(mailSender);

        TxProxyFactoryBean txProxyFactoryBean = context.getBean("&userService", TxProxyFactoryBean.class);

        txProxyFactoryBean.setTarget(testUserService);
        UserService txUserService = (UserService) txProxyFactoryBean.getObject();
        userDao.deleteAll();
        for (User user : users) userDao.add(user);

        try {
            txUserService.upgradeLevels();
            fail("TestUserServiceException expected");
        } catch(UserServiceImpl.TestUserService.TestUserServiceException e){
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