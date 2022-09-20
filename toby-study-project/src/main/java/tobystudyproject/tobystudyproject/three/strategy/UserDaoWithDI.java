package tobystudyproject.tobystudyproject.three.strategy;

import tobystudyproject.tobystudyproject.objectanddependency.dao.User;

import java.sql.*;

public class UserDaoWithDI {
    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void add(final User user) throws SQLException {
        this.jdbcContext.excuteSql("delete from users");
    }

    public void deleteAll() throws SQLException{
        excuteSql("delete from users");
    }

    private void excuteSql(final String query) throws SQLException {
        this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement(query);
                return ps;
            }
        });
    }
}
