package org.hch.security.board.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hch.security.board.model.Board;
import org.hch.security.board.model.BoardDto;
import org.springframework.validation.Errors;

public interface BoardService {
	public List<BoardDto> getBoardList();
	
	public Board save(@Valid Board board);
	
	public void update(Long idx, BoardDto dto);
	
	public void delete(Long idx);
	
	public BoardDto detail(Long idx);
	
	public Map<String, String> validateHandling(Errors errors);
}
