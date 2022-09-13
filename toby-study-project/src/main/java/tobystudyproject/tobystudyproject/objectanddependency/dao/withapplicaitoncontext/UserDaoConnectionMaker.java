package tobystudyproject.tobystudyproject.objectanddependency.dao.withapplicaitoncontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobystudyproject.tobystudyproject.objectanddependency.dao.forth.UserDao;

public class UserDaoConnectionMaker {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        //
        // dao 코드 사용
        //

        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Connection counter: "+ ccm.getCounter());

    }
}
