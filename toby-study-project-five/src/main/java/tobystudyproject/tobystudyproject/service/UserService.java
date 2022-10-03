package tobystudyproject.tobystudyproject.service;

import tobystudyproject.tobystudyproject.Level;
import tobystudyproject.tobystudyproject.User;
import tobystudyproject.tobystudyproject.dao.UserDao;

import java.util.List;

public class UserService implements UserLevelUpgradePolicy{

    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void upgradeLevels(){
        List<User> users=  userDao.getAll();
        for(User user : users){
            if(canUpgradeLevel(user))
                upgradeLevel(user);
        }
    }

    @Override
    public void upgradeLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);
    }

    @Override
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

    static class TestUserService extends UserService{
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


