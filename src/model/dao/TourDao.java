package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import config.OracleInfo;
import model.vo.MemberVO;
import query.user.UserStringQuery;

public class TourDao {
	private static TourDao reviewDao = new TourDao();
	private TourDao() {}
	public static TourDao getInstance() {
		return reviewDao;
	}
	
	static {
		try {
			Class.forName(OracleInfo.DRIVER_NAME);
			System.out.println("드라이버 로딩 성공");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(OracleInfo.URL, OracleInfo.USER, OracleInfo.PASS);
		System.out.println("디비 연결 성공!");
		return conn;
	}// getConnect
	
	private void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
	}// closeAll

	private void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(ps, conn);
	}// closeAll
	
	public void register(MemberVO vo) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnect();
			pstmt = conn.prepareStatement(UserStringQuery.REGISTER_USER);
			pstmt.setString(1,vo.getUserName());
			pstmt.setInt(2,vo.getSsn());
			pstmt.setString(3,vo.getId());
			pstmt.setString(4,vo.getPassword());
			pstmt.setString(5,vo.getTel());
			pstmt.setString(6,vo.getMail());
			pstmt.executeUpdate();
			System.out.println(vo.getUserName()+"님 회원가입 등록...");
		}finally{
			closeAll(pstmt,conn);
		}
	}
	
	public void updateInfo(MemberVO vo) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnect();
			pstmt=conn.prepareStatement(UserStringQuery.UPDATE_USER);			
			pstmt.setString(1,vo.getPassword());
			pstmt.setString(2,vo.getMail());
			pstmt.setString(3,vo.getTel());
			pstmt.setString(4,vo.getId());
			pstmt.executeUpdate();
			System.out.println("회원 정보 수정..");
		}finally{
			closeAll(pstmt,conn);
		}
	}
	
	public boolean idCheck(String id) throws SQLException{
		boolean result=false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getConnect();
			pstmt = conn.prepareStatement(UserStringQuery.IDCHECK_USER);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()){				
				if(rs.getInt(1)>0)
					result=true;
			}
		}finally{
			closeAll(rs,pstmt,conn);
		}
		return result;
	}
	
	public MemberVO login(String id, String password) throws SQLException{
		MemberVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn=getConnect();
			pstmt=conn.prepareStatement(UserStringQuery.LOGIN_USER);
			pstmt.setString(1,id);
			pstmt.setString(2,password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				//받아야되는거 id 
				vo = new MemberVO(rs.getString("id"));
			}
				
		}finally{
			closeAll(pstmt,conn);
		}
		return vo;
	}
	
	public static void main(String[] args) throws SQLException {//단위테스트
		//TourDao.getInstance().register(new MemberVO("이름", 123456, "아이디", "패스워드", "01011111111", "email@email.com"));
		//MemberVO rvo = TourDao.getInstance().login("아이디", "패스워드");
		//System.out.println(rvo);
		//Boolean result = TourDao.getInstance().idCheck("가나다");
		//System.out.println(result);
		//TourDao.getInstance().updateInfo("패스워드2", "01011111111", "ok2@go.com");
	}
}