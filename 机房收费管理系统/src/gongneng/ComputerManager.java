package gongneng;

import java.sql.Date;
import java.util.List;


import xinxi.Computer;

public class ComputerManager {
	//获取所有的计算机信息
	public Object[][] list(List list) {	
		Object[][] s = new Object[list.size()][3];
		System.out.println("计算机列表如下：");
		for(int i = 0; i < list.size(); i++) {
			Computer computer = (Computer) list.get(i);
			System.out.println("计算机编号："+computer.getComputer_id()+" 系统："+computer.getComputer_system()+" 使用情况："+computer.getState());
			s[i][0] = computer.getComputer_id();
			s[i][1] = computer.getComputer_system();
			s[i][2] = computer.getState();
			
		}
		return s;
	}
	
	//获取计算机编号
	public String[] id_list(List list) {
		String ss = "";	
		System.out.println("编号列表：");
		for(int i = 0; i < list.size(); i++) {
			Computer computer = (Computer) list.get(i);
			System.out.println("计算机编号："+computer.getComputer_id());
			if(i!=(list.size()-1)) {
				ss+=computer.getComputer_id()+",";
			}							//如果不是最后一个元素就追加“，” 	   方便后面分隔
			if(i==(list.size()-1)) {
				ss+=computer.getComputer_id();
			}
		}
		String[] ss2=ss.split(","); //，将字符串转化为String数组，注意分隔符是需要转译 
		return ss2;
	}
}
