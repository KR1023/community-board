package mybatis.communityBoard.controller;

import lombok.RequiredArgsConstructor;
import mybatis.communityBoard.dto.board.AddBoardRequestDTO;
import mybatis.communityBoard.dto.board.ModifyBoardRequestDTO;
import mybatis.communityBoard.service.board.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시판 목록
     * @param keyword
     * @param pageNum
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity getBoardList(@RequestParam (value="keyword", required = false) String keyword,
                                       @RequestParam (value="pageNum", required = false) Integer pageNum){
        return new ResponseEntity(boardService.boardList(keyword, pageNum), HttpStatus.OK);
    }

    /**
     * 게시판 추가
     * @param addBoardRequestDTO
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity addBoard(@RequestBody AddBoardRequestDTO addBoardRequestDTO){
        return new ResponseEntity(boardService.addBoard(addBoardRequestDTO), HttpStatus.OK);
    }

    /**
     * 게시판 상세
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity detailBoard(@PathVariable ("id") Long id){
        return new ResponseEntity(boardService.detailBoard(id),HttpStatus.OK);
    }

    /**
     * 게시판 수정
     * @param modifyBoardRequestDTO
     * @return
     */
    @PutMapping("/modify")
    public ResponseEntity modifyBoard(@RequestBody ModifyBoardRequestDTO modifyBoardRequestDTO){
        return new ResponseEntity(boardService.modifyBoard(modifyBoardRequestDTO), HttpStatus.OK);
    }

    /**
     * 게시판 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBoard(@PathVariable("id") Long id){
        return new ResponseEntity(boardService.deleteBoard(id), HttpStatus.OK);
    }
}
