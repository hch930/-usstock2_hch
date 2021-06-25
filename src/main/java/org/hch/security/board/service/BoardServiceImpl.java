package org.hch.security.board.service;

import java.time.LocalDateTime;

import org.hch.security.board.model.Board;
import org.hch.security.board.model.BoardDto;
import org.hch.security.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardRepository boardRepository;
	
	@Override
	@Transactional
	public Board save(BoardDto dto) {
		return boardRepository.save(dto.toEntity());
	}
	
	@Override
	@Transactional
	public void update(Long id, BoardDto dto) {
		Board board = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다" + id));
		
		board.update(dto.getTitle(), dto.getContent(), LocalDateTime.now());
	}
}
	