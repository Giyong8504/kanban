package controllers.member;

import lombok.*;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor // 두개를 추가해야 public 범위의 기본 생성자가 추가 된다.
public class UserForm {
    private long userNo;
    private String userId;
    private String userPw;
    private String userPwRe;
    private String userNm;
    private String email;
    private String mobile;
    private boolean agree;

}
