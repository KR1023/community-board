package mybatis.communityBoard.controller;

import lombok.RequiredArgsConstructor;
import mybatis.communityBoard.dto.account.AddAccountRequestDTO;
import mybatis.communityBoard.dto.account.ModifyAccountRequestDTO;
import mybatis.communityBoard.service.account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    /**
     * 회원 목록
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity accountList(@RequestParam (value="pageNum", required = false) String pageNum,
                                      @RequestParam (value = "pageSize", required = false) String pageSize,
                                        @RequestParam (value = "keyword", required = false) String keyword){
        return new ResponseEntity(accountService.accList(keyword, pageNum, pageSize), HttpStatus.OK);
    }

    /**
     * 회원 추가
     * @param addAccountRequestDTO
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity addAccount(@RequestBody AddAccountRequestDTO addAccountRequestDTO){
        return new ResponseEntity(accountService.addAccount(addAccountRequestDTO), HttpStatus.OK);
    }

    /**
     * 회원 상세
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity detailAccount(@PathVariable ("id") Long id){
        return new ResponseEntity(accountService.detailAccount(id), HttpStatus.OK);
    }

    /**
     * 회원 수정
     * @param dto
     * @return
     */
    @PutMapping("/modify")
    public ResponseEntity modifyAccount(@RequestBody ModifyAccountRequestDTO dto){
        return new ResponseEntity(accountService.modifyAccount(dto), HttpStatus.OK);
    }

    /**
     * 회원 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAccount(@PathVariable ("id") Long id){
        return new ResponseEntity(accountService.deleteAccount(id), HttpStatus.OK);
    }

    /**
     * ID 중복 확인
     * @param request
     * @return
     */
    @PostMapping("/check-dup")
    public ResponseEntity checkDuplicate(HttpServletRequest request){
        String id = request.getParameter("id");
        return new ResponseEntity(accountService.checkDup(id), HttpStatus.OK);
    }
}
