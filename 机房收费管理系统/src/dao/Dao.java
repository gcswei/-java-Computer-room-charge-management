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
	 * ����id���ж�������Ƿ��Ѵ���
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
	 * ��ȡ�����������Ϣ�б�
	 */
	public List<Computer> getAllComputers(int n){
		Connection conn = null; 
		Statement stmt = null;	
		ResultSet rs = null;
		List<Computer> computerList = new ArrayList<>();
		try {
			
			conn = MySqlUtils.getConn();
			String sql = "select * from computer_info";
			String sql2 = "select * from computer_info where state='����'";
			stmt = conn.createStatement();
			if(n==1) {
				rs = stmt.executeQuery(sql);
			}
			if(n==2) {
				rs = stmt.executeQuery(sql2);
			}
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
	 * ��ȡ����������Ϣ�б�
	 */
	public List<Expense_info> getAllExpenses(int n){
		Connection conn = null; 
		Statement stmt = null;	
		ResultSet rs = null;
		List<Expense_info> expenslist = new ArrayList<>();
		try {
			
			conn = MySqlUtils.getConn();
			String sql = "select * from expense_info";
			String sql2 = "SELECT * FROM expense_info WHERE expense IS NOT NULL";
			stmt = conn.createStatement();
			if(n==1) {
				rs = stmt.executeQuery(sql);
			}
			if(n==2) {
				rs = stmt.executeQuery(sql2);
			}
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
	 * ��ȡ����ѧ���б�
	 */
	public List<Expense_info> getAllOnline(){
		Connection conn = null; 
		Statement stmt = null;	
		ResultSet rs = null;
		List<Expense_info> expenslist = new ArrayList<>();
		try {
			
			conn = MySqlUtils.getConn();
			String sql = "SELECT * FROM expense_info WHERE expense IS NULL";
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
	 * ���������������š�����ϵͳ��ʹ����������Ҽ����������Ϣ
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
	 * ��������ѧ�š��������༶������������Ϣ
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
	 * ¼������������Ϣ
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
	 * ¼��������Ϣ
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
	 * �޸����ѱ�¼���»�ʱ�估���ѷ���
	 */
	public int modifyExpense(float expense,String offtime,int e_id){
		Connection conn = null;
		PreparedStatement stmt = null;
		int i=1;
		try {
			conn = MySqlUtils.getConn();
			String sql = "update expense_info set expense = ?,off_time =? where e_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setFloat(1, expense);
			stmt.setString(2, offtime);
			stmt.setInt(3, e_id);
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
	 * ����ѧ�źͷ���Ϊ�յ�������������id��������ѡ���»�ѧ����δ��������ѵ���
	 */
	public int findE_id(int s3){
		Connection conn = null; 
		PreparedStatement stmt = null;	
		int e_id=0;
		try {
			conn = MySqlUtils.getConn();
			String sql = "SELECT e_id FROM expense_info WHERE student_sno= ? AND expense IS NULL";
				stmt = conn.prepareStatement(sql);
			stmt.setInt(1, s3);
			ResultSet tmpres = stmt.executeQuery();
			while(tmpres.next()){
				e_id= tmpres.getInt("e_id");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySqlUtils.closeConn(conn, stmt);
		}
		return e_id;
	}
	
	/**
	 * �޸ļ����������Ϣ
	 */
	public int modifyComputer(int id, Computer computer){
		Connection conn = null;
		PreparedStatement stmt = null;
		int i=1;
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
			return i=0;
		}
		finally{
			MySqlUtils.closeConn(conn, stmt);
		}
		return i;
	}
	
	/**
	 * �޸ļ������ʹ��״̬
	 */
	public int modifyComputeStater(Object id,int n){
		Connection conn = null;
		PreparedStatement stmt = null;
		int i=1;
		try {
			conn = MySqlUtils.getConn();
			String sql = "update computer_info set state ='����' where computer_id = ?";
			String sql2 = "update computer_info set state ='ʹ����' where computer_id = ?";
			if(n==1) {
				stmt = conn.prepareStatement(sql);
			}
			if(n==2) {
				stmt = conn.prepareStatement(sql2);
			}
			stmt.setObject(1, id);
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
	 * ɾ��
	 */
	public int deleteComputer(int id){
		Connection conn = null; 
		PreparedStatement stmt = null;	
		int i=1;
		try {
			conn = MySqlUtils.getConn();
			String sql = "delete from computer_info where computer_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
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
}
