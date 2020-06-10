package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlUtils {
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/jf_system?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
	private static String user = "root"; 
	private static String password = "deng2606267739"; 
	private static Connection con = null;
	
	
	//获取连接
	public static Connection getConn() {
		/*
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		*/
		try {
			// 1.注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("oracle.jdbc.driver.OracleDriver"); 
			// 2.获取连接
			con = DriverManager.getConnection(url, user, password);
			/*
			stmt = con.createStatement();
			String sql = "select * from computer_info";
			rs = stmt.executeQuery(sql);
			System.out.println("computer_id  |	computer_system	|	state");
			while (rs.next()) {
				int id = rs.getInt("computer_id");
				String systemname = rs.getString("computer_system");
				String state = rs.getString("state");
				System.out.println(id +"   |   " + systemname +" 		|	" + state);
			}
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return con;
	}
	
	
	//关闭连接（有结果集）
	public static void closeConn(Connection conn, Statement stmt, ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	//关闭连接（无结果集）
	public static void closeConn(Connection conn, Statement stmt){
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	
	//测试连接数据库
	public static void main(String[] args) {
		System.out.print(getConn());
	}
	
}
