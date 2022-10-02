package tobystudyproject.tobystudyproject.before;

import org.h2.result.Row;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import tobystudyproject.tobystudyproject.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDao {

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userMapper =
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

    public void add(User user) {
        this.jdbcTemplate.update("insert into user (id, name, password) values(?,?,?)", user.getId(), user.getName(), user.getPassword());
    }

    /*public void add(User user) throws DuplicateUserIdException{
        try {
            this.jdbcTemplate.update("insert into user (id, name, password) values(?,?,?)", user.getId(), user.getName(), user.getPassword());
        } catch (SQLException e){
            throw new DuplicateUserIdException(e);
        }
    }*/


    public User get(String id) {
       return this.jdbcTemplate.queryForObject("select * from user where id = ?",
               new Object[]{id},
               userMapper
               );
    }

    public void deleteAll() {
        this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        return con.prepareStatement("delete from user");
                    }
                }
        );
    }

    public int getCount() {
        return this.jdbcTemplate.query(con -> con.prepareStatement("select count(*) from user"), rs -> {
            rs.next();
            return rs.getInt(1);
        });
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from user order by id",
                userMapper
        );
    }
}
