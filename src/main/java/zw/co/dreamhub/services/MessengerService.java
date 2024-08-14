package zw.co.dreamhub.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.config.env.EmailEnv;
import zw.co.dreamhub.domain.dto.request.MessengerRequest;
import zw.co.dreamhub.domain.dto.response.MessengerResponse;
import zw.co.dreamhub.domain.models.enums.MessageType;
import zw.co.dreamhub.domain.models.enums.MessengerProcessingStatus;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 14/1/2023
 */

@Service
@Configuration
@EnableAsync
@Slf4j
@RequiredArgsConstructor
public class MessengerService {

    private final JavaMailSender mailSender;
    private final EmailEnv emailEnv;

    @Async
    public void send(MessengerRequest request) {


        try {
            ObjectMapper mapper = new ObjectMapper();
            log.info("Outgoing messenger request : {} ", mapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            log.info("Messenger json processing exception : {}", e.getMessage());
        }

        // response
        Optional<MessengerResponse> response;

        // broadcast sms message
        if (request.messageType().equals(MessageType.SMS)) {
            response = sms(request);
        }
        // broadcast email message
        else if (request.messageType().equals(MessageType.EMAIL)) {
            response = email(request);
        }
        // broadcast firebase message
        else {
            response = firebase(request);
        }


        // return response
        CompletableFuture.completedFuture(response);
    }


    private Optional<MessengerResponse> email(MessengerRequest request) {
        try {

            String from = String.format("%s <%s>", request.from(), emailEnv.mail().username());

            if (request.file() == null) {
                SimpleMailMessage msg = new SimpleMailMessage();

                // format email
                msg.setTo(request.to());
                msg.setSubject(request.subject());
                msg.setText(request.content());
                msg.setFrom(from);

                // send email
                mailSender.send(msg);
            } else {
                // format definition
                MimeMessage message = mailSender.createMimeMessage();

                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                // format email
                helper.setFrom(from);
                helper.setTo(request.to());
                helper.setSubject(request.subject());
                helper.setText(request.content());

                // set file
                FileSystemResource file = new FileSystemResource(request.file());
                helper.addAttachment(request.file().getName(), file);

                // send email
                mailSender.send(message);
            }

            return Optional.of(new MessengerResponse(MessengerProcessingStatus.SUCCESS, MessengerProcessingStatus.SUCCESS.name()));
        } catch (Exception e) {
            log.info("Email Messenger Exception : {}", e.getMessage());
            return Optional.of(new MessengerResponse(MessengerProcessingStatus.ERROR, e.getMessage()));
        }
    }

    private Optional<MessengerResponse> sms(MessengerRequest request) {
        // todo : integrate sms's
        return Optional.empty();
    }

    private Optional<MessengerResponse> firebase(MessengerRequest request) {
        try {
            MulticastMessage message = MulticastMessage.builder()
                    .setNotification(Notification.builder()
                            .setTitle(request.subject())
                            .setBody(request.content()).build())
                    .addAllTokens(Arrays.stream(request.to().split(",")).toList())
                    .build();
            FirebaseMessaging.getInstance().sendEachForMulticast(message);
            log.info("Firebase Notification Sent Successfully");
            return Optional.of(new MessengerResponse(MessengerProcessingStatus.SUCCESS, MessengerProcessingStatus.SUCCESS.name()));
        } catch (FirebaseMessagingException e) {
            log.info("Firebase Notification Exception : {}", e.getMessage());
            return Optional.of(new MessengerResponse(MessengerProcessingStatus.ERROR, e.getMessage()));
        }
    }

}
