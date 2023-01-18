package com.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
	
	private Integer categoryID;
	
	@NotBlank
	@Size(min = 4, message = "Minimum size should be 4 character")
	private String categoryTitle;
	
	@NotBlank
	@Size(max = 100, message = "Maximum of 10 characters")
	private String categoryDescription;
}
