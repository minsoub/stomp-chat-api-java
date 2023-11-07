package kr.co.fns.chat.api.chat.service;

import kr.co.fns.chat.api.chat.enums.ChatType;
import kr.co.fns.chat.api.chat.enums.UserType;
import kr.co.fns.chat.api.chat.model.ChatMessage;
import kr.co.fns.chat.api.chat.model.ChatRoom;
import kr.co.fns.chat.api.chat.model.UserJoinRoom;
import kr.co.fns.chat.api.chat.model.request.ChatMessageRequest;
import kr.co.fns.chat.api.chat.model.response.ChatListResponse;
import kr.co.fns.chat.api.chat.model.response.JoinListResponse;
import kr.co.fns.chat.api.chat.model.response.RoomListResponse;
import kr.co.fns.chat.api.chat.repository.ChatMessageRepository;
import kr.co.fns.chat.api.chat.repository.ChatRoomRepository;
import kr.co.fns.chat.api.chat.repository.UserJoinRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final UserJoinRoomRepository userJoinRoomRepository;
    private final ChatRoomRepository chatRoomRepository;

    public ChatMessage save(ChatMessageRequest message) {
        return chatMessageRepository.save(ChatMessage.builder()
                .id(UUID.randomUUID().toString())
                .message(message.getMessage())
                .messageType(message.getMessageType())
                .dataInfo(message.getDataInfo())
                .integUid(message.getIntegUid())
                .nickName(message.getNickName())
                .roomId(message.getRoomId())
                .chatType(message.getChatType())
                .writeDate(LocalDateTime.now())
                .build());
    }

    public ChatListResponse messageList(ChatType chatType, String roomId) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByRoomIdAndChatType(roomId, chatType);

        return ChatListResponse.builder()
                .chatMessages(chatMessages)
                .build();
    }

    public UserJoinRoom saveUserJoin(ChatMessageRequest messageRequest) {

        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(messageRequest.getRoomId());


        UserJoinRoom userJoinRoom = userJoinRoomRepository.findByRoomIdAndChatTypeAndIntegUid(messageRequest.getRoomId(), messageRequest.getChatType(), messageRequest.getIntegUid());

        if (userJoinRoom != null) {
            return userJoinRoom;
        } else {
            return userJoinRoomRepository.save(
                    UserJoinRoom.builder()
                            .id(UUID.randomUUID().toString())
                            .integUid(messageRequest.getIntegUid())
                            .roomId(messageRequest.getRoomId())
                            .chatType(messageRequest.getChatType())
                            .userType(messageRequest.getIntegUid().equals(chatRoom.get().getIntegUid()) == true ? UserType.OWNER : UserType.USER)
                            .writeDate(LocalDateTime.now())
                            .build()
            );
        }
    }

    public void deleteUserJoin(ChatMessageRequest messageRequest) {
        UserJoinRoom userJoinRoom = userJoinRoomRepository.findByRoomIdAndChatTypeAndIntegUid(messageRequest.getRoomId(), messageRequest.getChatType(), messageRequest.getIntegUid());

        if (userJoinRoom != null) {
            userJoinRoomRepository.delete(userJoinRoom);
        }
    }

    public JoinListResponse getRoomJoinList(ChatType chatType, String roomId) {
        List<UserJoinRoom> userJoinRoomList = userJoinRoomRepository.findByRoomIdAndChatType(roomId, chatType);

        return JoinListResponse.builder()
                .userJoinRoomList(userJoinRoomList)
                .build();
    }

}
