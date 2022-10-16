package tobystudyproject.tobystudyproject.sql;

import tobystudyproject.tobystudyproject.dao.UserDao;
import tobystudyproject.tobystudyproject.service.SqlType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class JaxbXmlSqlReader implements SqlReader{
    private static final String DEFAULT_SQLMAP_FILE = "sqlmap.xml";
    private String sqlmapFile = DEFAULT_SQLMAP_FILE;
    public void setSqlmapFile(String sqlmapFile) {
        this.sqlmapFile = sqlmapFile;
    }
    private Map<String, String> sqlMap = new HashMap<>();
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
