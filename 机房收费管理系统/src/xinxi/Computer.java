package xinxi;

public class Computer {

	private int computer_id;
	private String computer_system;
	private String state;
	
	public Computer() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Computer(int computer_id, String computer_system, String state) {
		super();
		this.computer_id = computer_id;
		this.computer_system = computer_system;
		this.state = state;
	}
	
	public int getComputer_id() {
		return computer_id;
	}
	public void setComputer_id(int computer_id) {
		this.computer_id = computer_id;
	}
	public String getComputer_system() {
		return computer_system;
	}
	public void setComputer_system(String computer_system) {
		this.computer_system = computer_system;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Computer [computer_id=" + computer_id + ", computer_system=" + computer_system + ", state=" + state
				+ "]";
	}

}
