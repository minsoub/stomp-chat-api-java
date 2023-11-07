package kr.co.fns.chat.api.chat.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.fns.chat.api.chat.model.ChatMessage;
import kr.co.fns.chat.api.chat.model.UserJoinRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Chat Join List 데이터 응답")
public class JoinListResponse {
    @Schema(description = "Chat Join List", example = "UserJoinRoom 객체 (Schema 참조)", implementation = UserJoinRoom.class)
    private List<UserJoinRoom> userJoinRoomList;
}
