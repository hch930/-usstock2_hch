package org.hch.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name = "member", uniqueConstraints = {@UniqueConstraint(name = "NAME_ID_UNIQUE", columnNames = {"ID", "EMAIL"})})
public class Member {
	
	@Id
	private String id;
	@Column(nullable = false)
	@NotBlank
	@Length(min = 4)
	private String name;
	@Column(nullable = false)
	@NotBlank
	@Length(min = 4)
	private String password;
	@Transient
	@NotBlank
	private String confirmPassword;
	@Column(nullable = false)
	@NotBlank
	@Email
	private String email;
	@Column(nullable = false)
	private boolean enabled;
	@CreationTimestamp
	private Date regDate;
	@CreationTimestamp
	private Date updateDate;
	
}
