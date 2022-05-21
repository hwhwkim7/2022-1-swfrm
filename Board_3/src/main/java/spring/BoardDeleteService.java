package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class BoardDeleteService {
    @Autowired
    private BoardDao boardDao;

    public void delete(String title, String password) {
        Board board = boardDao.selectByTitle(title);
        if (board == null){
            System.out.printf("\n게시글이 없습니다.\n");
            return;
        }
        if (!board.getPassword().equals(password)) {
            System.out.printf("\n비밀번호가 일치하지 않습니다.\n");
            return;
        }

        boardDao.delete(board);
        System.out.printf("\n게시글을 삭제했습니다.\n");
    }
}
