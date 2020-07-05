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
	
	
	//��ȡ����
	public static Connection getConn() {
		/*
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		*/
		try {
			// 1.ע������
			Class.forName("com.mysql.jdbc.Driver"); 
			// 2.��ȡ����
			con = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return con;
	}
	
	
	//�ر����ӣ��н������
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
	
	//�ر����ӣ��޽������
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
	
	
	//�����������ݿ�
	public static void main(String[] args) {
		System.out.print(getConn());
	}
	
}
