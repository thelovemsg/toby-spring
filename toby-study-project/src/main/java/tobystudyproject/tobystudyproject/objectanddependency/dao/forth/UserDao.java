package tobystudyproject.tobystudyproject.objectanddependency.dao.forth;

import tobystudyproject.tobystudyproject.objectanddependency.dao.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {
    private ConnectionMaker connectionMaker;

//    public UserDao(){
//        connectionMaker = new DConnectionMaker();
//    }
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
