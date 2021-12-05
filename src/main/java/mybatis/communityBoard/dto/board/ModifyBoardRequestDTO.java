package mybatis.communityBoard.dto.board;

import lombok.Setter;

@Setter
public class ModifyBoardRequestDTO {
    
    private Long boardId;               // 게시판 번호
    private String title;               // 게시판 제목
    private String contents;            // 게시판 내용

    public ModifyBoardRequestDTO(Long boardId, String title, String contents) {
        this.boardId = boardId;
        this.title = title;
        this.contents = contents;
    }

    public Long getBoardId() {
        return boardId;
    }


}
