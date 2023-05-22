package com.example.Sample.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Database {
	@Id
	private Integer id;
	private String name;
	private Integer age;
}
