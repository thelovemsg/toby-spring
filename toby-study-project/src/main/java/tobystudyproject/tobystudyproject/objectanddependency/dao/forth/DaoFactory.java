package tobystudyproject.tobystudyproject.objectanddependency.dao.forth;


import tobystudyproject.testrefactoring.dao.first.ConnectionMaker;

public class DaoFactory {
    public UserDao userDao() {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }
}
