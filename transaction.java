import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.*;
public class transaction extends JFrame{
	JLabel jl=new JLabel("Please Select Your Transaction");
	String str1[]= {"Deposit","Fast Cash","Pin Change",};
	String str2[]= {"Withdraw","Balance Enquiry","Exit"};
	
	JButton jb1[]=new JButton[4];
	JButton jb2[]=new JButton[4];
	
	Font f1=new Font("Imprint MT Shadow",Font.PLAIN,40);
	Font f2=new Font("Franklin Gothic Demi",Font.BOLD,25);
	
	Color col1=new Color(255,255,255);
	Color col2=new Color(217,255,25);
	int k,l=0;
	transaction(){
		Connection con=register1db.dbconnect();
		jl.setFont(f1);
		jl.setBounds(60,50,600,40);		
		jl.setForeground(col1);
		add(jl);
		
		for(int i=0;i<3;i++) {
			jb1[i]=new JButton(str1[i]);
			jb1[i].setBounds(50,200+k,200,40);
			jb1[i].setFont(f2);
			jb1[i].setForeground(Color.BLACK);
			jb1[i].setBackground(col2);
			add(jb1[i]);
			k=k+100;
		}
		
		for(int i=0;i<3;i++) {
			jb2[i]=new JButton(str2[i]);
			jb2[i].setBounds(350,200+l,240,40);
			jb2[i].setFont(f2);
			jb2[i].setForeground(Color.BLACK);
			jb2[i].setBackground(col2);
			add(jb2[i]);
			l=l+100;
		}
		
		jb1[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			new Deposit();	
			dispose();
			}});
		jb1[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			new Fastcash();	
			dispose();
			}});
		jb1[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			new PinChange();	
			dispose();
			}});
		
		jb2[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			new Withdraw();	
			dispose();
			}});
		jb2[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			String b=JOptionPane.showInputDialog("Enter your pin");
			try {
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery("select * from atmsys.account_details where Pin='"+b+"'");
			long balance=0;
			if(rs.next()) {
				balance=rs.getLong("Balance");
				JOptionPane.showMessageDialog(null, "Your Balance is"+balance);
				}}
			catch(Exception ex) {
				ex.printStackTrace();
			}}});
		jb2[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
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
		setTitle("Transaction");
		setDefaultCloseOperation(EXIT_ON_CLOSE);}
	
	public static void main(String args[]) {
		new transaction();
	}}
