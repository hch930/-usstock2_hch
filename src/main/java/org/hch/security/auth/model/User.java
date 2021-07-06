package org.hch.security.auth.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	@Column(name = "username")
	@NotBlank(message = "사용할 아이디를 입력해주세요")
	private String username;

	@Column(name = "email")
	@Email(message = "유효한 이메일을 입력해주세요")
	@NotBlank(message = "이메일을 입력해주세요")
	private String email;

	@Column(name = "password")
	@Length(min = 5, message = "비밀번호는 5글자 이상이어야 합니다.")
	@NotBlank(message = "*사용할 비밀번호를 입력해주세요")
	private String password;

	@Transient
	@NotBlank(message = "사용할 비밀번호를 다시 입력해주세요")
	private String confirmpassword;

	@Column(name = "name")
	@NotBlank(message = "이름을 입력해주세요")
	private String name;

	@Column(name = "enabled")
	private boolean enabled;

	@CreationTimestamp
	@Column(name = "regdate")
	private Date regDate;

	@CreationTimestamp
	@Column(name = "updatedate")
	private Date updateDate;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
}
