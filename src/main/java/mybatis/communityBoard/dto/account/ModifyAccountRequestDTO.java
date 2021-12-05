package mybatis.communityBoard.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ModifyAccountRequestDTO {
    private Long id;
    private String username;
    private String userPwd;
    private String email;
}
