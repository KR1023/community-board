package mybatis.communityBoard.dao;

import mybatis.communityBoard.dto.account.AccountListResponseDTO;
import mybatis.communityBoard.dto.account.AddAccountRequestDTO;
import mybatis.communityBoard.dto.account.DetailAccountResponseDTO;
import mybatis.communityBoard.dto.account.ModifyAccountRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AccountDAO {
    // 회원 목록
    List<AccountListResponseDTO> findAcc(Map<String, Object> list);

    // total
    int countAll(String userId);

    // 회원 추가
    Long addAccount(AddAccountRequestDTO addAccountRequestDTO);

    // 회원 상세
    DetailAccountResponseDTO detailAccount(Long id);

    // 회원 수정
    Long modifyAccount(ModifyAccountRequestDTO modifyAccountRequestDTO);

    // 회원 삭제
    Long deleteAccount(Long id);

    // 관리자 로그인
    int login(Map<String, String> info);

    // 유저 PK
    Long getPk(String userId);

    // ID 중복 확인
    int checkDup(String id);
}
