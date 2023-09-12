import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import javax.swing.*;
import com.email.durgesh.Email;
class register2_slide extends JFrame{
	
	JLabel jl1=new JLabel("Page2: Account Details");
	JLabel jl2=new JLabel("Select Account Type:");
	
	String str1[]= {"Card number: ","XXXXXXXXXX"};
	JLabel jl3[]=new JLabel[3];
	
	String str2[]= {"(Your 10 digit card number)","(It would appear on ATM/Cheque Book)"};
	JLabel jl4[]=new JLabel[3];
	
	String str3[]= {"Pin: ","XXXX"};
	JLabel jl5[]=new JLabel[3];
	
	JLabel jlb1=new JLabel("(4 digit password)");
	
	JLabel jlb2=new JLabel("Email Address: ");
	JTextField tf=new JTextField();
	
	JLabel jlb3=new JLabel("I hereby declare that the above entered details are correct"
			+ " to the best of my knowledge.");
	
	JButton jbt1=new JButton("Submit");
	JButton jbt2=new JButton("Cancel");
	
	
	int k,l,m=0;
	JRadioButton jrb1=new JRadioButton("Savings Account",true);
	JRadioButton jrb2=new JRadioButton("Current Account");
	JRadioButton jrb3=new JRadioButton("Fixed Deposit Account");
	JRadioButton jrb4=new JRadioButton("Recurring Deposit Account");
	ButtonGroup bgrp=new ButtonGroup();
	
	
	Color col1=new Color(255,255,255);
	Color col2=new Color(0,255,0);
	Color col3=new Color(217,255,25);
	Color c=new Color(0,8,51);

	Font f1=new Font("Imprint MT Shadow",Font.PLAIN,42);
	Font f2=new Font("Platino",Font.PLAIN,25);
	Font f3=new Font("Verdana",Font.PLAIN,17);
	Font f4=new Font("Platino",Font.PLAIN,10);
	Font f5=new Font("Platino",Font.PLAIN,15);
	Font f6=new Font("Franklin Gothic Demi",Font.PLAIN,25);
	
	Random r=new Random();
	long i1=(r.nextInt() %9000000L)+5040936000L;
	long cardno=Math.abs(i1);
	
	long i3=(r.nextInt() % 90000L)+10000L;
	long pin=Math.abs(i3);	
	
register2_slide(){
	Connection con=register1db.dbconnect();

	jl1.setFont(f1);
	jl1.setForeground(col1);
	jl1.setBounds(120,10,550,50);
	jl1.setForeground(Color.white);
	add(jl1);
	
	jl2.setFont(f2);
	jl2.setForeground(col2);
	jl2.setBounds(50, 80, 250, 30);
	add(jl2);

	for(int i=0;i<2;i++) {
	jl3[i]=new JLabel(str1[i]);
	jl3[i].setFont(f2);
	jl3[i].setForeground(col2);
	jl3[i].setBounds(50+k, 220, 200, 30);
	add(jl3[i]);
	k=k+250;
	}
	
	for(int i=0;i<2;i++) {
	jl4[i]=new JLabel(str2[i]);	
	jl4[i].setFont(f4);
	jl4[i].setForeground(col2);
	jl4[i].setBounds(50+l, 250, 200, 12);
	add(jl4[i]);
	l=l+250;
	}
	
	for(int i=0;i<2;i++) {
		jl5[i]=new JLabel(str3[i]);	
		jl5[i].setFont(f2);
		jl5[i].setForeground(col2);
		jl5[i].setBounds(50+m, 300, 200, 30);
		add(jl5[i]);
		m=m+250;
		}
	
	jlb1.setBounds(50,330,200,12);
	jlb1.setForeground(col2);
	jlb1.setFont(f4);
	add(jlb1);
	
	jlb2.setBounds(50, 370, 200, 30);
	jlb2.setForeground(col2);
	jlb2.setFont(f2);
	add(jlb2);
	
	jlb3.setBounds(25, 470, 1000, 30);
	jlb3.setForeground(col2);
	jlb3.setFont(f5);
	add(jlb3);
	
	tf.setBounds(270, 370, 270, 30);
	tf.setFont(f3);
	add(tf);
	
	jrb1.setBounds(50, 115, 200, 27);
	jrb1.setFont(f3);
	jrb1.setForeground(col1);
	jrb1.setBackground(c);
	add(jrb1);
	
	jrb2.setBounds(300, 115, 200, 27);
	jrb2.setFont(f3);
	jrb2.setForeground(col1);
	jrb2.setBackground(c);
	add(jrb2);
	
	jrb3.setBounds(50, 150, 250, 27);
	jrb3.setFont(f3);
	jrb3.setForeground(col1);
	jrb3.setBackground(c);
	add(jrb3);
	
	jrb4.setBounds(300, 150, 250, 27);
	jrb4.setFont(f3);
	jrb4.setForeground(col1);
	jrb4.setBackground(c);
	add(jrb4);
	
	bgrp.add(jrb1);
	bgrp.add(jrb2);
	bgrp.add(jrb3);
	bgrp.add(jrb4);
	
	jbt1.setBounds(160, 540, 130, 30);
	jbt1.setFont(f6);
	jbt1.setForeground(Color.black);
	jbt1.setBackground(col3);
	add(jbt1);
	
	jbt1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {			
				String account="Savings Account";
				if(jrb2.isSelected()) {
					account="Current Account";
				}
				else if(jrb3.isSelected()){
					account="Fixed Deposit Account";
				}
				else {
					account="Recurring Deposit Account";
				}
				PreparedStatement pst=con.prepareStatement("insert into atmsys.account_details(Account_Type,EmailId,Cardnumber,Pin,Deposit,Balance,Withdraw)"
						+ "values(?,?,?,?,?,?,?)");
		        	pst.setString(1, account);
		        	pst.setString(2, tf.getText());
		        	pst.setLong(3, cardno);
		        	pst.setLong(4, pin);
		        	pst.setLong(5, 0);
		        	pst.setLong(6, 0);
		        	pst.setLong(7, 0);
		        	pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Form Submitted.");
					
					Email email=new Email("ashwinibhide2@gmail.com","@shwinibhide2020");
					email.setFrom("ashwinibhide2@gmail.com","The Boring Bank");
					email.setSubject("Welcome");
					email.setContent("<p>Dear Sir/Madam,<br>We are pleased to welcome you to the Boring Bank. We look forward for strong"
							+ " and long-term relationship with you.</p><p>This is your card no</p>"+cardno+"<p>This is your pin</p>"+pin,"text/html");
					email.addRecipient(tf.getText());
					email.send();
					JOptionPane.showMessageDialog(null,"Message sent");
					tf.setText(null);
			}
			catch(Exception ex2) {
				ex2.printStackTrace();
			}
			new welcome();
			dispose();}});
	
	jbt2.setBounds(300, 540, 130, 30);
	jbt2.setFont(f6);
	jbt2.setForeground(Color.black);
	jbt2.setBackground(col3);
	add(jbt2);
	jbt2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			System.exit(0);
		}
	});
	setLayout(null);
	setVisible(true);
	setSize(650, 650);
	ImageIcon ico=new ImageIcon("C:\\Users\\hp\\OneDrive\\Desktop\\OSMP\\AJPimages\\ATM .png");
	setIconImage(ico.getImage());
	setLocationRelativeTo(null);
	Color c=new Color(0,8,51);
	getContentPane().setBackground(c);
	setTitle("Register Page 2");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
}
public static void main(String args[]) {
	new register2_slide();
}
}

