package spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class BoardWriteService {

	@Autowired
	private BoardDao boardDao;

	public Long write(WriteRequest req) {
		Board newBoard = new Board(
				req.getTitle(), req.getAuthor(), req.getPassword(),
				LocalDateTime.now(), LocalDateTime.now(), req.getContent());
		boardDao.insert(newBoard);
		System.out.printf("\n글을 등록하였습니다.\n");

		return newBoard.getId();
	}

}
