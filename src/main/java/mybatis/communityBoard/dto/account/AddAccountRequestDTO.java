package mybatis.communityBoard.dto.account;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class AddAccountRequestDTO {
    private Long id;                // 기본키
    private String userId;          // 유저 ID
    private String userPwd;         // 유저 암호
    private String username;        // 유저 이름
    private String email;           // Email

    public Long getId() {
        return id;
    }

    public AddAccountRequestDTO(String userId, String userPwd, String username, String email) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.username = username;
        this.email = email;
    }
}
