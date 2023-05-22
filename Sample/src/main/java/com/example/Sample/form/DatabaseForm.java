package com.example.Sample.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseForm {
	private Integer id;
	@NotBlank
	private String name;
	@NotNull
	@PositiveOrZero
	private Integer age;
	
	private Boolean newData;
}
