package gongneng;

import java.sql.Date;
import java.util.List;


import xinxi.Computer;

public class ComputerManager {
	//��ȡ���еļ������Ϣ
	public Object[][] list(List list) {	
		Object[][] s = new Object[list.size()][3];
		System.out.println("������б����£�");
		for(int i = 0; i < list.size(); i++) {
			Computer computer = (Computer) list.get(i);
			System.out.println("�������ţ�"+computer.getComputer_id()+" ϵͳ��"+computer.getComputer_system()+" ʹ�������"+computer.getState());
			s[i][0] = computer.getComputer_id();
			s[i][1] = computer.getComputer_system();
			s[i][2] = computer.getState();
			
		}
		return s;
	}
	
	//��ȡ��������
	public String[] id_list(List list) {
		String ss = "";	
		System.out.println("����б�");
		for(int i = 0; i < list.size(); i++) {
			Computer computer = (Computer) list.get(i);
			System.out.println("�������ţ�"+computer.getComputer_id());
			if(i!=(list.size()-1)) {
				ss+=computer.getComputer_id()+",";
			}							//����������һ��Ԫ�ؾ�׷�ӡ����� 	   �������ָ�
			if(i==(list.size()-1)) {
				ss+=computer.getComputer_id();
			}
		}
		String[] ss2=ss.split(","); //�����ַ���ת��ΪString���飬ע��ָ�������Ҫת�� 
		return ss2;
	}
}
