package mybatis.communityBoard.dto.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class DetailAccountResponseDTO {
    private Long id;                    // PK
    private String userId;              // 유저 ID
    private String username;            // 유저 이름
    private String email;               // Email

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDt;    // 생성일
}
