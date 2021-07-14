package com.system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.AccessLevel;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDTO {
    private String username;
	private String password;
}
