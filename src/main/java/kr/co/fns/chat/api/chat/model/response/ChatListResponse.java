package kr.co.fns.chat.api.chat.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.fns.chat.api.chat.model.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Chat List 데이터 응답")
public class ChatListResponse {
    @Schema(description = "Chat List", example = "ChatMessage 객체 (Schema 참조)", implementation = ChatMessage.class)
    private List<ChatMessage> chatMessages;
}
