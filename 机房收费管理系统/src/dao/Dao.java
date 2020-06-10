package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import xinxi.Computer;
import xinxi.Expense_info;

public class Dao {

	/**
	 * 根据id名判定计算机是否已存在
	 */
	public boolean judgeExist(String targetName){
	Connection conn = null; 
	PreparedStatement stmt = null;	
	ResultSet rs = null;
	int count = 0;
	try {
		conn = MySqlUtils.getConn();
		String sql = "select count(*) as count from computer_info where computer_id = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, targetName);
		rs = stmt.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	finally{
		MySqlUtils.closeConn(conn, stmt, rs);
	}
	if(count==0) return false;
	else
		return true;
   }
	
	
	/**
	 * 获取所有计算机基本信息列表
	 */
	public List<Computer> getAllComputers(){
		Connection conn = null; 
		Statement stmt = null;	
		ResultSet rs = null;
		List<Computer> computerList = new ArrayList<>();
		try {
			
			conn = MySqlUtils.getConn();
			String sql = "select * from computer_info";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int computer_id = rs.getInt("computer_id");
				String computer_system = rs.getString("computer_system");
				String state = rs.getString("state");
				Computer computer = new Computer(computer_id, computer_system, state);
				computerList.add(computer);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySqlUtils.closeConn(conn, stmt, rs);
		}
		return computerList;
	}
	
	/**
	 * 获取所有消费信息列表
	 */
	public List<Expense_info> getAllExpenses(){
		Connection conn = null; 
		Statement stmt = null;	
		ResultSet rs = null;
		List<Expense_info> expenslist = new ArrayList<>();
		try {
			
			conn = MySqlUtils.getConn();
			String sql = "select * from expense_info";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int computer_id = rs.getInt("computer_id");
				int student_sno = rs.getInt("student_sno");
				String student_name = rs.getString("student_name");
				String sclass = rs.getString("class");
				String start_time = rs.getString("start_time");
				String off_time = rs.getString("off_time");
				float expense=rs.getFloat("expense");
				Expense_info expense_info = new Expense_info(computer_id, student_sno, student_name,sclass,start_time,off_time,expense);
				expenslist.add(expense_info);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySqlUtils.closeConn(conn, stmt, rs);
		}
		return expenslist;
	}
	
	/**
	 * 按条件（计算机编号、操作系统、使用情况）查找计算机基本信息
	 */
	public List<Computer> find(int n,String s3){
		Connection conn = null; 
		PreparedStatement stmt = null;	
		List<Computer> computerList = new ArrayList<>();
		try {
			conn = MySqlUtils.getConn();
			String sql = "select * from computer_info where computer_id = ?";
			String sql1 = "select * from computer_info where computer_system = ?";
			String sql2 = "select * from computer_info where state = ?";
			if(n==0) {
				stmt = conn.prepareStatement(sql);
			}else if(n==1) {
				stmt = conn.prepareStatement(sql1);
			}else if(n==2){
				stmt = conn.prepareStatement(sql2);
			}
			stmt.setString(1, s3);
			ResultSet tmpres = stmt.executeQuery();
			while(tmpres.next()){
				Computer computer1 = new Computer(tmpres.getInt("computer_id"),tmpres.getString("computer_system"),tmpres.getString("state"));
				computerList.add(computer1);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySqlUtils.closeConn(conn, stmt);
		}
		return computerList;
	}
	
	/**
	 * 按条件（学号、姓名、班级）查找消费信息
	 */
	public List<Expense_info> findExpense(int n,String s3){
		Connection conn = null; 
		PreparedStatement stmt = null;	
		Expense_info ex=null;
		List<Expense_info> expenselist = new ArrayList<>();
		try {
			conn = MySqlUtils.getConn();
			String sql = "select * from Expense_info where student_sno = ?";
			String sql1 = "select * from Expense_info where student_name = ?";
			String sql2 = "select * from Expense_info where class = ?";
			if(n==0) {
				stmt = conn.prepareStatement(sql);
			}else if(n==1) {
				stmt = conn.prepareStatement(sql1);
			}else if(n==2){
				stmt = conn.prepareStatement(sql2);
			}
			stmt.setString(1, s3);
			ResultSet tmpres = stmt.executeQuery();
			while(tmpres.next()){
				Expense_info expense_info = new Expense_info(tmpres.getInt("computer_id"),tmpres.getInt("student_sno"),tmpres.getString("student_name"),tmpres.getString("class"),tmpres.getString("start_time"),tmpres.getString("off_time"),tmpres.getFloat("expense"));
				expenselist.add(expense_info);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySqlUtils.closeConn(conn, stmt);
		}
		return expenselist;
	}
	
	/**
	 * 录入计算机基本信息
	 */
	public int addComputer(String id,Object selectedItem1,Object selectedItem2){
		Connection conn = null; 
		PreparedStatement stmt = null;	
		int i=1;
		try {
			conn = MySqlUtils.getConn();
			String sql = "insert into computer_info(computer_id, computer_system, state) values('"+id+"','"+selectedItem1+"','"+selectedItem2+"')";
			stmt = conn.prepareStatement(sql);

			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return i=0;
		}
		finally{
			MySqlUtils.closeConn(conn, stmt);
		}
		return i;
	}
	
	/**
	 * 录入消费信息
	 */
	public int addExpense(Object bianhao,String xuehao,String name,String sclass,String starttime){
		Connection conn = null; 
		PreparedStatement stmt = null;	
		int i=1;
		try {
			conn = MySqlUtils.getConn();
			String sql = "insert into expense_info(computer_id, student_sno, student_name,class,start_time) values('"+bianhao+"','"+xuehao+"','"+name+"','"+sclass+"','"+starttime+"')";
			stmt = conn.prepareStatement(sql);

			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return i=0;
		}
		finally{
			MySqlUtils.closeConn(conn, stmt);
		}
		return i;
	}
	
	/**
	 * 修改计算机基本信息
	 */
	public void modifyComputer(int id, Computer computer){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = MySqlUtils.getConn();
			String sql = "update computer_info set computer_id = ?, computer_system = ?,  state = ? where computer_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, computer.getComputer_id());
			stmt.setString(2, computer.getComputer_system());
			stmt.setString(3, computer.getState());
			stmt.setInt(4, id);
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySqlUtils.closeConn(conn, stmt);
		}
	}
	
	/**
	 * 删除
	 */
	public void deleteComputer(int id){
		Connection conn = null; 
		PreparedStatement stmt = null;	
		try {
			conn = MySqlUtils.getConn();
			String sql = "delete from computer_info where computer_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySqlUtils.closeConn(conn, stmt);
		}
	}
}
