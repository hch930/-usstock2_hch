package org.hch.security.board;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.hch.security.board.model.Board;
import org.hch.security.board.model.BoardDto;
import org.hch.security.board.repository.BoardRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JpaMappingTests {
	private RestTemplate restTemplate;
	
	@LocalServerPort
    private int port;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Before
	public void setUp() throws Exception {
		restTemplate = new RestTemplate();
	}
	
/*	
	@Test
	public void save() {	
		String inject_title = "title";
		String inject_content = "content";
		BoardDto dto = BoardDto.builder()
				.title(inject_title)
				.content(inject_content).build();	
		
		Board tmp = boardRepository.save(dto.toEntity());
		
		assertThat(tmp.getTitle()).isEqualTo(inject_title);
		assertThat(tmp.getContent()).isEqualTo(inject_content);
		
		List<Board> list = boardRepository.findAll();
		assertThat(list.get(0).getTitle()).isEqualTo(inject_title);
		assertThat(list.get(0).getContent()).isEqualTo(inject_content);
	}

	@Test
	public void update_test() throws Exception{
		String title = "title";
		String content = "content";
		Board board = boardRepository.save(Board.builder()
				.title(title)
				.content(content)
				.regdate(LocalDateTime.now())
				.updateDate(LocalDateTime.now())
				.build());
		Long id = board.getIdx();
		String change_title = "change_title";
		String change_content = "change_content";
		
		BoardDto dto = BoardDto.builder()
				.title(change_title)
				.content(change_content)
				.updateDate(LocalDateTime.now()).build();
		
		String url = "http://localhost:"+port+"/board/update/"+id;
		System.out.println(url);
		HttpEntity<BoardDto> requestEntity = new HttpEntity<>(dto);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
		
		List<Board>list = boardRepository.findAll();
		
		assertThat(list.get(0).getTitle()).isEqualTo(change_title);
		assertThat(list.get(0).getContent()).isEqualTo(change_content);	
	}	
*/	
	@Test
	public void delete_test() {
		String title = "title";
		String content = "content";
		
		Board board = boardRepository.save(Board.builder()
				.title(title)
				.content(content)
				.regdate(LocalDateTime.now())
				.updateDate(LocalDateTime.now())
				.build());
		Long id = board.getIdx();
		String url = "http://localhost:"+port+"/board/delete/"+id;
		System.out.println(url);
		HttpEntity<Board> requestEntity = new HttpEntity<>(board);
		ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Long.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		List<Board>list = boardRepository.findAll();
		assertThat(list.size()).isEqualTo(0);
	}
}
