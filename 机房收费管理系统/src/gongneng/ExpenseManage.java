package gongneng;

import java.util.List;

import xinxi.Expense_info;

public class ExpenseManage {
	//获取所有消费信息
	public Object[][] list(List list) {	
		Object[][] s = new Object[list.size()][8];
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
}
