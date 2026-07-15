import java.sql.*;
import java.io.*;

public class Program
{
    public static void main(String[] args) {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/j2ee""root""");
            PreparedStatement pst=con.prepareStatement("insert into std values(?,?,?)");
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            do
            {
                System.out.println("Enter id:");
                int id=Integer.parseInt(br.readLine());
                System.out.println("Enter name:");
                String name=br.readLine();
                System.out.println("Enter city:");
                String city=br.readLine();
                
                pst.setInt(1,id);
                pst.setString(2,name);
                pst.setString(3,city);
                
            }
            
        }
    }
}