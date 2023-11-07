package kr.co.fns.chat.api.chat.service;

import kr.co.fns.chat.api.chat.enums.ChatType;
import kr.co.fns.chat.api.chat.model.ChatRoom;
import kr.co.fns.chat.api.chat.model.request.ChatCreateRequest;
import kr.co.fns.chat.api.chat.model.response.RoomListResponse;
import kr.co.fns.chat.api.chat.repository.ChatRoomRepository;
import kr.co.fns.chat.api.chat.repository.RedisChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChatRoomService {
    private final RedisChatRoomRepository redisChatRoomRepository;
    private final ChatRoomRepository chatRoomRepository;

    /**
     * ChatType에 따른 채팅방 리스트 리턴
     *
     * @param chatType
     * @param integUid
     * @return
     */
    public RoomListResponse getRooms(ChatType chatType, String integUid) {

        List<ChatRoom> chatRoomList = chatRoomRepository.findByChatType(chatType);
        //List<ChatRoom> chatRoomList = redisChatRoomRepository.findAllRoom(chatType);

        return RoomListResponse.builder()
                .chatRoomList(chatRoomList)
                .build();
    }

    /**
     * 채팅방 개설
     *
     * @param chatCreateRequest
     * @return
     */
    public ChatRoom createRoom(ChatCreateRequest chatCreateRequest) {
        ChatRoom chatRoom = ChatRoom.builder()
                        .chatType(chatCreateRequest.getChatType())
                        .name(chatCreateRequest.getName())
                        .roomId(chatCreateRequest.getRoomId())
                        .integUid(chatCreateRequest.getIntegUid())
                        .userType(chatCreateRequest.getUserType())
                        .build();
        //redisChatRoomRepository.createChatRoom(chatRoom);

        return chatRoomRepository.save(chatRoom);

        //return chatRoom;
    }

    /**
     * 채팅방 정보 조회
     *
     * @param chatType
     * @param roomId
     * @return
     */
    public ChatRoom getRoom(ChatType chatType, String roomId) {

        return chatRoomRepository.findByRoomIdAndChatType(roomId, chatType);

        //return redisChatRoomRepository.findRoomById(chatType, roomId);
    }


}
