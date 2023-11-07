package kr.co.fns.chat.api.chat.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import kr.co.fns.chat.api.chat.enums.ChatType;
import kr.co.fns.chat.api.chat.enums.MessageType;
import kr.co.fns.chat.api.chat.model.DataInfo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="chat_message")
@Getter
@Setter
@Builder
@NoArgsConstructor    // 기본 생성자 추가
@AllArgsConstructor
public class ChatMessage {
    @Id
    private String id;
    private MessageType messageType;
    private String roomId;  // 방번호
    private ChatType chatType;
    private String integUid;  // 메시지 보낸사람 (통합 UID)
    private String nickName;      // 메시지 보낸사람 NickName
    private String message;
    private Boolean isFile;
    private DataInfo dataInfo;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime writeDate;


}
