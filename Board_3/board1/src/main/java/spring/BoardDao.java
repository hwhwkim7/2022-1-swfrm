package spring;

import java.util.Collection;
import java.util.HashMap;

public class BoardDao {

	private static long nextId = 0;

	private HashMap<String, Board> map = new HashMap<>();

	public Board selectByTitle(String title) {
		return map.get(title);
	}

	public void insert(Board board) {
		board.setId(++nextId);
		map.put(board.getTitle(), board);
	}

	public void update(Board board) {
		map.put(board.getTitle(), board);
	}
	
	public Collection <Board> selectAll() {
		return map.values();
	}

	public void delete(Board board) {
		map.remove(board.getTitle());
	}
}
