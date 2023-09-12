import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class register1db {
	Connection con=null;
	PreparedStatement pst;
		public static Connection dbconnect(){
			try {
				String url="jdbc:mysql://localhost:3306/atmsys";
				String uname="root";
				String pass="@shwinibhide2002";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection(url,uname,pass);
			return conn;
		}
			catch(Exception ex) {
				System.out.print(ex);
				return null;
			}}}
