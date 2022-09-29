package tobystudyproject.tobystudyproject.three.strategy;

import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import tobystudyproject.tobystudyproject.objectanddependency.dao.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDaoWithDI {
//    private JdbcContext jdbcContext;
//
//    public void setJdbcContext(JdbcContext jdbcContext) {
//        this.jdbcContext = jdbcContext;
//    }
    private RowMapper<User> userRowMapper =
        new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    };

    private JdbcTemplate template;

    public void setDataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public void deleteAll(final User user) throws SQLException {
        //        excuteSql("delete from users");

        this.template.update("delete from users");
    }

    public void add(User user) throws SQLException {
        this.template.update("insert into users(id, name, password) values(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
    }

    public int getCount() {
//        return this.template.query(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                return con.prepareStatement("select count(*) form users");
//            }
//        }, new ResultSetExtractor<Integer>() {
//            public Integer extractData(ResultSet rs) throws SQLException,
//                    DataAccessException{
//                rs.next();
//                return rs.getInt(1);
//            }
//        });
            return this.template.query(con -> con.prepareStatement("select count(*) form users"), rs -> {
            rs.next();
            return rs.getInt(1);
        });
    }

    public User get(String id){
        return this.template.queryForObject("select * from users where id = ?",
                new Object[]{id},
                this.userRowMapper
                );

    }

    public List<User> getAll() {
        return this.template.query("select * from users order by id",
                this.userRowMapper);
    }


 //    public void deleteAll() throws SQLException{
//        excuteSql("delete from users");
//    }

//    private void excuteSql(final String query) throws SQLException {
//        this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps = c.prepareStatement(query);
//                return ps;
//            }
//        });
//    }
}
