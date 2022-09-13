package tobystudyproject.tobystudyproject.objectanddependency.dao.withapplicaitoncontext;

import tobystudyproject.testrefactoring.dao.first.ConnectionMaker;
import tobystudyproject.tobystudyproject.objectanddependency.dao.forth.DConnectionMaker;
import tobystudyproject.tobystudyproject.objectanddependency.dao.forth.UserDao;

/*
* Inversion of Control
* => application context 를 활용한 IoC
*
* */
//@Configuration // application context or bean Factory 가 사용할 정보
public class DaoFactory {

//    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

//    @Bean
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker();
    }
}
