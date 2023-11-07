package kr.co.fns.chat.api.chat.controller;

import kr.co.fns.chat.api.chat.enums.MessageType;
import kr.co.fns.chat.api.chat.listener.RedisPublisher;
import kr.co.fns.chat.api.chat.model.ChatMessage;
import kr.co.fns.chat.api.chat.model.request.ChatMessageRequest;
import kr.co.fns.chat.api.chat.repository.RedisChatRoomRepository;
import kr.co.fns.chat.api.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ChatController {

    private final RedisPublisher redisPublisher;
    private final RedisChatRoomRepository chatRoomRepository;
    private final ChatMessageService chatMessageService;
    /**
     * /send/room/{roomId}/message
     * @param chatMessage
     */
    @MessageMapping("/room/{roomId}/message")
    public void message(ChatMessageRequest chatMessage) {
        log.debug("rcv message => {}", chatMessage);
        if (chatMessage.getMessageType() == MessageType.JOIN) {
            chatRoomRepository.enterChatRoom(chatMessage.getRoomId());
            chatMessage.setMessage(chatMessage.getNickName()+"님이 입장하셨습니다.");

            chatMessageService.saveUserJoin(chatMessage);
        } else if(chatMessage.getMessageType() == MessageType.EXIT) {
            //chatRoomRepository.enterChatRoom(chatMessage.getRoomId());
            chatMessage.setMessage(chatMessage.getNickName()+"님이 퇴장하셨습니다.");

            chatMessageService.deleteUserJoin(chatMessage);
        }

        ChatMessage message = chatMessageService.save(chatMessage);

        // Websocket에 발행된 메시지를 redis로 발행한다. (Publish)
        redisPublisher.publish(chatRoomRepository.getTopic(chatMessage.getRoomId()), message);
    }
}
