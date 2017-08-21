package roguezero.email;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static roguezero.environment.Environment.isProduction;
import static roguezero.environment.Environment.testEmail;

public class JeeMessage implements Message {
    private final transient MimeMessage mimeMessage;
    private final transient Emailer emailer;
    private boolean isMinimumFilled = false;

    JeeMessage(Session session, Emailer emailer) throws MessagingException {
        this.emailer = emailer;
        this.mimeMessage = new MimeMessage(session);
        this.mimeMessage.setFrom(emailer.defaultFrom());
    }

    public Message from(String email, String alias) throws MessagingException {
        mimeMessage.setFrom(emailer.emailPerson(email, alias));
        return this;
    }

    public Message to(String email, String alias) throws MessagingException {
        return to(emailer.emailPerson(email, alias));
    }

    public Message to(InternetAddress... tos) throws MessagingException {
        mimeMessage.setRecipients(javax.mail.Message.RecipientType.TO, tos);
        return this;
    }

    public Message bcc(InternetAddress... bccs) throws MessagingException {
        mimeMessage.setRecipients(javax.mail.Message.RecipientType.BCC, bccs);
        return this;
    }

    public Message cc(InternetAddress... ccs) throws MessagingException {
        mimeMessage.setRecipients(javax.mail.Message.RecipientType.CC, ccs);
        return this;
    }

    public Message subject(String subject) throws MessagingException {
        mimeMessage.setSubject(subject);
        return this;
    }

    public Message body(String body) throws MessagingException {
        mimeMessage.setText(body);
        isMinimumFilled = true;
        return this;
    }

    public MimeMessage getMimeMessage() {
        return this.mimeMessage;
    }

    public void send() throws MessagingException {
        if (!isProduction()) {
            to(emailer.emailPerson(
                    testEmail() != null ? testEmail() : "no-reply@tst.jus.br",
                    "TESTE DESENVOLVIMENTO"
            ));
        }

        if (!isMinimumFilled) {
            body("");
        }

        emailer.send(this);
    }
}

