package xinxi;

public class Expense_info {
	private int computer_id;
	private int student_sno;
	private String student_name;
	private String sclass;
	private String start_time;
	private String off_time;
	private float expense;
	public Expense_info() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Expense_info(int computer_id, int student_sno, String student_name, String sclass, String start_time,
			String off_time, float expense) {
		super();
		this.computer_id = computer_id;
		this.student_sno = student_sno;
		this.student_name = student_name;
		this.sclass = sclass;
		this.start_time = start_time;
		this.off_time = off_time;
		this.expense = expense;
	}
	@Override
	public String toString() {
		return "Expense_info [computer_id=" + computer_id + ", student_sno=" + student_sno + ", student_name="
				+ student_name + ", sclass=" + sclass + ", start_time=" + start_time + ", off_time=" + off_time
				+ ", expense=" + expense + "]";
	}

	
	public int getComputer_id() {
		return computer_id;
	}
	public void setComputer_id(int computer_id) {
		this.computer_id = computer_id;
	}
	public int getStudent_sno() {
		return student_sno;
	}
	public void setStudent_sno(int student_sno) {
		this.student_sno = student_sno;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getSclass() {
		return sclass;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getOff_time() {
		return off_time;
	}
	public void setOff_time(String off_time) {
		this.off_time = off_time;
	}
	public float getExpense() {
		return expense;
	}
	public void setExpense(float expense) {
		this.expense = expense;
	}	
}
