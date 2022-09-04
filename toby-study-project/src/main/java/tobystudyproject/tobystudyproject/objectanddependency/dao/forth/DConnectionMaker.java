package tobystudyproject.tobystudyproject.objectanddependency.dao.forth;

import java.sql.Connection;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws SQLException, ClassNotFoundException {
        //D사 독자적인 연결 방법3
        return null;
    }
}
