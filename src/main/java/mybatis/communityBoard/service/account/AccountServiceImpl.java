package mybatis.communityBoard.service.account;

import mybatis.communityBoard.dao.AccountDAO;
import mybatis.communityBoard.dto.account.AccountListResponseDTO;
import mybatis.communityBoard.dto.account.AddAccountRequestDTO;
import mybatis.communityBoard.dto.account.DetailAccountResponseDTO;
import mybatis.communityBoard.dto.account.ModifyAccountRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDAO accountDAO;

    // 회원 목록
    @Transactional
    @Override
    public List<AccountListResponseDTO> accList(String keyword, String pageNum, String pageSize){
        Integer num = 0;
        Integer size = 10;

        if(pageNum != null){
            num = Integer.parseInt(pageNum) * 10;
        }

        if(pageSize != null){
            size = Integer.parseInt(pageSize);
        }

        Map<String, Object> paging = new HashMap<>();
        paging.put("num",num);
        paging.put("size", size);
        paging.put("userId", keyword);

        return accountDAO.findAcc(paging);
    }

    // total
    @Override
    public int countAll(String keyword){
        return accountDAO.countAll(keyword);
    }


    // 회원 추가
    @Transactional
    @Override
    public Long addAccount(AddAccountRequestDTO addAccountRequestDTO) {
        accountDAO.addAccount(addAccountRequestDTO);
        return addAccountRequestDTO.getId();
    }

    
    // 회원 상세
    @Transactional
    @Override
    public DetailAccountResponseDTO detailAccount(Long id) {
        return accountDAO.detailAccount(id);
    }

    // 회원 수정
    @Transactional
    @Override
    public DetailAccountResponseDTO modifyAccount(ModifyAccountRequestDTO dto) {
        accountDAO.modifyAccount(dto);
        return accountDAO.detailAccount(dto.getId());
    }

    // 회원 삭제
    @Transactional
    @Override
    public Long deleteAccount(Long id) {
        accountDAO.deleteAccount(id);
        return id;
    }

    // 관리자 페이지 로그인
    @Transactional
    @Override
    public int login(String userId, String userPwd) {
        Map<String, String> info = new HashMap<String, String>();
        info.put("userId",userId);
        info.put("userPwd", userPwd);
        if(userId.equals("admin")){
            return accountDAO.login(info);
        }else{
            return 0;
        }
    }

    // 사용자 로그인
    @Transactional
    @Override
    public int userLogin(String userId, String userPwd) {
        Map<String, String> info = new HashMap<String, String>();
        info.put("userId",userId);
        info.put("userPwd", userPwd);
        return accountDAO.login(info);
    }


    // 유저 PK
    @Transactional
    @Override
    public Long getPk(String userId) {
        return accountDAO.getPk(userId);
    }

    // ID 중복 확인
    @Transactional
    @Override
    public int checkDup(String id) {
        return accountDAO.checkDup(id);
    }

}
