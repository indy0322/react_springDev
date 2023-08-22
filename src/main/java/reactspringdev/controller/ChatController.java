package reactspringdev.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import reactspringdev.domain.Message;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController{
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message") // 다수의 클라이언트에게 message 데이터를 보내기 위해서는 /app/message 로 message 데이터를 보낸다.
    //@SendTo("/chatroom/public")
    public void receivePublicMessage(@Payload Message message){
        log.info("메세지 : {}",message);
        simpMessagingTemplate.convertAndSend("/chatroom/public",message); // /chatroom/public 주소를 구독한 클라이언트에게 message 데이터를 보낸다.
        //return message;
    }

    @MessageMapping("/private-message") // 특정 클라이언트에게 message 데이터를 보내기 위해서는 /app/private-message 로 message 데이터를 보내야 한다.
    public void receivePrivateMessage(@Payload Message message){
        log.info("메세지 : {}",message);
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message); // /user/message.getReceiverName()/private 에게 message 데이터를 보낸다.(정확히는 /user/message.getReceiverName()/private 을 구독한 클라이언트에게 message 데이터를 보낸다.)
    }


}










/*public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message") // /app/message
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message){

        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message); // /user/David/private

        return message;
    }
}*/
