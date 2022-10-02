package tobystudyproject.tobystudyproject.objectanddependency.dao.forth;

import tobystudyproject.testrefactoring.dao.first.ConnectionMaker;
import tobystudyproject.tobystudyproject.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {
    private ConnectionMaker connectionMaker;

//    public UserDao(){
//        connectionMaker = new DConnectionMaker();
//    }

    // 수정자 메소드 DI 방식을 이용한 UserDao
    /*public void setConnectionMaker(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }*/
    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException{
        Connection c = connectionMaker.makeConnection();
        // ~~
    }

    public User get(String id) throws ClassNotFoundException, SQLException{
        Connection c = connectionMaker.makeConnection();
        // ~~~~
        return null;
    }

}
