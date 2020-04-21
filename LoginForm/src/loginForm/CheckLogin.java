package loginForm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLogin extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg = "";
		String status = "";
		
		Connection connection = getJDBCConnection();
		if (connection != null) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			try {
				Statement statement = connection.createStatement();
				String sql = "SELECT * FROM m_user WHERE username = '" + username + "' AND password = '" + password + "'";
				ResultSet rs = statement.executeQuery(sql);
				if (rs.next()) {
					status = "true";
				} else {
					status = "false";
					msg = "ユーザ名とパスワードが間違いました。";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			resp.setContentType("text/html; charset = Shift_JIS");
		} else {
			msg = "DBに接続出来ません。";
			status = "false";
		}
		if (status.equals("true") == true) {
		    resp.sendRedirect("/LoginForm/success.jsp");
		} else {
			req.setAttribute("msg", msg);
	        req.getRequestDispatcher("/fail.jsp").forward(req, resp);
		}
	}
	// コネクションを作成
	public static Connection getJDBCConnection() {
		final String url = "jdbc:mysql://localhost:3306/login?serverTimezone=JST";
		final String user = "root";
		final String password = "admin";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
