import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

import com.email.durgesh.Email;

public class Withdraw extends JFrame{
	JLabel jl1=new JLabel("Maximum Daily Withdrawal is Rs. 10,000");
	JLabel jl2=new JLabel("Enter Pin");
	JLabel jl3=new JLabel("Enter Amount");
	JPasswordField tf1=new JPasswordField(20);
	JTextField tf2=new JTextField(20);
	JButton jb1[]=new JButton[3];
	JButton jb2=new JButton("Exit");
	String str[]= {"Withdraw","Back"};

	
	Color col1=new Color(255,255,255);
	Color col2=new Color(0,255,0);
	Color col3=new Color(217,255,25);

	
	Font f1=new Font("Imprint MT Shadow",Font.PLAIN,30);
	Font f2=new Font("Platino",Font.PLAIN,25);
	Font f3=new Font("Verdana",Font.PLAIN,30);
	Font f4=new Font("Franklin Gothic Demi",Font.BOLD,25);
	int k=0;

	Withdraw(){
		Connection con=register1db.dbconnect();

		jl1.setBounds(40, 40,600, 50);
		jl1.setFont(f1);
		jl1.setForeground(col1);
		add(jl1);
	
		jl2.setBounds(200,120,200,30);
		jl2.setFont(f2);
		jl2.setForeground(col2);
		add(jl2);
		
		tf1.setBounds(200,160,200,35);
		tf1.setFont(f3);
		add(tf1);
		
		jl3.setBounds(200,220,200,30);
		jl3.setFont(f2);
		jl3.setForeground(col2);
		add(jl3);
		
		tf2.setBounds(200,260,200,35);
		tf2.setFont(f3);
		add(tf2);
		
		for(int i=0;i<2;i++) {
			jb1[i]=new JButton(str[i]);
			jb1[i].setBounds(100+k,350,200, 40);
			jb1[i].setBackground(col3);
			jb1[i].setFont(f4);
			jb1[i].setForeground(Color.black);
			add(jb1[i]);
			k=k+220;
		}
		
		jb2.setBounds(220, 450, 150, 40);
		jb2.setFont(f4);
		jb2.setBackground(col3);
		jb2.setForeground(Color.black);
		add(jb2);
		
		jb1[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
			long s1=Long.valueOf(tf1.getText());
			long s2=Long.parseLong(tf2.getText());
			
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery("select * from atmsys.account_details where Pin='"+s1+"'");
			long balance=0;
			if(rs.next()) {
				String pin=rs.getString("Pin");
				String address=rs.getString("EmailId");
				long card=rs.getLong("Cardnumber");
				balance=rs.getLong("Balance");
				long d=s2;
				balance-=d;
				String str="Update atmsys.account_details set Withdraw='"+d+"',Balance='"+balance+"' where Pin='"+pin+"'";
				stm.executeUpdate(str);
				JOptionPane.showMessageDialog(null,"Rs "+s2+" Debited Successfully");

				Email email=new Email("ashwinibhide2@gmail.com","@shwinibhide2020");
				email.setFrom("ashwinibhide2@gmail.com","The Boring Bank");
				email.setSubject("Debited");
				email.setContent(s2+" Rs Debited to CardNo. "+card+"<p>Your Balance is "+balance+"<br><br><br><p>The Boring Bank</p>","text/html");
				email.addRecipient(address);
				email.send();
				JOptionPane.showMessageDialog(null,"Message sent");
			}
			tf1.setText(null);
			tf2.setText(null);		
			}
			catch(Exception x) {
			x.printStackTrace();
			}}});
		
		jb1[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			new transaction();
			dispose();
			}});
		
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}});
	setLayout(null);
	setVisible(true);
	setSize(650, 650);
	ImageIcon ico=new ImageIcon("C:\\Users\\hp\\OneDrive\\Desktop\\OSMP\\AJPimages\\ATM .png");
	setIconImage(ico.getImage());
	setLocationRelativeTo(null);
	Color c=new Color(0,8,51);
	getContentPane().setBackground(c);
	setTitle("Withdraw");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
}
public static void main(String args[]) {
	new Withdraw();
}
}
