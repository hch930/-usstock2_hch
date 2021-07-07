package org.hch.security.forum.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ForumDto {
	private Long idx;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime regdate;
	private LocalDateTime updateDate;
	private Integer categoryNo;
	
	@Builder
	public ForumDto(Long idx, String title, String content, String writer, LocalDateTime regdate, LocalDateTime updateDate, Integer categoryNo) {
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.updateDate = updateDate;
		this.categoryNo = categoryNo;
	}
	
	@Builder
	public ForumDto(String title, String content, LocalDateTime updateDate) {
		this.title = title;
		this.content = content;
		this.updateDate = updateDate;
	}
}
