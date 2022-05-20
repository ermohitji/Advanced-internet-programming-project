package src;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginForm extends JDialog {
    private JTextField textField1;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel LoginP;
    private JPasswordField passwordField1;

    public LoginForm(){
        setTitle("Login");
        setContentPane(LoginP);
        setMinimumSize(new Dimension(450,380));
        setModal(true);
        OKButton.addActionListener(e-> {
            try {
                loginUser();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        cancelButton.addActionListener(e->dispose());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loginUser() throws SQLException, ClassNotFoundException {
        String email = textField1.getText();
        String password = String.valueOf(passwordField1.getPassword());

        if(email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all the fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mohitdb","root","mysql");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from student");
        while(rs.next()){
            if(email.equals(rs.getString("email")) && password.equals(rs.getString("password"))){
                System.out.println("\nUid:\t"+" "+rs.getString("uid"));
                System.out.println("Name:\t"+" "+rs.getString("name"));
                System.out.println("course:\t"+" "+rs.getString("course"));
                System.out.println("Section:"+" "+rs.getString("section"));
                st.close();
                con.close();
                return;
            }
        }
        st.close();
        con.close();
        System.out.println("Incorrect email or password!");
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        LoginForm loginForm = new LoginForm();
        loginForm.loginUser();
    }
}
