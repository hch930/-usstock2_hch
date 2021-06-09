package org.hch.auth.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "member", uniqueConstraints = {@UniqueConstraint(name = "NAME_EMAIL_UNIQUE", columnNames = {"USERNAME", "EMAIL"})})
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	@NotBlank
	@Length(min = 4)
	private String username;
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
	private Boolean enabled;
	@CreationTimestamp
	private Date regDate;
	@CreationTimestamp
	private Date updateDate;
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "member_role",
			joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rold_id", referencedColumnName ="id"))
	private Set<Role> roles;
}
