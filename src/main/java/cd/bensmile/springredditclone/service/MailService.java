package cd.bensmile.springredditclone.service;

import cd.bensmile.springredditclone.exception.SpringRedditException;
import cd.bensmile.springredditclone.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;

@AllArgsConstructor
@Service
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    private final MailContentBuilder mailContentBuilder;

    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper((mimeMessage));
//            messageHelper.setFrom("smilekafirongo@gmail.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation mail sent");
        } catch (Exception ex) {

            ex.printStackTrace();
//            throw new SpringRedditException("Exception occured  when sending mail to " + notificationEmail.getRecipient());
        }
    }

}
