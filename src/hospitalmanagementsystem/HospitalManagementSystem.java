package hospitalmanagementsystem;

import javax.swing.JFrame;


public class HospitalManagementSystem {

   
    public static void main(String[] args) {
        Login lg=new Login();
        lg.setLocationRelativeTo(null);
        lg.setVisible(true);
        lg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
