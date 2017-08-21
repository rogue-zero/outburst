package roguezero.email;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public interface Emailer {

    InternetAddress defaultFrom() throws AddressException;

    InternetAddress emailPerson(String email, String alias) throws AddressException;

    void send(Message message) throws MessagingException;

    Message create() throws MessagingException;

}