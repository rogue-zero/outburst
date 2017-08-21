package roguezero.email;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.UnsupportedEncodingException;

public class JeeEmailer implements Emailer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JeeEmailer.class);
    transient Session session;

    public JeeEmailer() {
        this(null);
    }

    public JeeEmailer(String jndiName) {
        if (jndiName != null) {
            try {
                Context initCtx = new InitialContext();
                session = (Session) initCtx.lookup(jndiName);
            } catch (NamingException e) {
                LOGGER.error("Falha ao inicializar emailer: ", e);
            }
        }
    }

    public InternetAddress defaultFrom() throws AddressException {
        return emailPerson("no-reply@tst.jus.br", "Sistema TST");
    }

    public InternetAddress emailPerson(String email, String alias) throws AddressException {
        try {
            return new InternetAddress(email, alias);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(String.format("Falha ao definir alias de remetente (%s): ", alias), e);
            return new InternetAddress(email);
        }
    }

    public void send(Message message) throws MessagingException {
        Transport.send(((JeeMessage) message).getMimeMessage());
    }

    public Message create() throws MessagingException {
        return new JeeMessage(session, this);
    }

}