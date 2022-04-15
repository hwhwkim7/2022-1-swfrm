package spring;

import java.time.LocalDateTime;

public class BoardRegisterService {
	private BoardDao boardDao;

	public BoardRegisterService(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	public Long regist(RegisterRequest req) {
		
		Board board = boardDao.selectByTitle(req.getTitle());
		
		if (board != null) {
			throw new DuplicateBoardException("dup title " + req.getTitle());
		}
		
		Board newBoard = new Board(
				req.getTitle(), req.getContent(), req.getAuthor(), req.getPassword(),
				LocalDateTime.now(), LocalDateTime.now());
		
		boardDao.insert(newBoard);
		
		return newBoard.getId();
	}
}
