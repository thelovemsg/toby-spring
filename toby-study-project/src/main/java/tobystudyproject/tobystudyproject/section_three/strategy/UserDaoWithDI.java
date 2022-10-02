package tobystudyproject.tobystudyproject.section_three.strategy;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import tobystudyproject.tobystudyproject.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDaoWithDI {
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
        this.template.update("delete from users");
    }

    public void add(User user) throws SQLException {
        this.template.update("insert into users(id, name, password) values(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
    }

    public int getCount() {

        return this.template.query(con -> con.prepareStatement("select count(*) form users"), rs -> {
            rs.next();
            return rs.getInt(1);
        });
    }

    public User get(String id) {
        return this.template.queryForObject("select * from users where id = ?",
                new Object[]{id},
                this.userRowMapper
        );

    }

    public List<User> getAll() {
        return this.template.query("select * from users order by id",
                this.userRowMapper);
    }
}