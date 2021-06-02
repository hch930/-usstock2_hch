package org.hch.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@Length(min = 4)
	private String id;
	@Column(unique = true)
	private String role;
}
