package org.hch.security.board.service;

import java.util.List;

import org.hch.security.board.model.Board;
import org.hch.security.board.model.BoardDto;

public interface BoardService {
	public List<BoardDto> getBoardList();
	
	public Board save(BoardDto dto);
	
	public void update(Long id, BoardDto dto);
	
	public void delete(Long id);
}
