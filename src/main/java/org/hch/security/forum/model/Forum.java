package org.hch.security.forum.model;

//https://victorydntmd.tistory.com/208

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import groovy.transform.ToString;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
public class Forum {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private Long idx;
	
	@Column
	@NotBlank(message = "게시글 제목을 입력해주세요")
	private String title;
	
	@Column
	@NotBlank(message = "내용을 입력해주세요")
	private String content;
	
	@Column
	private String writer;
	
	@Column
	private LocalDateTime regdate;
	
	@Column
	private LocalDateTime updateDate;
	
	@Column
	private Integer categoryNo;

	@Builder
	public Forum(String title, String content, String writer, LocalDateTime regdate, LocalDateTime updateDate, Integer categoryNo) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.updateDate = updateDate;
		this.categoryNo = categoryNo;
	}
	
	public void update(String title, String content, LocalDateTime updateDate) {
		this.title = title;
		this.content = content;
		this.updateDate = updateDate;
	}
	
	public Forum toEntity() {
		 Forum forum = Forum.builder()
				.title(title)
				.content(content)
				.writer(writer)
				.regdate(LocalDateTime.now())
				.updateDate(LocalDateTime.now())
				.categoryNo(categoryNo).build();
		 return forum;
	}
}