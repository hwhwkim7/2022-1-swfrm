package spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class BoardListPrinter {

	@Autowired
	private BoardDao boardDao;
	@Autowired
	private BoardPrinter printer;
	
	public void printAllTitles() {
		Collection<Board> boards = boardDao.selectAll();

		if (boards.size() == 0) {
			System.out.println("\n게시글이 없습니다.");
			return;
		}
		System.out.println("\n=============================");
		System.out.println("게시글 목록");
		System.out.println("=============================");

		boards.forEach(b -> printer.printTitle(b));
	}
}
