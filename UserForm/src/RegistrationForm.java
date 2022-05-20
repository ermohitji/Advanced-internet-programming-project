package src;

import src.main.User;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegistrationForm extends JDialog {
    private JTextField tfName;
    private JTextField tfCourse;
    private JTextField tfSection;
    private JTextField tfEmail;
    private JPasswordField pfpass;
    private JPasswordField pfConfirmPass;
    private JButton registerButton;
    private JButton cancelButton;
    private JTextField tfUid;
    private JPanel RegisterPanel;
    public RegistrationForm(){
        setTitle("Create a new account");
        setContentPane(RegisterPanel);
        setMinimumSize(new Dimension(800,600));
        setModal(true);

        registerButton.addActionListener(e -> registerUser());
        cancelButton.addActionListener(e-> dispose());
        setVisible(true);
    }

    private void registerUser() {
        String name = tfName.getText();
        String uid = tfUid.getText();
        String course = tfCourse.getText();
        String section = tfSection.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(pfpass.getPassword());
        String conPassword = String.valueOf(pfConfirmPass.getPassword());

        if(name.isEmpty() || uid.isEmpty() || course.isEmpty() || section.isEmpty() || email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!password.equals(conPassword)){
            JOptionPane.showMessageDialog(this,
                    "Confirm passwaord does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        user = addUserToDatabase(name,uid,course,section,email,password);
        if(user != null){
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;
    private User addUserToDatabase(String name, String uid, String course, String section, String email, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/mohitdb";
        try{
            Connection con = DriverManager.getConnection(DB_URL,"root","mysql");
            //connected to database successfully...
            Statement stmt = con.createStatement();
            String sql = "insert into student(name,uid,course,section,email,password) values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,uid);
            preparedStatement.setString(3,course);
            preparedStatement.setString(4,section);
            preparedStatement.setString(5,email);
            preparedStatement.setString(6,password);

            //Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0){
                user = new User();
                user.uid = uid;
                user.name = name;
                user.course = course;
                user.section = section;
                user.email = email;
                user.password = password;
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args){
        RegistrationForm myForm = new RegistrationForm();
        User user = myForm.user;
        if(user != null){
            System.out.println("Successful registration of: "+user.name);
        }
        else{
            System.out.println("Registration canceled");
        }
    }
}
