package com.forcelate.entity;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
public class User {

	@Setter
	private long id;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	};

	@Getter
	@Setter
	@Column(name = "username")
	private String username;

	@Getter
	@Setter
	@Column(name = "password")
	@JsonIgnore
	private String password;

	@Getter
	@Setter
	@Column(name = "salary")
	private long salary;

	@Getter
	@Setter
	@Column(name = "age")
	private int age;
}
