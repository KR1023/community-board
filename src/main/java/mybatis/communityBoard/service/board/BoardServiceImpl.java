package mybatis.communityBoard.service.board;

import lombok.RequiredArgsConstructor;
import mybatis.communityBoard.dao.BoardDAO;
import mybatis.communityBoard.dto.board.AddBoardRequestDTO;
import mybatis.communityBoard.dto.board.BoardListResponseDTO;
import mybatis.communityBoard.dto.board.DetailBoardResponseDTO;
import mybatis.communityBoard.dto.board.ModifyBoardRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardDAO boardDAO;

    // 게시물 조회
    @Transactional
    @Override
    public List<BoardListResponseDTO> boardList(String keyword, Integer pageNum) {
        Map<String, Object> list = new HashMap<>();
        int page = 0;
        if(pageNum != null){
            page = pageNum * 10;
        }

        list.put("keyword", keyword);
        list.put("pageNum", page);
        return boardDAO.findAll(list);
    }

    // 공지사항 목록
    @Transactional
    @Override
    public List<BoardListResponseDTO> noticeList(String keyword, Integer pageNum) {
        Map<String, Object> list = new HashMap<>();
        int page = 0;
        if(pageNum != null){
            page = pageNum * 10;
        }

        list.put("keyword", keyword);
        list.put("pageNum", page);
        return boardDAO.findNotice(list);
    }

    // 게시글 수 (자유 게시판)
    @Transactional
    @Override
    public int countAll(String keyword) {
        return boardDAO.countAll(keyword);
    }

    // 공지사항 수
    @Transactional
    @Override
    public int countNotice(String keyword) {
        return boardDAO.countNotice(keyword);
    }

    // 게시물 추가
    @Transactional
    @Override
    public Long addBoard(AddBoardRequestDTO addBoardRequestDTO) {
        boardDAO.addBoard(addBoardRequestDTO);
        return addBoardRequestDTO.getBoardId();
    }

    // 공지사항 추가
    @Transactional
    @Override
    public Long addNotice(AddBoardRequestDTO addBoardRequestDTO) {
        boardDAO.addNotice(addBoardRequestDTO);
        return addBoardRequestDTO.getBoardId();
    }

    // 게시물 상세
    @Transactional
    @Override
    public DetailBoardResponseDTO detailBoard(Long id) {
        DetailBoardResponseDTO dto = boardDAO.detailBoard(id);
        HashMap<String, Object> view = new HashMap<>();
        Integer intId = id.intValue();
        view.put("viewCnt", dto.getViewCnt());
        view.put("id", intId);

        boardDAO.incViewCnt(view);
        return boardDAO.detailBoard(id);
    }

    // 게시물 수정
    @Transactional
    @Override
    public Long modifyBoard(ModifyBoardRequestDTO modifyBoardRequestDTO) {
        boardDAO.modifyBoard(modifyBoardRequestDTO);
        return modifyBoardRequestDTO.getBoardId();
    }

    // 게시물 삭제
    @Transactional
    @Override
    public Long deleteBoard(Long id) {
        boardDAO.deleteBoard(id);
        return id;
    }
}
