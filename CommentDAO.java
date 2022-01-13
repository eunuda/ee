package org.edu.comment;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.edu.common.DAO;

public class CommentDAO extends DAO {

	public CommentVO insertComment(String name, String content) {
		String sql = "select value from id_repository where name = 'COMMENT'";
		String insertSql = "insert into comments values?(?,?,?)";
		String updateSql = "update id_repository set value =? where name = 'COMMENT'";
		CommentVO comment = null;

		connect();

		try {
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			int seq = -1;
			if (rs.next()) {
				seq = rs.getInt("Value");
			}

			psmt = conn.prepareStatement(insertSql);
			psmt.setInt(1, seq);
			psmt.setString(2, name);
			psmt.setString(3, content);
			int r = psmt.executeUpdate();
			System.out.println(r + "입력됨.");

			// 비정상적으로 처리

			if (r < 1) {
				return null;
			}
				comment = new CommentVO();
				comment.setId(seq);
				comment.setName(name);
				comment.setContent(content);

				//시퀀스 증가처리
				psmt = conn.prepareStatement(updateSql);
				psmt = setInt(1,++seq);
				
				r = psmt.executeUpdate();
				
				//정상완료
				return comment;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;

	}

	private PreparedStatement setInt(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CommentVO> selectAll() {

		List<CommentVO> list = new ArrayList<>();

		try {
			connect();
			psmt = conn.prepareStatement("select * from comment");

		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

}
