import java.awt.Color;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class func {
 
 public static void setColor1a(JPanel panel)
 {
     panel.setBackground(new Color(226,193,68));
 }
 public static void setColor1b(JPanel panel)
 {
     panel.setBackground(new Color(102,79,8));
 }
 public static void setColor2a(JPanel panel)
 {
     panel.setBackground(new Color(74,23,30));
 }
 public static void setColor2b(JPanel panel)
 {
     panel.setBackground(new Color(196,90,82));
 }
 public static void setColor1a(JLabel label)
 {
     label.setForeground(new Color(226,193,68));
 }
 public static void setColor2a(JLabel label)
 {
     label.setForeground(new Color(74,23,30));
 }
 public static void setColorw(JLabel label)
 {
     label.setForeground(Color.WHITE);
 }
 public static void show_pass(JPasswordField pf, JCheckBox cb){
     if (cb.isSelected()) pf.setEchoChar((char)0);
        else pf.setEchoChar('•');   
 }
 
 
  public static int name_check(String name){
        for(int i = 0; i < name.length(); ++i)
        {
            int ch = name.charAt(i);
            if((ch>64 && ch<91) || (ch>96 && ch<123) || ch==32)
                continue;
            else
                return -1;
        }
        return 1;
    }
  
  public static void show_student(JTable tb)
    {
        tb.setAutoCreateRowSorter(true);
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Student ID");
        table.addColumn("Name");
        table.addColumn("Email");
        table.addColumn("Phone Number");
    
        try
        {
            Class.forName("java.sql.DriverManager");
            Connection con = DriverManager.getConnection(url(),user(),pass());
            Statement stm = con.createStatement();
            String q = "SELECT * FROM student_data ORDER BY stu_id;";
            
            ResultSet rs = stm.executeQuery(q);

            while(rs.next())
            {
                table.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                });
            }
            tb.setModel(table);
        }
        catch(Exception e){
            e.getMessage();
        }
    }
    public static void show_attendance(JTable tb)
    {
        tb.setAutoCreateRowSorter(true);
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Student ID");
        table.addColumn("Name");
        table.addColumn("Date");
        table.addColumn("Attendance");

        try
        {
            Class.forName("java.sql.DriverManager");
            Connection con = DriverManager.getConnection(url(),user(),pass());
            Statement stm = con.createStatement();
            String q = "SELECT * FROM student_attendance ORDER BY stu_id;";
            
            ResultSet rs = stm.executeQuery(q);

            while(rs.next())
            {
                table.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                });
            }
            tb.setModel(table);
        }
        catch(Exception e){
            e.getMessage();
        }
    }
        public static void show_attendance(JTable tb, String Id)
    {
        tb.setAutoCreateRowSorter(true);
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Student ID");
        table.addColumn("Name");
        table.addColumn("Date");
        table.addColumn("Attendance");

        try
        {
            Class.forName("java.sql.DriverManager");
            Connection con = DriverManager.getConnection(url(),user(),pass());
            Statement stm = con.createStatement();
            String q = "SELECT * FROM student_attendance WHERE stu_id = '"+Id+"' ORDER BY atten_date;";
            
            ResultSet rs = stm.executeQuery(q);

            while(rs.next())
            {
                table.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                });
            }
            tb.setModel(table);
        }
        catch(Exception e){
            e.getMessage();
        }
    }
    
    public static void show_faculty(JTable tb)
    {
        tb.setAutoCreateRowSorter(true);
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("ID");
        table.addColumn("Name");
        table.addColumn("Department");
        table.addColumn("DOB");
        table.addColumn("Address");
        table.addColumn("Contact No");
        table.addColumn("Salary");
    
        try
        {
            Class.forName("java.sql.DriverManager");
            Connection con = DriverManager.getConnection(url(),user(),pass());
            Statement stm = con.createStatement();
            String q = "select * from faculty_data;";
            
            ResultSet rs = stm.executeQuery(q);

            while(rs.next())
            {
                table.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                });
            }
            tb.setModel(table);
        }
        catch(Exception e){
            e.getMessage();
        }
    }
    public static void show_faculty(JTable tb,String Id)
    {
        tb.setAutoCreateRowSorter(true);
        if(Id.length()!=0)
        {
            DefaultTableModel table = new DefaultTableModel();
            table.addColumn("ID");
            table.addColumn("Name");
            table.addColumn("Department");
            table.addColumn("DOB");
            table.addColumn("Address");
            table.addColumn("Contact No");
            table.addColumn("Salary");

            try
            {
                Class.forName("java.sql.DriverManager");
                Connection con = DriverManager.getConnection(url(),user(),pass());
                Statement stm = con.createStatement();
                String q = "select * from faculty_data where fac_id = '"+Id+"';";

                ResultSet rs = stm.executeQuery(q);

                while(rs.next())
                {
                    table.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                    });
                }
                tb.setModel(table);
            }
            catch(Exception e){
                e.getMessage();
            }
        }
    }
    
 
  public static String url(){
      return "jdbc:mysql://localhost:3306/attendancesystem";
  }
  public static String user(){
      return "root";
  }
  public static String pass(){
      return "mysql";
  }
  
    public static String generateRandomPassword(int len)
    {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance
 
        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
    }
  
  
 public static void main(String[] args)
    {
        Splash sp = new Splash();
        sp.setVisible(true);
        login sig = new login();
        try{
            for(int i=0; i<=100;i++){
                Thread.sleep(60);
                sp.bar.setValue(i);
                if(i==100){
                    sp.setVisible(false);
                    sig.setVisible(true);
                }
            }
        }
        catch(Exception e){
            e.getMessage();
        }
    }
}
