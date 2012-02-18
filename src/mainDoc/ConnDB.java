package mainDoc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnDB 
{
	public static Connection con;
	public static void ConnDb() throws ClassNotFoundException,SQLException 
	{
		String username = "root";
		String password = "";
		
		String url = "jdbc:mysql://localhost:3306/NLP_prj";
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password);
	}
}