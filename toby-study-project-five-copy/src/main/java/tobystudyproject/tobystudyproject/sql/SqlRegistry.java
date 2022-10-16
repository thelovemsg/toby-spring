package tobystudyproject.tobystudyproject.sql;

import tobystudyproject.tobystudyproject.customexception.SqlNotFoundException;

public interface SqlRegistry {
    void registerSql(String key, String sql);
    String findSql(String key) throws SqlNotFoundException;
}
