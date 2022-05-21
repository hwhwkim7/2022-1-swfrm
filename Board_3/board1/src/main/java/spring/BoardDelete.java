package spring;

public class BoardDelete {

    private BoardDao boardDao;

    public BoardDelete(BoardDao boardDao){
        this.boardDao = boardDao;
    }

    public void deleteContent(String title, String password) {
        Board board = boardDao.selectByTitle(title);
        if (board == null)
            throw new BoardNotFoundException();

        boardDao.delete(board);
    }
}
