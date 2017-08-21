package roguezero.email;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

public interface Message {

    Message from(String email, String alias) throws MessagingException;

    Message to(String email, String alias) throws MessagingException;

    Message to(InternetAddress... tos) throws MessagingException;

    Message bcc(InternetAddress... bccs) throws MessagingException;

    Message cc(InternetAddress... ccs) throws MessagingException;

    Message subject(String subject) throws MessagingException;

    Message body(String body) throws MessagingException;

    void send() throws MessagingException;

}
