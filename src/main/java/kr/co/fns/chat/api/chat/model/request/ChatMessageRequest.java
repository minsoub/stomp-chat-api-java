package kr.co.fns.chat.api.chat.model.request;

import kr.co.fns.chat.api.chat.enums.ChatType;
import kr.co.fns.chat.api.chat.enums.MessageType;
import kr.co.fns.chat.api.chat.model.DataInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageRequest {
    private MessageType messageType;
    private String roomId;  // 방번호
    private ChatType chatType;
    private String integUid;  // 메시지 보낸사람 (통합 UID)
    private String nickName;      // 메시지 보낸사람 NickName
    private String message;
    private Boolean isFile;
    private DataInfo dataInfo;
}
