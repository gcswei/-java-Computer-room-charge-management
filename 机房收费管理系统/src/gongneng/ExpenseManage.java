package gongneng;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import xinxi.Computer;
import xinxi.Expense_info;

public class ExpenseManage {
	//��������Ϣ�б����ת��
	public Object[][] list(List list) {	
		Object[][] s = new Object[list.size()][7];
		System.out.println("������Ϣ�б����£�");
		for(int i = 0; i < list.size(); i++) {
			Expense_info expense_info = (Expense_info) list.get(i);
			System.out.println("�������ţ�"+expense_info.getComputer_id()+" ѧ�ţ�"+expense_info.getStudent_sno()+" ������"+expense_info.getStudent_name());
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

	//������ѧ�����б����ת��
	public Object[][] online_list(List list) {
		SimpleDateFormat myfmt1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	//��ȡ��ǰʱ��
		Object[][] s = new Object[list.size()][6];
		System.out.println("����ѧ���б����£�");
		for(int i = 0; i < list.size(); i++) {
			Expense_info expense_info = (Expense_info) list.get(i);
			java.util.Date date=new java.util.Date();
			String ss=expense_info.getStart_time();
			long between=(date.getTime()-java.sql.Timestamp.valueOf(ss).getTime())/1000;	//��������������1000������Ϊ��ת������
//			long day1=between/(24*3600);		//�������
//			long hour1=between%(24*3600)/3600;	//��24Сʱ�Ƽ�������Сʱ
			long hour2=between/3600;	//�����Сʱ
			long minute1=between%3600/60;
			System.out.println(" ѧ�ţ�"+expense_info.getStudent_sno()+" ������"+expense_info.getStudent_name()+" ��ʼʱ�䣺"+expense_info.getStart_time());
			s[i][0] = expense_info.getComputer_id();
			s[i][1] = expense_info.getStudent_sno();
			s[i][2] = expense_info.getStudent_name();
			s[i][3] = expense_info.getSclass();
			s[i][4] = expense_info.getStart_time();
			s[i][5] = hour2+"ʱ"+minute1+"��";
		}
		return s;
	}
	
	//��ȡ������ѧ����ѧ��
	public String[] online_sno(List list) {
		String ss = "";
		for(int i = 0; i < list.size(); i++) {
			Expense_info expense_info = (Expense_info) list.get(i);
			System.out.println(" ѧ�ţ�"+expense_info.getStudent_sno());
			if(i!=(list.size()-1)) {
				ss+=expense_info.getStudent_sno()+",";
			}							//����������һ��Ԫ�ؾ�׷�ӡ����� 	   �������ָ�
			if(i==(list.size()-1)) {
				ss+=expense_info.getStudent_sno();
			}
		}
		String[] ss2=ss.split(",");
		return ss2;
	}
	
	//��ȡ������ѧ��������
	public String[] online_name(List list) {
		String ss = "";
		for(int i = 0; i < list.size(); i++) {
			Expense_info expense_info = (Expense_info) list.get(i);
			System.out.println("������"+expense_info.getStudent_name());
			if(i!=(list.size()-1)) {
				ss+=expense_info.getStudent_name()+",";
			}							//����������һ��Ԫ�ؾ�׷�ӡ����� 	   �������ָ�
			if(i==(list.size()-1)) {
				ss+=expense_info.getStudent_name();
			}
		}
		String[] ss2=ss.split(",");
		return ss2;
	}
	
	//ͳ������
public Object[][] countExpense(List list) {
	Object[][] s = new Object[12][2];
	s[0][0] = "һ��";
	s[1][0] = "����";
	s[2][0] = "����";
	s[3][0] = "����";
	s[4][0] = "����";
	s[5][0] = "����";
	s[6][0] = "����";
	s[7][0] = "����";
	s[8][0] = "����";
	s[9][0] = "ʮ��";
	s[10][0] = "ʮһ��";
	s[11][0] = "ʮ����";
	float[] count= {0,0,0,0,0,0,0,0,0,0,0,0};
		for(int i = 0; i < list.size(); i++) {
			Expense_info expense_info = (Expense_info) list.get(i);
			int month=java.sql.Timestamp.valueOf(expense_info.getOff_time()).getMonth();	//��ȡ�������ڵ���
			int Eyear=java.sql.Timestamp.valueOf(expense_info.getOff_time()).getYear();
			int nowyear=new java.util.Date().getYear();
			float expense = expense_info.getExpense();
			if(Eyear==nowyear) {		//���������ڵ���ͽ������Ƚ�
				for(int m=0;m<12;m++) {
					if(month==m) {		//ͳ��ÿ���µ�����
						count[m]+=expense;	
					}
				}
			}
		}
	for(int aa=0;aa<count.length;aa++) {		//������������ݶ���ֵ�����ĵڶ���
		s[aa][1]=count[aa];
	}
	return s;
}
}
