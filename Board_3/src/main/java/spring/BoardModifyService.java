package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class BoardModifyService {

	@Autowired
	private BoardDao boardDao;

	public void modify(String title, String content, String password) {
		Board board = boardDao.selectByTitle(title);
		if (board == null) {
			System.out.printf("\n게시글이 없습니다.\n");
			return;
		}
		if (!board.getPassword().equals(password)) {
			System.out.printf("\n비밀번호가 일치하지 않습니다.\n");
			return;
		}
		
		board.setContent(content);
		board.setUpdatedAtNow();
		
		boardDao.update(board);
		System.out.printf("\n게시글을 수정하였습니다.\n");
	}

}
