package org.hch.security.board.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	public List<BoardDto> getBoardList() {
		List<Board> boards = boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		for(Board board : boards) {
			BoardDto boardDto = BoardDto.builder()
					.idx(board.getIdx())
					.title(board.getTitle())
					.regdate(board.getRegdate())
					.updateDate(board.getUpdateDate())
					.build();
			
			boardDtoList.add(boardDto);
		}
		return boardDtoList;
	}
	
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
	
	@Override
	@Transactional
	public void delete(Long id) {
		Board board = boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다" + id));
		boardRepository.delete(board);
	}
}
	