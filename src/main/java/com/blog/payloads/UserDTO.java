package com.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {
	private int id;

	@NotEmpty
	@Size(min = 4, message = "Username must be minimum 4 characters")
	private String name;

	@Email(message = "Email ID is not valid")
	private String email;

	@NotEmpty
	@Size(min = 4, max = 10, message = "Message must be of 4 to 10 characters")
	private String password;

	@NotEmpty
	private String about;
}
