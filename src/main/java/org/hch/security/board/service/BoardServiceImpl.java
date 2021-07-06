package org.hch.security.board.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hch.security.board.model.Board;
import org.hch.security.board.model.BoardDto;
import org.hch.security.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardRepository boardRepository;
	
	@Override
	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();
		
		for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
	}
	
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
	public Board save(Board board) {
		return boardRepository.save(board.toEntity());
	}
	
	@Override
	@Transactional
	public void update(Long idx, BoardDto dto) {
		Board board = boardRepository.findById(idx).orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다" + idx));
		
		board.update(dto.getTitle(), dto.getContent(), LocalDateTime.now());
	}
	
	@Override
	@Transactional
	public void delete(Long idx) {
		Board board = boardRepository.findById(idx).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다" + idx));
		boardRepository.delete(board);
	}
	
	@Override
	public BoardDto detail(Long idx) {
		 Optional<Board> boards = boardRepository.findById(idx);
		 Board board = boards.get();
	        BoardDto boardDto = BoardDto.builder()
	                .idx(board.getIdx())
	                .title(board.getTitle())
	                .content(board.getContent())
	                .writer(board.getWriter())
	                .regdate(board.getRegdate())
	                .build();

	        return boardDto;
	}
}
	