package tobystudyproject.tobystudyproject.service;

import tobystudyproject.tobystudyproject.customexception.SqlRetrievalFailureException;

public interface SqlService {
    String getSql(String key) throws SqlRetrievalFailureException;
}
