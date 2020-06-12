package gongneng;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import xinxi.Computer;
import xinxi.Expense_info;

public class ExpenseManage {
	//把消费信息列表进行转换
	public Object[][] list(List list) {	
		Object[][] s = new Object[list.size()][7];
		System.out.println("消费信息列表如下：");
		for(int i = 0; i < list.size(); i++) {
			Expense_info expense_info = (Expense_info) list.get(i);
			System.out.println("计算机编号："+expense_info.getComputer_id()+" 学号："+expense_info.getStudent_sno()+" 姓名："+expense_info.getStudent_name());
			s[i][0] = expense_info.getComputer_id();
			s[i][1] = expense_info.getStudent_sno();
			s[i][2] = expense_info.getStudent_name();
			s[i][3] = expense_info.getSclass();
			s[i][4] = expense_info.getStart_time();
			s[i][5] = expense_info.getOff_time();
			s[i][6] = expense_info.getExpense();
		}
		return s;
	}

	//把在线学生的列表进行转换
	public Object[][] online_list(List list) {
		SimpleDateFormat myfmt1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	//获取当前时间
		Object[][] s = new Object[list.size()][6];
		System.out.println("在线学生列表如下：");
		for(int i = 0; i < list.size(); i++) {
			Expense_info expense_info = (Expense_info) list.get(i);
			java.util.Date date=new java.util.Date();
			String ss=expense_info.getStart_time();
			long between=(date.getTime()-java.sql.Timestamp.valueOf(ss).getTime())/1000;	//相差毫秒数，除以1000毫秒是为了转化成秒
//			long day1=between/(24*3600);		//相差天数
//			long hour1=between%(24*3600)/3600;	//按24小时制计算相差的小时
			long hour2=between/3600;	//总相差小时
			long minute1=between%3600/60;
			System.out.println(" 学号："+expense_info.getStudent_sno()+" 姓名："+expense_info.getStudent_name()+" 起始时间："+expense_info.getStart_time());
			s[i][0] = expense_info.getComputer_id();
			s[i][1] = expense_info.getStudent_sno();
			s[i][2] = expense_info.getStudent_name();
			s[i][3] = expense_info.getSclass();
			s[i][4] = expense_info.getStart_time();
			s[i][5] = hour2+"时"+minute1+"分";
		}
		return s;
	}
	
	//获取把在线学生的学号
	public String[] online_sno(List list) {
		String ss = "";
		for(int i = 0; i < list.size(); i++) {
			Expense_info expense_info = (Expense_info) list.get(i);
			System.out.println(" 学号："+expense_info.getStudent_sno());
			if(i!=(list.size()-1)) {
				ss+=expense_info.getStudent_sno()+",";
			}							//如果不是最后一个元素就追加“，” 	   方便后面分隔
			if(i==(list.size()-1)) {
				ss+=expense_info.getStudent_sno();
			}
		}
		String[] ss2=ss.split(",");
		return ss2;
	}
	
	//获取把在线学生的姓名
	public String[] online_name(List list) {
		String ss = "";
		for(int i = 0; i < list.size(); i++) {
			Expense_info expense_info = (Expense_info) list.get(i);
			System.out.println("姓名："+expense_info.getStudent_name());
			if(i!=(list.size()-1)) {
				ss+=expense_info.getStudent_name()+",";
			}							//如果不是最后一个元素就追加“，” 	   方便后面分隔
			if(i==(list.size()-1)) {
				ss+=expense_info.getStudent_name();
			}
		}
		String[] ss2=ss.split(",");
		return ss2;
	}
	
	//统计消费
public Object[][] countExpense(List list) {
	Object[][] s = new Object[12][2];
	s[0][0] = "一月";
	s[1][0] = "二月";
	s[2][0] = "三月";
	s[3][0] = "四月";
	s[4][0] = "五月";
	s[5][0] = "六月";
	s[6][0] = "七月";
	s[7][0] = "八月";
	s[8][0] = "九月";
	s[9][0] = "十月";
	s[10][0] = "十一月";
	s[11][0] = "十二月";
	float[] count= {0,0,0,0,0,0,0,0,0,0,0,0};
		for(int i = 0; i < list.size(); i++) {
			Expense_info expense_info = (Expense_info) list.get(i);
			int month=java.sql.Timestamp.valueOf(expense_info.getOff_time()).getMonth();	//获取结算日期的年
			int Eyear=java.sql.Timestamp.valueOf(expense_info.getOff_time()).getYear();
			int nowyear=new java.util.Date().getYear();
			float expense = expense_info.getExpense();
			if(Eyear==nowyear) {		//将结算日期的年和今年作比较
				for(int m=0;m<12;m++) {
					if(month==m) {		//统计每个月的消费
						count[m]+=expense;	
					}
				}
			}
		}
	for(int aa=0;aa<count.length;aa++) {		//将数组里的数据都赋值给表单的第二列
		s[aa][1]=count[aa];
	}
	return s;
}
}
