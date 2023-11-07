package kr.co.fns.chat.api.chat.repository;

import kr.co.fns.chat.api.chat.enums.ChatType;
import kr.co.fns.chat.api.chat.model.UserJoinRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserJoinRoomRepository extends MongoRepository<UserJoinRoom, String> {

    List<UserJoinRoom> findByRoomIdAndChatType(String roomId, ChatType chatType);

    UserJoinRoom findByRoomIdAndChatTypeAndIntegUid(String roomId, ChatType chatType, String integUid);
}
