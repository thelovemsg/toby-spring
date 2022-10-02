package tobystudyproject.tobystudyproject.section_three.templatemethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract public class UserDao {
    abstract protected PreparedStatement makeStatement(Connection c) throws SQLException;
}
