package tobystudyproject.tobystudyproject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobystudyproject.tobystudyproject.before.CountingConnectionMaker;
import tobystudyproject.tobystudyproject.before.CountingDaoFactory;
import tobystudyproject.tobystudyproject.before.UserDao;

public class UserDaoConnectionCountingTest {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("ccm.getCounter() :: " + ccm.getCounter());
    }

}
