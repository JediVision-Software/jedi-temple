package com.jedivision.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@EqualsAndHashCode
@Entity(name = "users")
@ToString
public class User {

	private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Getter
	private String username;

	@Getter
	private String password;

	private Role role;

	@Enumerated(EnumType.STRING)
	public Role getRole() {
		return role;
	}
}
