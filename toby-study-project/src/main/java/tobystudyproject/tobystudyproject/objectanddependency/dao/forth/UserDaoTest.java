package tobystudyproject.tobystudyproject.objectanddependency.dao.forth;

public class UserDaoTest {
    public static void main(String[] args) {
        /*
        DConnectionMaker c = new DConnectionMaker();
        UserDao dao = new UserDao(c);

        이렇게 코딩시에 생길 문제점
        어떤 ConnectionManager를 사용할지 직접 선택하므로
        얼떨결에 결정 기능을 떠맡아 버렸다
        // ~~
        */

        UserDao userDao = new DaoFactory().userDao();

    }
}
