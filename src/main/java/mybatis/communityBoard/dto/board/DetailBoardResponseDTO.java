package mybatis.communityBoard.dto.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class DetailBoardResponseDTO {

    private Long boardId;                   // 게시판 번호
    private String userId;                  // 작성자 ID
    private String title;                   // 게시판 제목
    private String contents;                // 게시판 내용
    private int viewCnt;                 // 조회수

    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime createdDt;        // 작성일
}
