package ATMSimulator;
import javax.swing.*;

import com.email.durgesh.Email;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PinChange extends JFrame{
	JLabel jl1=new JLabel("Change Your Pin");
	JLabel jl2[]=new JLabel[3];
	String str1[]= {"Current Pin","New Pin","Re-Enter Pin"};
	JPasswordField tf1[]=new JPasswordField[3];
	JButton jb1[]=new JButton[2];
	String str2[]= {"Save","Back"};

	
	Font f1=new Font("Imprint MT Shadow",Font.PLAIN,40);
	Font f2=new Font("Palatino",Font.PLAIN,30);
	Font f3=new Font("Verdana",Font.PLAIN,30);
	Font f4=new Font("Franklin Gothic Demi",Font.BOLD,35);

	
	Color col1=new Color(255,255,255);//heading
	Color col2=new Color(50, 240, 20);//label
	Color col3=new Color(246, 185, 59);//button

	int k,l,m=0;

PinChange(){
	Connection con=register1db.dbconnect();

	jl1.setBounds(170,30,300,50);
	jl1.setFont(f1);
	jl1.setForeground(col1);
	add(jl1);
	
	for(int i=0;i<3;i++) {
		jl2[i]=new JLabel(str1[i]);
		jl2[i].setBounds(80, 200+k, 250, 40);
		jl2[i].setFont(f2);
		jl2[i].setForeground(col2);
		add(jl2[i]);
		k=k+50;
	}

	for(int i=0;i<3;i++) {
		tf1[i]=new JPasswordField();
		tf1[i].setBounds(300, 200+l, 250, 35);
		tf1[i].setFont(f3);
		add(tf1[i]);
		l=l+50;
	}
	
	for(int i=0;i<2;i++) {
		jb1[i]=new JButton(str2[i]);
		jb1[i].setBounds(200+m,370,120,40);
		jb1[i].setBackground(col3);
		jb1[i].setForeground(Color.black);
		jb1[i].setFont(f4);
		add(jb1[i]);
		m=m+150;
	}
			
	jb1[0].addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		 
		 if(tf1[1].getText().equals(tf1[2].getText())) {
			 long text1=Integer.parseInt(tf1[0].getText());
			 long text3=Integer.parseInt(tf1[2].getText());
			 try {
			 PreparedStatement pst=con.prepareStatement("Update boring_bank.account_details set Pin='"+text3+"' where Pin='"+text1+"'");
			 pst.executeUpdate();
			 JOptionPane.showMessageDialog(null, "Pin Changed");
			 
			 Statement stm=con.createStatement();
			 ResultSet rs=stm.executeQuery("select * from boring_bank.account_details where Pin='"+text3+"'");
			 if(rs.next()) {
				 String address=rs.getString("EmailId");
			 
			 Email email=new Email("ashwinibhide2@gmail.com","oyrvzzdocamdjmuh");
				email.setFrom("ashwinibhide2@gmail.com","The Boring Bank");
				email.setSubject("Pin Changed");
				email.setContent("Your pin was changed to "+text3+"<br><br><br><p>The Boring Bank</p>","text/html");
				email.addRecipient(address);
				email.send();
				JOptionPane.showMessageDialog(null,"Message sent");
			 tf1[0].setText(null);
			 tf1[1].setText(null);
			 tf1[2].setText(null);
			 new welcome();
			 dispose();
			 }}
		 catch(Exception x) {
			 x.printStackTrace();
		 }}
		 else{
			 JOptionPane.showMessageDialog(null, "Password Mismatch");
			 tf1[0].setText(null);
			 tf1[1].setText(null);
			 tf1[2].setText(null);
		 }}});
		 
	jb1[1].addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new transaction();
			dispose();
		}});
	setLayout(null);
	setVisible(true);
	setSize(650, 650);
	ImageIcon ico=new ImageIcon("C:\\Users\\hp\\OneDrive\\Desktop\\OSMP\\AJPimages\\ATM .png");
	setIconImage(ico.getImage());
	setLocationRelativeTo(null);
	Color c=new Color(10, 61, 98);//background
	getContentPane().setBackground(c);
	setTitle("Pin Change");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
}
public static void main(String args[]) {
	new PinChange();
}
}
