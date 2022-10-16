package tobystudyproject.tobystudyproject.sql;

import tobystudyproject.tobystudyproject.customexception.SqlNotFoundException;
import tobystudyproject.tobystudyproject.customexception.SqlRetrievalFailureException;
import tobystudyproject.tobystudyproject.dao.UserDao;
import tobystudyproject.tobystudyproject.service.SqlType;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class XmlSqlService implements SqlService, SqlRegistry, SqlReader {

    private String sqlmapFile;
    public void setSqlmapFile(String sqlmapFile){
        this.sqlmapFile = sqlmapFile;
    }

    private SqlReader sqlReader;
    private SqlRegistry sqlRegistry;

    public void setSqlReader(SqlReader sqlReader){
        this.sqlReader = sqlReader;
    }

    public void setSqlRegistry(SqlRegistry sqlRegistry){
        this.sqlRegistry = sqlRegistry;
    }

    private Map<String, String> sqlMap = new HashMap<>();
    public String findSql(String key) throws SqlNotFoundException{
        String sql = sqlMap.get(key);
        if(sql == null) throw new SqlNotFoundException(key + "에 대한 sql을 찾을 수 없습니다.");
        else return sql;
    }

    public void registerSql(String key, String sql){
        sqlMap.put(key, sql);
    }

    @PostConstruct
    public void loadSql() {
        this.sqlReader.read(this.sqlRegistry);
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        String sql = this.sqlRegistry.findSql(key);
        if(sql == null)
            throw new SqlRetrievalFailureException(key + "를 이용해서 SQL을 찾을 수 없습니다.");
        return sql;
    }

    @Override
    public void read(SqlRegistry sqlRegistry) {
        String contextPath = Sqlmap.class.getPackage().getName();
        try {
            JAXBContext context = JAXBContext.newInstance(contextPath);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream is = UserDao.class.getResourceAsStream(this.sqlmapFile);
            Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(is);
            for (SqlType sqlType : sqlmap.getSql()) {
                sqlMap.put(sqlType.getKey(), sqlType.getValue());
            }
        } catch (JAXBException e){
            throw new RuntimeException(e);
        }
    }
}
