package tobystudyproject.tobystudyproject.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import tobystudyproject.tobystudyproject.Level;
import tobystudyproject.tobystudyproject.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDao implements IUserDao {

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
                    user.setLevel(Level.valueOf(rs.getInt("level")));
                    user.setLogin(rs.getInt("login"));
                    user.setRecommend(rs.getInt("recommend"));
                    return user;
                }
            };

    public void add(User user) {
        this.jdbcTemplate.update("insert into user (id, name, password, level, login, recommend) values(?,?,?,?,?,?)",
                user.getId(), user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend());
    }

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

    public void update(User user1) {
        this.jdbcTemplate.update(
            "update user set name = ?, password = ?, level = ?, login = ?, "
            + "recommend = ? where id = ?", user1.getName(), user1.getPassword(), user1.getLevel().intValue(), user1.getLogin(),
            user1.getRecommend(), user1.getId()
        );
    }
}
