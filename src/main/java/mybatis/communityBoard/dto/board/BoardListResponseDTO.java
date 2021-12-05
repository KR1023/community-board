package mybatis.communityBoard.dto.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BoardListResponseDTO {

    private Long boardId;               // 게시판 번호
    private String title;               // 게시물 제목
    private int viewCnt;                // 조회 수
    private String userId;                  // 작성자
    @JsonFormat(pattern="yyyy.MM.dd")
    private LocalDateTime createdDt;    // 작성일

}
