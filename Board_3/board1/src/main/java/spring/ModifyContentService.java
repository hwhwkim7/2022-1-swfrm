package spring;

public class ModifyContentService {

	private BoardDao boardDao;

	public void changeContent(String title, String newContent, String password) {
		Board board = boardDao.selectByTitle(title);
		if (board == null)
			throw new BoardNotFoundException();

		board.changeContent(password, newContent);

		boardDao.update(board);
	}

	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

}
