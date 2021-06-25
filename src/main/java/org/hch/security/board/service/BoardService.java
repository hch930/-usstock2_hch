package org.hch.security.board.service;

import org.hch.security.board.model.Board;
import org.hch.security.board.model.BoardDto;

public interface BoardService {
	public Board save(BoardDto dto);
	
	public void update(Long id, BoardDto dto);
}
