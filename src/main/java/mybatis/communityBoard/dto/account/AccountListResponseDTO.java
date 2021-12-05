package mybatis.communityBoard.dto.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class AccountListResponseDTO {
    private String userId;
    private String username;
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDt;

//    public Account toEntity(){
//        return Account.builder()
//                .userId(userId)
//                .username(username)
//                .email(email)
//                .createdDt(createdDt)
//                .build();
//    }
}
