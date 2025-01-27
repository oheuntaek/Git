package score.dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import score.bean.ScoreDTO;

public class ScoreDAO {
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		DataSource ds;
		
		String score_insert = "INSERT INTO score VALUES (?,?,?,?,?,?,?,SYSDATE)";
		String score_update = "UPDATE score SET name = ?, kor=?, eng=?,mat=?,tot=?,avg=? " + 
				" where studNo =?";
		String score_delete = "DELETE score where studNo = ?";
		String score_select = "SELECT * FROM score WHERE studNo =?";
		String score_selectList = "select studNo, name, kor, eng, mat, tot, avg,logtime " + 
				" from (select rownum rn,tt.*from(select * from score order by studNo asc)tt) where rn>=? and rn<=?";
		String score_count = "select count(*) as count from score";
		public ScoreDAO() {
			try {
				Context context = new InitialContext();
				ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void close() {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 성적 입력
		public int scoreInput(ScoreDTO dto) {
			int su = 0;
			//String sql = "INSERT INTO score VALUES (?,?,?,?,?,?,?,SYSDATE)";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(score_insert);
				pstmt.setString(1, dto.getStudNo());
				pstmt.setString(2, dto.getName());
				pstmt.setInt(3, dto.getKor());
				pstmt.setInt(4, dto.getEng());
				pstmt.setInt(5, dto.getMat());
				pstmt.setInt(6, dto.getTot());
				pstmt.setDouble(7, dto.getAvg());
				su = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close();
			}
			return su;
		}
		
		// 성적 삭제
		public int scoreDel(String studNo) {
			int su = 0;
			//String sql = "DELETE score where studNo = ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(score_delete);
				pstmt.setString(1, studNo);
				su = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close();
			}
			return su;
		}
		
		// 성적 수정
		public int scoreUpdate(ScoreDTO dto) {
			System.out.println("업데이트");
			int su = 0;
			//String score_update = "UPDATE score SET name = ?, kor=?, eng=?,mat=?,tot=?,avg=?  where studNo =?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(score_update);
				pstmt.setString(1,dto.getName() );
				pstmt.setInt(2, dto.getKor());
				pstmt.setInt(3, dto.getEng());
				pstmt.setInt(4, dto.getMat());
				pstmt.setInt(5, dto.getTot());
				pstmt.setDouble(6, dto.getAvg());
				pstmt.setString(7, dto.getStudNo());
				su = pstmt.executeUpdate();
				System.out.println("업데이트 끝");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close();
			}
			return su;
		}
		// 성적 보기
		public ScoreDTO scoreView(String studNo) {
			ScoreDTO dto = new ScoreDTO();
			//String sql = "SELECT * FROM score WHERE studNo =?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(score_select);
				pstmt.setString(1, studNo);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					dto.setAvg(rs.getInt("avg"));
					dto.setTot(rs.getInt("tot"));
					dto.setEng(rs.getInt("eng"));
					dto.setMat(rs.getInt("mat"));
					dto.setKor(rs.getInt("kor"));
					dto.setName(rs.getString("name"));
					dto.setStudNo(rs.getString("studNo"));
					dto.setLogtime(rs.getString("logtime"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close();
			}
			return dto;
		}
		
		// 성적 목록
		public List<ScoreDTO> scoreList(int startNum, int endNum) {
		    List<ScoreDTO> list = new ArrayList<ScoreDTO>();
			/*String sql = "select studNo, name, kor, eng, mat, tot, avg,logtime " + 
					"from (select rownum rn,tt.*from(select * from score order by studNo asc)tt) where rn>=? and rn<=?";*/
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(score_selectList);
				pstmt.setInt(1, startNum);
				pstmt.setInt(2, endNum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					ScoreDTO dto = new ScoreDTO();
					dto.setAvg(rs.getInt("avg"));
					dto.setTot(rs.getInt("tot"));
					dto.setEng(rs.getInt("eng"));
					dto.setMat(rs.getInt("mat"));
					dto.setKor(rs.getInt("kor"));
					dto.setName(rs.getString("name"));
					dto.setStudNo(rs.getString("studNo"));
					dto.setLogtime(rs.getString("logtime"));
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close();
			}
			return list;
		}
		
		// 총 글수
		public int scoreCount() {
			int totalA = 0;
			//String sql = "select count(*) as count from score";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(score_count);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					totalA = rs.getInt("count");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close();
			}
			return totalA;
		}
		
}
