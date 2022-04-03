package com.sda.inTeams.DTO;

import lombok.Data;

@Data
public class RegisterTeamDTO implements RegisterDto {

    private String teamName;

    private String username;
    private String password;
    private String confirmPassword;

    private String firstName;
    private String lastName;
}
