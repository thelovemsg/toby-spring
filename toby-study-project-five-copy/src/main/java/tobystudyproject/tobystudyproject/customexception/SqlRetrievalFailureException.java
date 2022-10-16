package tobystudyproject.tobystudyproject.customexception;

public class SqlRetrievalFailureException extends RuntimeException{
    public SqlRetrievalFailureException(String message){
        super(message);
    }

    public SqlRetrievalFailureException(String message, Throwable cause) {
        super(message, cause);
    }
<<<<<<< HEAD
=======

    public SqlRetrievalFailureException(SqlNotFoundException e) {
        super(e);
    }
>>>>>>> sjmoon
}

