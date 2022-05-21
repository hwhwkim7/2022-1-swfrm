package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class BoardDao {

	private JdbcTemplate jdbcTemplate;

	public BoardDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Board selectByTitle(String title) {
		List<Board> results = jdbcTemplate.query(
				"select * from BOARD where TITLE = ?",
				new RowMapper<Board>() {
					@Override
					public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
						Board board = new Board(
								rs.getString("TITLE"),
								rs.getString("AUTHOR"),
								rs.getString("PASSWORD"),
								rs.getTimestamp("CREATEDAT").toLocalDateTime(),
								rs.getTimestamp("UPDATEDAT").toLocalDateTime(),
								rs.getString("CONTENT"));
						board.setId(rs.getLong("ID"));
						return board;
					}
				}, title);
		return results.isEmpty() ? null : results.get(0);
	}


	public void insert(Board board) {
		long start = System.currentTimeMillis();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
						"insert into BOARD (TITLE, PASSWORD, AUTHOR, CREATEDAT, UPDATEDAT, CONTENT) " +
								"values (?, ?, ?, ?, ?, ?)",
						new String[]{"ID"});
				// 인덱스 파라미터 값 설정
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getPassword());
				pstmt.setString(3, board.getAuthor());
				pstmt.setTimestamp(4,
						Timestamp.valueOf(board.getCreatedAt()));
				pstmt.setTimestamp(5,
						Timestamp.valueOf(board.getUpdatedAt()));
				pstmt.setString(6, board.getContent());
				// 생성한 PreparedStatement 객체 리턴
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		board.setId(keyValue.longValue());
		/*long end = System.currentTimeMillis();
		String methodName = board.getClass().getEnclosingMethod().getName();
		System.out.printf("[ %d ] [ %s() ] [ %d ]", end, methodName, (end - start));*/
	}

	public void update(Board board) {
		jdbcTemplate.update(
				"update BOARD set CONTENT = ?, UPDATEDAT = ? where TITLE = ?",
				board.getContent(), board.getUpdatedAt(), board.getTitle());
	}

	public void delete(Board board) {
		jdbcTemplate.update(
				"delete from BOARD where title = ?",
				board.getTitle());
	}

	public List<Board> selectAll() {
		List<Board> results = jdbcTemplate.query(
				"select * from BOARD",
				(ResultSet rs, int rowNum) -> {
					Board board = new Board(
							rs.getString("TITLE"),
							rs.getString("AUTHOR"),
							rs.getString("PASSWORD"),
							rs.getTimestamp("CREATEDAT").toLocalDateTime(),
							rs.getTimestamp("UPDATEDAT").toLocalDateTime(),
							rs.getString("CONTENT"));
					board.setId(rs.getLong("ID"));
					return board;
				});
		return results;
	}

	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from BOARD", Integer.class);
		return count;
	}
}
