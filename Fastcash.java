import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class Fastcash extends JFrame {
	JLabel jl1=new JLabel("Select Withdrawal Amount");
	JLabel jl2=new JLabel("Enter Pin");
	JPasswordField tf1=new JPasswordField();
	JButton jb1[]=new JButton[2];
	JButton jb2[]=new JButton[2];
	JButton jb3=new JButton("Exit");
	
	String str1[]= {"100","500"};
	String str2[]= {"1000","5000"};
	
	int k,l=0;
	
	Font f1=new Font("Imprint MT Shadow",Font.PLAIN,40);
	Font f2=new Font("Palatino",Font.PLAIN,30);
	Font f3=new Font("Verdana",Font.PLAIN,30);
	Font f4=new Font("Franklin Gothic Demi",Font.BOLD,35);
	
	Color col1=new Color(255,255,255);
	Color col2=new Color(0,255,0);
	Color col3=new Color(217,255,25);
	Fastcash(){
		Connection con=register1db.dbconnect();

		jl1.setBounds(100, 40, 600, 40);
		jl1.setFont(f1);
		jl1.setForeground(col1);
		add(jl1);
		
		jl2.setBounds(250, 100, 600, 40);
		jl2.setFont(f2);
		jl2.setForeground(col2);
		add(jl2);
		
		tf1.setBounds(250,150, 200, 35);
		tf1.setFont(f3);
		add(tf1);
		
		for(int i=0;i<2;i++) {
			jb1[i]=new JButton(str1[i]);
			jb1[i].setFont(f4);
			jb1[i].setForeground(Color.black);
			jb1[i].setBackground(col3);
			jb1[i].setBounds(100+k, 250, 200, 40);
			add(jb1[i]);
			k=k+250;
		}
		
		for(int i=0;i<2;i++) {
			jb2[i]=new JButton(str2[i]);
			jb2[i].setFont(f4);
			jb2[i].setForeground(Color.black);
			jb2[i].setBackground(col3);
			jb2[i].setBounds(100+l,350,200,40);
			add(jb2[i]);
			l=l+250;
		}
		
		jb3.setBounds(245,450,200,40);
		jb3.setForeground(Color.black);
		jb3.setFont(f4);
		jb3.setBackground(col3);
		add(jb3);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}});
		
		jb1[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					long s1=Long.valueOf(tf1.getText());
					
					Statement stm=con.createStatement();
					ResultSet rs=stm.executeQuery("select * from atmsys.account_details where Pin='"+s1+"'");
					long balance=0;
					if(rs.next()) {
						String pin=rs.getString("Pin");
						balance=rs.getLong("Balance");
						
						balance-=100;
						String str="Update atmsys.account_details set Balance='"+balance+"' where Pin='"+pin+"'";
						stm.executeUpdate(str);
					}
					JOptionPane.showMessageDialog(null,"Rs "+100+" Debited Successfully");
					tf1.setText(null);
					}
					catch(Exception x) {
					x.printStackTrace();
					}}});
		jb1[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				long s1=Long.valueOf(tf1.getText());
				
				Statement stm=con.createStatement();
				ResultSet rs=stm.executeQuery("select * from atmsys.account_details where Pin='"+s1+"'");
				long balance=0;
				if(rs.next()) {
					String pin=rs.getString("Pin");
					balance=rs.getLong("Balance");
					
					balance-=500;
					String str="Update atmsys.account_details set Balance='"+balance+"' where Pin='"+pin+"'";
					stm.executeUpdate(str);
				}
				JOptionPane.showMessageDialog(null,"Rs "+500+" Debited Successfully");
				tf1.setText(null);
				}
				catch(Exception x) {
				x.printStackTrace();
				}}});
		jb2[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				long s1=Long.valueOf(tf1.getText());
				
				Statement stm=con.createStatement();
				ResultSet rs=stm.executeQuery("select * from atmsys.account_details where Pin='"+s1+"'");
				long balance=0;
				if(rs.next()) {
					String pin=rs.getString("Pin");
					balance=rs.getLong("Balance");
					
					balance-=1000;
					String str="Update atmsys.account_details set Balance='"+balance+"' where Pin='"+pin+"'";
					stm.executeUpdate(str);
				}
				JOptionPane.showMessageDialog(null,"Rs "+1000+" Debited Successfully");
				tf1.setText(null);
				}
				catch(Exception x) {
				x.printStackTrace();
				}}});
		jb2[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					long s1=Long.valueOf(tf1.getText());
					
					Statement stm=con.createStatement();
					ResultSet rs=stm.executeQuery("select * from atmsys.account_details where Pin='"+s1+"'");
					long balance=0;
					if(rs.next()) {
						String pin=rs.getString("Pin");
						balance=rs.getLong("Balance");
						
						balance-=5000;
						String str="Update atmsys.account_details set Balance='"+balance+"' where Pin='"+pin+"'";
						stm.executeUpdate(str);
					}
					JOptionPane.showMessageDialog(null,"Rs "+5000+" Debited Successfully");
					tf1.setText(null);
					}
					catch(Exception x) {
					x.printStackTrace();
					}}});

	setLayout(null);
	setVisible(true);
	setSize(650, 650);
	ImageIcon ico=new ImageIcon("C:\\Users\\hp\\OneDrive\\Desktop\\OSMP\\AJPimages\\ATM .png");
	setIconImage(ico.getImage());
	setLocationRelativeTo(null);
	Color c=new Color(0,8,51);
	getContentPane().setBackground(c);
	setTitle("Fast Cash");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
}

public static void main(String args[]) {
	new Fastcash();
}
}
