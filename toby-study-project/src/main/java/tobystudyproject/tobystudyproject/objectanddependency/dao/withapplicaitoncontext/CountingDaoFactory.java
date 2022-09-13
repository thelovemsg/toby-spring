package tobystudyproject.tobystudyproject.objectanddependency.dao.withapplicaitoncontext;

import tobystudyproject.testrefactoring.dao.first.ConnectionMaker;
import tobystudyproject.tobystudyproject.objectanddependency.dao.forth.DConnectionMaker;
import tobystudyproject.tobystudyproject.objectanddependency.dao.forth.UserDao;

/**
 *  의존관계
 *
 *  UserDao  => CountingConnectionMaker => DConnectionMaker
 */
//@Configuration
public class CountingDaoFactory {

//    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

//    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
    }

//    @Bean
    public ConnectionMaker realConnectionMaker() {
        return new DConnectionMaker();
    }

}
