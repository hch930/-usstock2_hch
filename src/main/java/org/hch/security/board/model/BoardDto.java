package org.hch.security.board.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board")
public class BoardDto {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "bno")
	private Long bno;
	
	@Column(name = "title")
	@NotBlank(message = "제목을 입력해주세요")
	private String title;
	
	@Column(name = "content")
	@NotBlank(message = "내용을 입력해주세요")
	private String content;
	
	@Column(name = "regdate")
	private Date regdate;
	
	@Column(name = "updateDate")
	private Date updateDate;
}