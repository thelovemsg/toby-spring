package tobystudyproject.tobystudyproject.objectanddependency.dao.withapplicaitoncontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobystudyproject.tobystudyproject.objectanddependency.dao.forth.UserDao;

/**
 * @Configuration이 붙은 자바 코드를 설정정보로 사용하려면
 * AnnotationConfigApplicationContext를 이용하면 된다.
 */
public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException{
        // to something...
        // 1. factory에서 매번 새로운 instance를 생성
        DaoFactory daoFactory = new DaoFactory();
        UserDao dao1 = daoFactory.userDao();
        UserDao dao2 = daoFactory.userDao();

        System.out.println(dao1);
        System.out.println(dao2);

        // 2. ApplicationContext를 통해 객체를 생성
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao3 = context.getBean("userDao", UserDao.class);
        UserDao userDao4 = context.getBean("userDao", UserDao.class);
        System.out.println("userDao3 = " + userDao3);
        System.out.println("userDao4 = " + userDao4);

        //
    }
}
