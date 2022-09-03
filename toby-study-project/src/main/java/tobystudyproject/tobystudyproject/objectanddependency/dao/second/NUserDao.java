package tobystudyproject.tobystudyproject.objectanddependency.dao.second;

import java.sql.Connection;
import java.sql.SQLException;

public class NUserDao extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //N 사 DB생성 코드
        return null;
    }
}
