package org.hch.security.board.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
	private String title;
	private String content;
	private LocalDateTime regdate;
	private LocalDateTime updateDate;
	
	@Builder
	public BoardDto(String title, String content, LocalDateTime regdate, LocalDateTime updateDate) {
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.updateDate = updateDate;
	}
	
	@Builder
	public BoardDto(String title, String content, LocalDateTime updateDate) {
		this.title = title;
		this.content = content;
		this.updateDate = updateDate;
	}
	
	public Board toEntity() {
		return Board.builder()
				.title(title)
				.content(content)
				.regdate(LocalDateTime.now())
				.updateDate(LocalDateTime.now()).build();
	}
}
