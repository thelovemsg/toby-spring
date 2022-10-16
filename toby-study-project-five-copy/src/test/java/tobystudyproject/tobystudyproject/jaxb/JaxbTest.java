package tobystudyproject.tobystudyproject.jaxb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tobystudyproject.tobystudyproject.service.SqlType;
import tobystudyproject.tobystudyproject.sql.Sqlmap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.List;

public class JaxbTest {

    @Test
    public void readSqlMap() throws JAXBException, IOException {
        String contextPath = Sqlmap.class.getPackage().getName();
        System.out.println("contextPath :: " + contextPath);
        JAXBContext context = JAXBContext.newInstance(contextPath);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(getClass().getResourceAsStream("sqlmap.xml"));

        List<SqlType> list = sqlmap.getSql();

        Assertions.assertThat(list.size()).isEqualTo(3);
        Assertions.assertThat(list.get(0).getKey()).isEqualTo("add");
        Assertions.assertThat(list.get(0).getValue()).isEqualTo("insert");
        Assertions.assertThat(list.get(1).getKey()).isEqualTo("get");
        Assertions.assertThat(list.get(1).getValue()).isEqualTo("select");
        Assertions.assertThat(list.get(2).getKey()).isEqualTo("delete");
        Assertions.assertThat(list.get(2).getValue()).isEqualTo("delete");

    }
}
