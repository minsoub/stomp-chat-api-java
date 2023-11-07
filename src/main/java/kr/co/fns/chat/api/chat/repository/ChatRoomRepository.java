package kr.co.fns.chat.api.chat.repository;

import kr.co.fns.chat.api.chat.enums.ChatType;
import kr.co.fns.chat.api.chat.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    ChatRoom findByRoomIdAndChatType(String roomId, ChatType chatType);

    List<ChatRoom> findByChatType(ChatType chatType);
}
