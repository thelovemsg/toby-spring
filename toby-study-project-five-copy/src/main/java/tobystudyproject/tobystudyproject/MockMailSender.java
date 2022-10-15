package tobystudyproject.tobystudyproject;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import tobystudyproject.tobystudyproject.service.MailSender;

import java.util.ArrayList;
import java.util.List;

public class MockMailSender implements MailSender {
    private List<String> requests = new ArrayList<>();

    public List<String> getRequests() {
        return requests;
    }

    @Override
    public void send(SimpleMailMessage simpleMailMessage) throws MailException {
        requests.add(simpleMailMessage.getTo()[0]);
    }

    @Override
    public void send(SimpleMailMessage[] simpleMessages) throws MailException {
    }
}
