package tobystudyproject.tobystudyproject.three.strategy;

import tobystudyproject.tobystudyproject.objectanddependency.dao.User;

import java.sql.*;

public class UserDao {
    public void add(final User user) throws ClassNotFoundException, SQLException{
        jdbcContextWithStatementStrategy(c -> {
            PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            return ps;
        });
//        class AddStatement implements StatementStrategy{
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
//                ps.setString(1, user.getId());
//                ps.setString(2, user.getName());
//                ps.setString(3, user.getPassword());
//                return ps;
//            }
//        }

        //익명클래스 생성
//        StatementStrategy st = c -> {
//            PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
//            ps.setString(1, user.getId());
//            ps.setString(2, user.getName());
//            ps.setString(3, user.getPassword());
//            return ps;
//        };
//        jdbcContextWithStatementStrategy(st);
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);
        User user = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            rs.next();
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        c.close();

        if(user == null) throw new RuntimeException("no data");

        return user;
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        jdbcContextWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                return c.prepareStatement("delete from users");
            }
        });
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = getConnection();
            ps = c.prepareStatement("select count(*) from users");
            ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e){
            throw  e;
        } finally {
            if(rs!=null){
                try {
                    rs.close();
                }catch (SQLException e){

                }
            }
            if(c != null){
                try {
                    c.close();
                }catch (SQLException e){

                }
            }
        }

    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = getConnection();
            ps = stmt.makePreparedStatement(c);
            ps.executeUpdate();
        } catch(Exception e){
            throw new SQLException();
        } finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e){
                }
            }
            if(c != null){
                try{
                    c.close();
                }catch (SQLException e){
                }
            }
        }
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/toby", "sa", "");
    }
}
