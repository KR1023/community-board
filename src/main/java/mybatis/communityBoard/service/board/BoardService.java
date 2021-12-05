package mybatis.communityBoard.service.board;

import mybatis.communityBoard.dto.board.AddBoardRequestDTO;
import mybatis.communityBoard.dto.board.BoardListResponseDTO;
import mybatis.communityBoard.dto.board.DetailBoardResponseDTO;
import mybatis.communityBoard.dto.board.ModifyBoardRequestDTO;

import java.util.List;

public interface BoardService {

    // 게시판 목록
    List<BoardListResponseDTO> boardList(String keyword, Integer pageNum);

    // 공지사항 목록
    List<BoardListResponseDTO> noticeList(String keyword, Integer pageNum);

    // 게시글 수 (자유 게시판)
    int countAll(String keyword);

    // 공지사항 수
    int countNotice(String keyword);

    // 게시글 등록
    Long addBoard(AddBoardRequestDTO addBoardRequestDTO);

    // 공지사항 추가
    Long addNotice(AddBoardRequestDTO addBoardRequestDTO);

    // 게시판 상세
    DetailBoardResponseDTO detailBoard(Long id);

    // 게시판 수정
    Long modifyBoard(ModifyBoardRequestDTO modifyBoardRequestDTO);

    // 게시판 삭제
    Long deleteBoard(Long id);
}
