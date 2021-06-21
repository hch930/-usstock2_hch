package org.hch.security.board.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BoardDto {
	@Id
	private Long bno;
	private String title;
	private String content;
	private Date regdate;
	private Date updateDate;
}