package src.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class form {
    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mohitdb","root","mysql");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from student");
        while(rs.next()){
            System.out.println("\nUid:\t"+" "+rs.getString("uid"));
            System.out.println("Name:\t"+" "+rs.getString("name"));
            System.out.println("course:\t"+" "+rs.getString("course"));
            System.out.println("Section:"+" "+rs.getString("section"));
        }
        st.close();
        con.close();
    }
}
