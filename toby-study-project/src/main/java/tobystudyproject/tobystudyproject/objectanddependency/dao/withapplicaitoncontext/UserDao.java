package tobystudyproject.tobystudyproject.objectanddependency.dao.withapplicaitoncontext;

import tobystudyproject.tobystudyproject.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//DataSource를 사용하는 userDao
public class UserDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {
        Connection c = dataSource.getConnection();
        //do something...
    }
}
