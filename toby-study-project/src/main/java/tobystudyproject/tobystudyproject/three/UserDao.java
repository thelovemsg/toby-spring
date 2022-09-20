package tobystudyproject.tobystudyproject.three;

import tobystudyproject.tobystudyproject.objectanddependency.dao.User;

import java.sql.*;

public class UserDao {
    //초난감 UserDao
    public void add(User user) throws ClassNotFoundException, SQLException{
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2
                , user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();
        ps.close();
        c.close();
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
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = getConnection();
            ps = c.prepareStatement("delete from users");
            ps.executeUpdate();
        } catch (SQLException e){
            throw e;
        } finally {
            if(ps!=null){
                try {
                    ps.close();
                }catch (SQLException e){
                }
            }
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e){
                }
            }
        }

        ps.executeUpdate();

        ps.close();
        c.close();
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

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/toby", "sa", "");
    }
}
