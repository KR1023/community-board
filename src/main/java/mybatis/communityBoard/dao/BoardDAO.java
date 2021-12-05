package mybatis.communityBoard.dao;

import mybatis.communityBoard.dto.board.AddBoardRequestDTO;
import mybatis.communityBoard.dto.board.BoardListResponseDTO;
import mybatis.communityBoard.dto.board.DetailBoardResponseDTO;
import mybatis.communityBoard.dto.board.ModifyBoardRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BoardDAO {

    // 게시판 목록
    List<BoardListResponseDTO> findAll(Map<String, Object> list);

    // 게시글 수 (자유 게시판)
    int  countAll(String keyword);

    // 공지사항 목록
    List<BoardListResponseDTO> findNotice(Map<String, Object> list);

    // 공지사항 수
    int countNotice(String keyword);

    // 게시판 등록
    Long addBoard(AddBoardRequestDTO addBoardRequestDTO);

    // 공지사항 등록
    Long addNotice(AddBoardRequestDTO addBoardRequestDTO);

    // 게시판 상세
    DetailBoardResponseDTO detailBoard(Long id);

    // 게시판 조회 시 조회 수 증가
    void incViewCnt(Map<String, Object> view);

    // 게시판 수정
    Long modifyBoard(ModifyBoardRequestDTO modifyBoardRequestDTO);

    // 게시판 삭제
    void deleteBoard(Long id);
}
