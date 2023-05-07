package com.team2.findmytown.dto.request;


import com.team2.findmytown.domain.entity.Role;
import lombok.*;

@Builder
@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String token;
    private String id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private Role role;
    private Boolean isFemale;

}
