import java.sql.*;
import java.io.*;
import java.util.*;


public class Program
{
    public static void main(String[] args) {
                try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/j2ee","root","");
            
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Menu");
            System.out.println("1. Insert");
            System.out.println("2. Update");
            System.out.println("3. Delete");
            System.out.println("4. Display");
            System.out.println("5. Exit");

            Scanner sc=new Scanner(System.in);
            System.out.println("Enter your choice: ");
            int choice=sc.nextInt();
            switch (choice){
                case 1 ->
                    PreparedStatement pst=con.prepareStatement("insert into std values(?,?,?)");
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
                
                int i=pst.executeUpdate();
                System.out.println(i+"Record Inserted");
                System.out.println("Do you want to continue: y/n");
                String s=br.readLine();
                if(s.StartsWith("n"))
                {
                    System.out.println("Exit");
                    break;
                }

            }while(true);
            
         }           
    }
}