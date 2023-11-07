package kr.co.fns.chat.api.chat.model;

import kr.co.fns.chat.api.chat.enums.ChatType;
import kr.co.fns.chat.api.chat.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigInteger;

@Document(collection="chat_room")
@Getter
@Setter
@Builder
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    @Id
    private String roomId;
    private String name;
    private ChatType chatType;
    private String integUid;
    private UserType userType;  // 방장 구분 : OWNER, USER
}
