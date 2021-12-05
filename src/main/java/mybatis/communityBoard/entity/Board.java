package mybatis.communityBoard.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class Board {
    private Long boardId;              // 글 번호
    private String title;               // 글 제목
    private String contents;            // 글 내용
    private String boardType;           // 타입
    private int viewCnt;                // 조회 수
    private LocalDateTime createdDt;   // 생성일
    private LocalDateTime updatedDt;   // 수정 일
}
