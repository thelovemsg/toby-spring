package tobystudyproject.tobystudyproject.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import tobystudyproject.tobystudyproject.Level;
import tobystudyproject.tobystudyproject.User;
import tobystudyproject.tobystudyproject.sql.SqlService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDaoJdbc implements IUserDao {
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private JdbcTemplate jdbcTemplate;
    private Map<String, String> sqlMap;
    public void setSqlMap(Map<String, String> sqlMap){
        this.sqlMap = sqlMap;
    }
    private SqlService sqlService;
    public void setSqlService(SqlService sqlService){
        this.sqlService = sqlService;
    }
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
        this.jdbcTemplate.update(
            this.sqlService.getSql("userAdd"),
            user.getId(), user.getName(), user.getPassword(), user.getLevel().intValue(),
            user.getLogin(), user.getRecommend(), user.getEmail());
    }
    public User get(String id) {
       return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userGet"),
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
        return this.jdbcTemplate.query(con -> con.prepareStatement(this.sqlService.getSql("userUpdate")), rs -> {
            rs.next();
            return rs.getInt(1);
        });
    }
    public List<User> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userDeleteAll"),
                userMapper
        );
    }
    public void update(User user1) {
        this.jdbcTemplate.update(
            this.sqlService.getSql("userUpdate"),
            user1.getName(), user1.getPassword(), user1.getLevel().intValue(), user1.getLogin(),
            user1.getRecommend(), user1.getEmail(), user1.getId()
        );
    }
}
