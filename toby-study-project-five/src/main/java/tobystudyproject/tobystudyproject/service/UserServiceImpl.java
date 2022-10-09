package tobystudyproject.tobystudyproject.service;

import org.springframework.mail.SimpleMailMessage;
import tobystudyproject.tobystudyproject.Level;
import tobystudyproject.tobystudyproject.User;
import tobystudyproject.tobystudyproject.dao.MockUserDao;
import tobystudyproject.tobystudyproject.dao.UserDao;

import java.util.List;

public class UserServiceImpl implements UserService{
    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;

    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    MockUserDao mockUserDao;

    UserDao userDao;

    public void setMockUserDao(MockUserDao mockUserDao){
        this.mockUserDao = mockUserDao;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void upgradeLevels(){
        List<User> users=  mockUserDao.getAll();
        for(User user : users){
            if(canUpgradeLevel(user))
                upgradeLevel(user);
        }
    }

    protected void upgradeLevel(User user) {
        user.upgradeLevel();
        mockUserDao.update(user);
        sendUpgradeEmail(user);
    }

    private void sendUpgradeEmail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("useradmin@ksug.org");
        mailMessage.setSubject("Upgrade 안내");
        mailMessage.setTo("사용자님의 등급이 " + user.getLevel().name());

        mailSender.send(mailMessage);
    }

    public boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch(currentLevel) {
            case BASIC : return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
            case SILVER : return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
            case GOLD : return false;
            default : throw new IllegalArgumentException("Unknown level : " + currentLevel);
        }
    }

    public void add(User user) {
        if (user.getLevel() == null) user.setLevel(Level.BASIC);
        userDao.add(user);
    }

    static class TestUserService extends UserServiceImpl {
        private String id;
        TestUserService(String id){
            this.id = id;
        }

        @Override
        public void upgradeLevel(User user) {
            if (user.getId().equals(this.id)) throw new TestUserServiceException();
            super.upgradeLevel(user);
        }

        public class TestUserServiceException extends RuntimeException {
        }
    }
}


