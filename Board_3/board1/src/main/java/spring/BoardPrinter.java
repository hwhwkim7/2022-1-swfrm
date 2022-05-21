package spring;

public class BoardPrinter {
	public void print(Board board) {
		if(board.getUpdateAt().equals(board.getCreateAt())){
			System.out.printf(
					"아이디 : %d, 제목 : %s, 작성자 : %s, 등록일 : %tF %tT\n내용: %s\n",
					board.getId(), board.getTitle(), board.getAuthor(),
					board.getCreateAt(), board.getCreateAt(), board.getContent());
		} else {
			System.out.printf(
					"아이디 : %d, 제목 : %s, 작성자 : %s, 등록일 : %tF %tT, 수정일 : %tF %tT\n내용: %s\n",
					board.getId(), board.getTitle(), board.getAuthor(), board.getCreateAt(), board.getCreateAt(),
					board.getUpdateAt(), board.getUpdateAt(), board.getContent());
		}

	}
}
