package mybatis.communityBoard.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Getter
@Component
@NoArgsConstructor
public class Account {
    private Long id;                // 기본키
    private String username;        // 유저 이름
    private String userId;          // 유저 ID
    private String userPwd;         // 유저 암호
    private String email;           // Email
    private LocalDateTime createdDt;// 생성일

    @Builder
    public Account(String username, String userId, String userPwd, String email, LocalDateTime createdDt){
        this.userId = userId;
        this.userPwd = userPwd;
        this.username = username;
        this.email = email;
        this.createdDt = createdDt;
    }
}
