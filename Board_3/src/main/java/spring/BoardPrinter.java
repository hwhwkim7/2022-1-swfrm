package spring;

public class BoardPrinter {

	public void printTitle(Board board) {
		System.out.printf("#%d [ %s ] [ %s ]\n", board.getId(), board.getTitle(), board.getAuthor());
	}

	public void print(Board board) {
		System.out.printf(
				"\n==============================\n" + "제목: [ %s ]\n" + "-----------------------------\n" + "작성일: %tF %tT\n"
						+ "수정일: %tF %tT\n" + "작성자: %s\n" + "-----------------------------\n" +
						"내용\n" + ":%s\n",
				board.getTitle(), board.getCreatedAt(), board.getCreatedAt(), board.getUpdatedAt(), board.getUpdatedAt(), board.getAuthor(), board.getContent());
	}
}
