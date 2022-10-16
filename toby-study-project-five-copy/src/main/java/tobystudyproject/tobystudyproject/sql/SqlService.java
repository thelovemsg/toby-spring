package tobystudyproject.tobystudyproject.sql;

import tobystudyproject.tobystudyproject.customexception.SqlRetrievalFailureException;

public interface SqlService {
    String getSql(String key) throws SqlRetrievalFailureException;
}
