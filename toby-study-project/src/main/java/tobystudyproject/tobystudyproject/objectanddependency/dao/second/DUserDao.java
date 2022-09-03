package tobystudyproject.tobystudyproject.objectanddependency.dao.second;

import java.sql.Connection;
import java.sql.SQLException;

public class DUserDao extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //D사 DB 코드생성
        return null;
    }
}
