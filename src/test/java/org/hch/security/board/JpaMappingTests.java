package org.hch.security.board;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;

import org.hch.security.board.model.Board;
import org.hch.security.board.repository.BoardRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class JpaMappingTests {
	private final String title = "제목";
	private final String content = "내용";
	
	@Autowired
	private BoardRepository boardRepository;
	
	@BeforeEach
	public void init() {
		boardRepository.save(Board.builder()
				.title(title)
				.content(content)
				.regdate(LocalDateTime.now())
				.updateDate(LocalDateTime.now()).build());
	}																		
	
	@Test
	public void test() {
		Board board= boardRepository.getById((long) 1);
		assertThat(board.getTitle(), is(title));
		assertThat(board.getContent(), is(content));
	}
}
