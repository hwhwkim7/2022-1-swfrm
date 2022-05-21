package spring;

public class BoardInfoPrinter {
	private BoardDao boardDao;
	private BoardPrinter printer;
	
	public void printBoardInfo(String title, String password) {
		Board board = boardDao.selectByTitle(title);
		if(board == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		printer.print(board);;
		System.out.println();
	}
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public void setPrinter(BoardPrinter printer) {
		this.printer = printer;
	}
}
