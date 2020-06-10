package ui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Dao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class InsertComputer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_2;
	private JPanel buttonPane;
	
	Dao dao = new Dao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InsertComputer dialog = new InsertComputer();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Insert() {
		InsertComputer dialog = new InsertComputer();
		dialog.setVisible(true);
	}
	/**
	 * Create the dialog.
	 */
	public InsertComputer() {
		setModal(true);
		setTitle("计算机信息录入");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(1200, 450, 570, 355);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		textField_2 = new JTextField("1000");
		textField_2.setFont(new Font("宋体", Font.BOLD, 17));
		textField_2.setColumns(10);
		
		JLabel label = new JLabel("\u8BA1\u7B97\u673A\u7F16\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JComboBox comboBox = new JComboBox(new String[] {"Windows10", "Windows7","Linux-ubuntu", "Linux-fedora","Macos"});
		comboBox.setFont(new Font("宋体", Font.BOLD, 17));
		
		JLabel label_1 = new JLabel("\u7CFB\u7EDF\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.BOLD, 20));
		
		JLabel label_2 = new JLabel("\u4F7F\u7528\u60C5\u51B5\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("宋体", Font.BOLD, 20));
		
		JComboBox comboBox_1 = new JComboBox(new String[] {"空闲", "使用中", "不可用"});
		comboBox_1.setFont(new Font("宋体", Font.BOLD, 17));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(133, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, 0, 126, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(label)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField_2))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED))))
					.addContainerGap(151, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(textField_2.getText().length()==0){
							JOptionPane.showMessageDialog(null, "计算机编号不能为空");
							return;
						}
						if(textField_2.getText().length()<5||textField_2.getText().length()>10){
							JOptionPane.showMessageDialog(null, "计算机编号需要在5-10位之间");
							return;
						}
						String bianhao=textField_2.getText();
						Object selectedItem1 = comboBox.getSelectedItem();
						Object selectedItem2 = comboBox_1.getSelectedItem();
						int i=dao.addComputer(bianhao, selectedItem1, selectedItem2);
						if(i==1){
						JOptionPane.showMessageDialog(null, "添加成功");
						}else {
							JOptionPane.showMessageDialog(null, "添加失败,计算机编号重复！");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u91CD\u7F6E");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textField_2.setText("1000");
						comboBox.setSelectedIndex(0);
						comboBox_1.setSelectedIndex(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(buttonPane, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
				.addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
	}
}
