package tobystudyproject.tobystudyproject.reflection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobystudyproject.tobystudyproject.beanstudy.Message;
import tobystudyproject.tobystudyproject.beanstudy.MessageFactoryBean;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class) // (JUnit5)
@ContextConfiguration(locations="/FactoryBeanTestCOntext.xml")
public class FactoryBeanTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void getMessageFromFactoryBean() {
        Object message = context.getBean("message");
        assertThat(message).isEqualTo(Message.class);
        assertThat(((Message)message).getText()).isEqualTo("Factory Bean");
    }

    @Test
    public void getFactoryBean() throws Exception {
        Object factory = context.getBean("&message");
        assertThat(factory).isEqualTo(MessageFactoryBean.class);
    }

}
