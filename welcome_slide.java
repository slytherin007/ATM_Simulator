import java.awt.*;
import java.awt.color.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class welcome_slide {
	public static void main(String args[]) {
		new welcome();
	}}

class welcome extends JFrame{
	JLabel jl1=new JLabel("WELCOME TO ATM");
	ImageIcon img=new ImageIcon("C:\\Users\\hp\\OneDrive\\Desktop\\OSMP\\AJPimages\\Money1.png");
	JLabel j1=new JLabel(img,JLabel.CENTER);
	JButton jb[]=new JButton[3];
	JLabel jl2[]=new JLabel[2];
	String str1[]= {"CARD NO: ","PIN:"};
	String str2[]= {"Enter","Register","Clear"};
	JTextField jtf=new JTextField(20);
	JPasswordField jpf=new JPasswordField(20);
	
	ImageIcon i1=new ImageIcon("C:\\Users\\hp\\OneDrive\\Desktop\\OSMP\\AJPimages\\login.png");
	ImageIcon i2=new ImageIcon("C:\\Users\\hp\\OneDrive\\Desktop\\OSMP\\AJPimages\\register.png");
	ImageIcon i3=new ImageIcon("C:\\Users\\hp\\OneDrive\\Desktop\\OSMP\\AJPimages\\clear.png");

	int k,j;
	
	Font f1=new Font("Imprint MT Shadow",Font.PLAIN,50);
	Font f2=new Font("Palatino",Font.PLAIN,30);
	Font f3=new Font("Verdana",Font.PLAIN,30);
	Font f4=new Font("Franklin Gothic Demi",Font.BOLD,25);
	
	Color col1=new Color(255,255,255);
	Color col2=new Color(0,255,0);
	Color col3=new Color(217,255,25);
	
	Border br1=BorderFactory.createLineBorder(Color.MAGENTA,2);
	welcome(){
		Connection con=register1db.dbconnect();

		j1.setBounds(250,130,150,150);
		add(j1);
		
		jl1.setFont(f1);
		jl1.setForeground(col1);
		jl1.setBounds(80,50,600,50);
		add(jl1);
		
		for(int i=0;i<2;i++) {
			jl2[i]=new JLabel(str1[i]);
			jl2[i].setBounds(100,300+k,200,30);
			jl2[i].setFont(f2);
			jl2[i].setForeground(col2);
			add(jl2[i]);
			k=k+50;}
		
		jtf.setBounds(255, 300, 200, 35);
		jtf.setFont(f3);
		add(jtf);
		
		jpf.setBounds(255, 350, 200, 35);
		jpf.setFont(f3);
		add(jpf);
		
		for(int i=0;i<3;i++){
			jb[i]=new JButton(str2[i]);
			jb[i].setBounds(50+j,455,150,40);
			jb[i].setFont(f4);
			jb[i].setForeground(Color.BLACK);
			jb[i].setBackground(col3);
			jb[i].setBorder(br1);
			add(jb[i]);
			j=j+200;}
		
		jb[0].setIcon(i1);
		jb[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{String text1=jtf.getText();
				String text2=String.valueOf(jpf.getText());
				PreparedStatement pst=con.prepareStatement("select * from atmsys.account_details where Cardnumber=? and Pin=?");
				pst.setString(1,text1);
				pst.setString(2,text2);
				ResultSet rs=pst.executeQuery();
				if(rs.next()) {
					new transaction();
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Incorrect Information");
				}}
			catch(Exception x) {
				x.printStackTrace();
			}}});
		
		jb[1].setIcon(i2);
		jb[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new register1();				
				dispose();
			}});
		
		jb[2].setIcon(i3);
		jb[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtf.setText(null);
				jpf.setText(null);
			}});
		setLayout(null);
		setVisible(true);
		setSize(650, 650);
		ImageIcon ico=new ImageIcon("C:\\Users\\hp\\OneDrive\\Desktop\\OSMP\\AJPimages\\ATM .png");
		setIconImage(ico.getImage());
		setLocationRelativeTo(null);
		Color c=new Color(0,8,51);
		getContentPane().setBackground(c);
		setTitle("ATM SIMULATOR SYSTEM");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}}