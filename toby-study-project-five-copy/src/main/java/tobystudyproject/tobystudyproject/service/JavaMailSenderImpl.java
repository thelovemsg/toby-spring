package tobystudyproject.tobystudyproject.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

public class JavaMailSenderImpl implements MailSender {
    @Override
    public void send(SimpleMailMessage simpleMailMessage) throws MailException {

    }

    @Override
    public void send(SimpleMailMessage[] simpleMessages) throws MailException {

    }
}
