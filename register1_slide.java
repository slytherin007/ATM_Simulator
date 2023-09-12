import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
public class register1_slide{
	public static void main(String args[]) {
		new register1();	
}}
class register1 extends JFrame{
	JLabel jl1=new JLabel("Application Form");
	JLabel jl2=new JLabel("Page1");
	JLabel jl3[]=new JLabel[11];
	String str1[]= {"FirstName:","MiddleName:","LastName:","Address","City:",
			"Pincode:","State:","DOB(DD/MM/YY):","Gender:","MaritalStatus:"};
	
	JTextField jtf1[]=new JTextField[8];
	int k,l,j=0;	
	
	JRadioButton jrb1=new JRadioButton("Male",true);
	JRadioButton jrb2=new JRadioButton("Female");
	JRadioButton jrb3=new JRadioButton("Married");
	JRadioButton jrb4=new JRadioButton("Single",true);
	ButtonGroup bg1=new ButtonGroup();    
	ButtonGroup bg2=new ButtonGroup();   
	
	JButton jb=new JButton("Next");
	
	Color col1=new Color(255,255,255);
	Color col2=new Color(0,255,0);
	Color col3=new Color(217,255,25);
	Color col4=new Color(0,8,51);
	
	Font f1=new Font("Imprint MT Shadow",Font.PLAIN,50);
	Font f1_1=new Font("Imprint MT Shadow",Font.PLAIN,35);
	Font f2=new Font("Platino",Font.PLAIN,25);
	Font f3=new Font("Verdana",Font.PLAIN,17);
	Font f4=new Font("Franklin Gothic Demi",Font.PLAIN,25);
	
	register1(){
		
		Connection con=register1db.dbconnect();
		
		jl1.setFont(f1);
		jl1.setForeground(col1);							//Heading1
		jl1.setBounds(150,10,550,50);
		jl1.setForeground(Color.WHITE);
		add(jl1);
		
		jl2.setFont(f1_1);
		jl2.setForeground(col1);							//Heading2
		jl2.setBounds(250,70,550,40);
		jl2.setForeground(Color.WHITE);
		add(jl2);
		
		for(int i=0;i<10;i++) {
			jl3[i]=new JLabel(str1[i]);
			jl3[i].setBounds(40,120+k,200,30);				//Labels 
			jl3[i].setFont(f2);
			jl3[i].setForeground(col2);
			add(jl3[i]);
			k=k+40;
		}
		
		for(int i=0;i<8;i++) {
			jtf1[i]=new JTextField();
			jtf1[i].setBounds(270,120+j,210,27);			//first 8 textfields
			jtf1[i].setFont(f3);
			add(jtf1[i]);
			j=j+40;
		}
		
		jrb1.setBounds(270,440,80,27);				//Radio buttons
		jrb1.setForeground(col1);
		jrb1.setBackground(col4);
		jrb1.setFont(f3);
		add(jrb1);
		bg1.add(jrb1);
		
		jrb2.setBounds(350,440,210,27);
		jrb2.setForeground(col1);
		jrb2.setBackground(col4);
		jrb2.setFont(f3);
		add(jrb2);
		bg1.add(jrb2);
		
		jrb3.setBounds(270,480,90,27);
		jrb3.setForeground(col1);
		jrb3.setBackground(col4);
		jrb3.setFont(f3);
		add(jrb3);
		bg2.add(jrb3);
		
		jrb4.setBounds(370,480,210,27);
		jrb4.setForeground(col1);
		jrb4.setBackground(col4);
		jrb4.setFont(f3);
		add(jrb4);
		bg2.add(jrb4);
		
		jb.setBounds(490,530,90,40);
		jb.setFont(f4);
		jb.setForeground(Color.BLACK);
		jb.setBackground(col3);
		add(jb);
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String gender="Male";
					if(jrb2.isSelected()){
						gender="Female";}
					String marital="Single";
					if(jrb3.isSelected()){
						marital="Married";}
				
		PreparedStatement pst =con.prepareStatement("insert into atmsys.personal_details(FirstName,MiddleName,LastName,Address,City,Pincode,State,Dob,Gender,MaritalStatus) "
				+ "values(?,?,?,?,?,?,?,?,?,?)");
					pst.setString(1, jtf1[0].getText());
					pst.setString(2, jtf1[1].getText());
					pst.setString(3, jtf1[2].getText());
					pst.setString(4, jtf1[3].getText());
					pst.setString(5, jtf1[4].getText());
					pst.setInt(6, Integer.parseInt(jtf1[5].getText()));
					pst.setString(7, jtf1[6].getText());
					pst.setString(8, jtf1[7].getText());
					pst.setString(9, gender);
					pst.setString(10, marital);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data inserted");
					jtf1[0].setText(null);
					jtf1[1].setText(null);
					jtf1[2].setText(null);
					jtf1[3].setText(null);
					jtf1[4].setText(null);
					jtf1[5].setText(null);
					jtf1[6].setText(null);
					jtf1[7].setText(null);
				}
				catch(Exception e1) {
					System.out.println(e1);
				}
				new register2_slide();
				dispose();
				}});	
		
		setLayout(null);
		setVisible(true);
		setSize(650, 650);
		ImageIcon ico=new ImageIcon("C:\\Users\\hp\\OneDrive\\Desktop\\OSMP\\AJPimages\\ATM .png");
		setIconImage(ico.getImage());
		setLocationRelativeTo(null);
		Color c=new Color(0,8,51);
		getContentPane().setBackground(c);
		setTitle("Register Page 1");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}}