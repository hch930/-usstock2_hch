package org.hch.security.forum.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hch.security.forum.model.Forum;
import org.hch.security.forum.model.ForumDto;
import org.hch.security.forum.repository.ForumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ForumService{
	private final ForumRepository forumRepository;
	
	@Transactional
	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();
		
		for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
	}
	
	@Transactional
	public List<ForumDto> getBoardList() {
		List<Forum> forums = forumRepository.findAll();
		List<ForumDto> forumDtoList = new ArrayList<>();
		
		for(Forum forum : forums) {
			ForumDto forumDto = ForumDto.builder()
					.idx(forum.getIdx())
					.title(forum.getTitle())
					.regdate(forum.getRegdate())
					.updateDate(forum.getUpdateDate())
					.categoryNo(forum.getCategoryNo())
					.build();
			
			forumDtoList.add(forumDto);
		}
		return forumDtoList;
	}
	
	@Transactional
	public Forum save(Forum forum) {
		return forumRepository.save(forum.toEntity());
	}
	
	@Transactional
	public void update(Long idx, Forum forum) {
		Forum forums = forumRepository.findById(idx).orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다" + idx));
		forums.update(forum.getTitle(), forum.getContent(), LocalDateTime.now());
	}

	@Transactional
	public void delete(Long idx) {
		Forum forum = forumRepository.findById(idx).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다" + idx));
		forumRepository.delete(forum);
	}
	
	@Transactional
	public ForumDto detail(Long idx) {
		 Optional<Forum> forums = forumRepository.findById(idx);
		 Forum forum = forums.get();
		 ForumDto forumDto = ForumDto.builder()
	                .idx(forum.getIdx())
	                .title(forum.getTitle())
	                .content(forum.getContent())
	                .writer(forum.getWriter())
	                .regdate(forum.getRegdate())
	                .build();

	        return forumDto;
	}
}
	