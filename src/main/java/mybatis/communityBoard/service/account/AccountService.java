package mybatis.communityBoard.service.account;

import mybatis.communityBoard.dto.account.AccountListResponseDTO;
import mybatis.communityBoard.dto.account.AddAccountRequestDTO;
import mybatis.communityBoard.dto.account.DetailAccountResponseDTO;
import mybatis.communityBoard.dto.account.ModifyAccountRequestDTO;

import java.util.List;

public interface AccountService {
    // 회원 목록
    List<AccountListResponseDTO> accList(String keyword, String pageNum, String pageSize);

    // 회원 수
    int countAll(String keyword);

    // 회원 등록
    Long addAccount(AddAccountRequestDTO addAccountRequestDTO);

    // 회원 상세
    DetailAccountResponseDTO detailAccount(Long id);

    // 회원 수정
    DetailAccountResponseDTO modifyAccount(ModifyAccountRequestDTO dto);

    // 회원 삭제
    Long deleteAccount(Long id);

    // 관리자 로그인
    int login(String  userId, String userPwd);

    // 사용자 로그인
    int userLogin(String userId, String userPwd);

    // 유저 pk
    Long getPk(String userId);
    
    // ID 중복 확인
    int checkDup(String id);


}
