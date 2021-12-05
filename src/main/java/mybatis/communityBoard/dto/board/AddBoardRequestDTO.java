package mybatis.communityBoard.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AddBoardRequestDTO {

    private Long boardId;              // 게시판 번호
    private String title;               // 제목
    private String contents;            // 내용
    private Long id;                    // ID

    //  Id 없이 삽입. 테스트
    public AddBoardRequestDTO(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public AddBoardRequestDTO(String title, String contents, Long id) {
        this.title = title;
        this.contents = contents;
        this.id = id;
    }
}
