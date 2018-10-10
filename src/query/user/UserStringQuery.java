package query.user;

public interface UserStringQuery {
	String REGISTER_USER = "INSERT INTO member VALUES(?,?,?,?,?,?)";
	String UPDATE_USER = "UPDATE member SET password=?, mail=?, tel=? WHERE id=?";
	String IDCHECK_USER = "SELECT count(*) FROM member WHERE id=?";
	String LOGIN_USER = "SELECT * FROM member WHERE id=? AND password=?";
	String FINDIDPASS_USER = "SELECT id, password FROM member WHERE member_name=? AND ssn=? AND tel=?";
}
