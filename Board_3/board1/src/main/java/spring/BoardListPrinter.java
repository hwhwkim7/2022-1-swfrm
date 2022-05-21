package spring;

import java.util.Collection;

public class BoardListPrinter {
	private BoardDao boardDao;
	private BoardPrinter printer;

	public BoardListPrinter(BoardDao boardDao, BoardPrinter printer) {
		this.boardDao = boardDao;
		this.printer = printer;
	}
	
	public void printAll() {
		Collection<Board> boards = boardDao.selectAll();
		boards.forEach(b->printer.print(b));
	}
}
