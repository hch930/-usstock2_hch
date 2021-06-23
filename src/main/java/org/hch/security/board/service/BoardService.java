package org.hch.security.board.service;

import org.hch.security.board.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
	Page<Board> findBoardList(Pageable pageable);
}
