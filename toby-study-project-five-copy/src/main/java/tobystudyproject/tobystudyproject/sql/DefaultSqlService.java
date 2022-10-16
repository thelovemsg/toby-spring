package tobystudyproject.tobystudyproject.sql;

public class DefaultSqlService extends BaseSqlService{
    public DefaultSqlService() {
        setSqlReader(new JaxbXmlSqlReader());
        setSqlRegistry(new HashMapSqlRegistry());
    }
}
