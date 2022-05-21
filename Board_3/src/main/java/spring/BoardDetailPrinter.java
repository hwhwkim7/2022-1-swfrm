package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class BoardDetailPrinter {

	@Autowired
	private BoardDao boardDao;
	@Autowired
	private BoardPrinter printer;
	
	public void printBoardDetail(String title, String password) {
		Board board = boardDao.selectByTitle(title);
		if (board == null) {
			System.out.printf("\n게시글이 없습니다.\n");
			return;
		}
		
		if (!board.getPassword().equals(password)) {
			System.out.printf("\n비밀번호가 일치하지 않습니다.\n");
			return;
		}

		printer.print(board);
	}
}
