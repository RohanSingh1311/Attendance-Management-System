import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class admin_home extends javax.swing.JFrame {
    int xMouse, yMouse;
    int getValue;
    /**
     * Creates new form student_home
     */
    public admin_home() throws SQLException {
        initComponents();
        setBackground(new Color(0,0,0,0));
        setId();
    }

    public void clear() throws SQLException{
        setId();
        namef.setText("");
        emailf.setText("");
        phonef.setText("");
    }
    public void clear2(){
        namef1.setText("");
        depf.setText("");
        dobf.setText("");
        addressf.setText("");
        numberf.setText("");
        salaryf.setText("");
    }
    
    public void generateRegisterNo(String Query) throws SQLException{
        Connection con = DriverManager.getConnection(func.url(),func.user(),func.pass());
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(Query);
            if(rs.next())
            {
                getValue=Integer.parseInt(rs.getString(1));
            }
        }
        catch(Exception e)
        {
            //
        }
    }
    
    public void setId() throws SQLException{
        generateRegisterNo("select count(stu_id)+1 from student_data;");
        String inv = "SD"+ new SimpleDateFormat("ddMMyyy").format(new java.util.Date())+((int)(Math.random())*100)+getValue;
        idf.setText(inv);
    }
    public String setId2() throws SQLException{
        generateRegisterNo("select count(fac_id)+1 from faculty_data;");
        String inv = "FD"+ new SimpleDateFormat("ddMMyyy").format(new java.util.Date())+((int)(Math.random())*100)+getValue;
        return inv;
    }
    public void rm_student(){
        String id = idf1.getText().toUpperCase().trim();
        
        if(id.length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter ID");
        
        else{
            try{
                Class.forName("java.sql.DriverManager");
                Connection con = DriverManager.getConnection(func.url(),func.user(),func.pass());
                Statement stm = con.createStatement();
                String q = "delete from student_data where stu_id='"+id+"';";
                stm.executeUpdate(q);
                JOptionPane.showMessageDialog(this, "Removed Successfull");
                idf1.setText("");
            }

            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
    public void reg_student() throws SQLException{
        String details[]= new String[4];
        
            details[0] = idf.getText().trim();
            details[1] = namef.getText().trim();
            details[2] = emailf.getText().trim();
            details[3] = phonef.getText().trim();
        

        if(details[1].length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter Name");
        else if(details[2].length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter Email");
        else if(details[3].length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter Phone Number");
        else if(func.name_check(details[1])==-1)
            JOptionPane.showMessageDialog(this, "Please Enter Valid Name");
        else if(details[3].length()>10 || details[3].length()<10 )
            JOptionPane.showMessageDialog(this, "Please Enter Valid Phone Number");
        else{
            
            try{
                Class.forName("java.sql.DriverManager");
                Connection con = DriverManager.getConnection(func.url(),func.user(),func.pass());
                
                PreparedStatement psmnt = con.prepareCall("insert into student_data values(?,?,?,?)");
                
                psmnt.setString(1,details[0]);
                psmnt.setString(2,details[1]);
                psmnt.setString(3,details[2]);
                psmnt.setString(4,details[3]);

                psmnt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Registered Successfull");
                clear();
                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
    
    public void reg_faculty() throws SQLException{
        String details[]= new String[7];
        
            details[0] = setId2();
            details[1] = namef1.getText().trim();
            details[2] = depf.getText().trim();
            details[3] = dobf.getText().trim();
            details[4] = addressf.getText().trim();
            details[5] = numberf.getText().trim();
            details[6] = salaryf.getText().trim();
        

        if(details[1].length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter Name");
        else if(details[2].length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter Department");
        else if(details[3].length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter Date of Birth");
        else if(details[4].length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter Date of Address");
        else if(details[5].length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter Number");
        else if(details[6].length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter Salary");
        else if(func.name_check(details[1])==-1)
            JOptionPane.showMessageDialog(this, "Please Enter Valid Name");
        else if(details[5].length()>10 || details[5].length()<10 )
            JOptionPane.showMessageDialog(this, "Please Enter Valid Phone Number");
        else{
            
            try{
                Class.forName("java.sql.DriverManager");
                Connection con = DriverManager.getConnection(func.url(),func.user(),func.pass());
                
                PreparedStatement psmnt = con.prepareCall("insert into faculty_data values(?,?,?,?,?,?,?)");
                psmnt.setString(1,details[0]);
                psmnt.setString(2,details[1]);
                psmnt.setString(3,details[2]);
                psmnt.setString(4,details[3]);
                psmnt.setString(5,details[4]);
                psmnt.setString(6,details[5]);
                psmnt.setString(7,details[6]);
                psmnt.executeUpdate();
                
                PreparedStatement psmnt1 = con.prepareCall("insert into faculty_login values(?,?,?)");
                psmnt1.setString(1,details[0]);
                psmnt1.setString(2,details[1]);
                psmnt1.setString(3,func.generateRandomPassword(16));
                psmnt1.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Registered Successfull");
                clear2();
                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
    
    public void remove_faculty()
    {
        if(idf3.getText().length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter ID");
        
        else{        
            try{
                Class.forName("java.sql.DriverManager");
                Connection con = DriverManager.getConnection(func.url(),func.user(),func.pass());
                Statement stm = con.createStatement();
                String q = "delete from faculty_data where fac_id='"+idf3.getText().trim()+"';";
                stm.executeUpdate(q);
                JOptionPane.showMessageDialog(this, "Removed Successfull");
                func.show_faculty(jTable3);
                idf3.setText("");
            }

            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button_side = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        home_pane = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        add_stu_but = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        view_list_but = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        view_atten_but = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        add_fac_but = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        view_flist_but = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        logout_pane = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        welcome_side = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        close2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        add_stu_box = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        view_list_box = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        view_atten_box = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        add_fac_box = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        view_flist_box = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        stu_add_side = new javax.swing.JPanel();
        clear_pane = new javax.swing.JPanel();
        login_label = new javax.swing.JLabel();
        register_button1 = new javax.swing.JPanel();
        register_label = new javax.swing.JLabel();
        title1 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        close6 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        idf = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        namef = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        emailf = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        phonef = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        stu_list_side = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        title2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        close3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        del_stu_but = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        idf1 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        stu_atten_side = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        title3 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        close4 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        refresh_but = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        idf2 = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        search_but = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        fac_add_side = new javax.swing.JPanel();
        clear_pane1 = new javax.swing.JPanel();
        login_label1 = new javax.swing.JLabel();
        register_button2 = new javax.swing.JPanel();
        register_label1 = new javax.swing.JLabel();
        title4 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        close7 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        namef1 = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel52 = new javax.swing.JLabel();
        depf = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel53 = new javax.swing.JLabel();
        dobf = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel54 = new javax.swing.JLabel();
        addressf = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel55 = new javax.swing.JLabel();
        numberf = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel56 = new javax.swing.JLabel();
        salaryf = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        fac_list_side = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        title5 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        close5 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        ref_fac_but = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        idf3 = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        search_fac_but = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        del_fac_but = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button_side.setBackground(new java.awt.Color(74, 23, 30));
        button_side.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button_sideMousePressed(evt);
            }
        });
        button_side.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button_sideMouseDragged(evt);
            }
        });
        button_side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(226, 193, 68));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ATTENDANCE");
        button_side.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 240, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(226, 193, 68));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("MANAGEMENT");
        button_side.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 240, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(226, 193, 68));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SYSTEM");
        button_side.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 240, 30));

        home_pane.setBackground(new java.awt.Color(226, 193, 68));
        home_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(74, 23, 30));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("HOME");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        home_pane.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 50));

        button_side.add(home_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 220, 50));

        add_stu_but.setBackground(new java.awt.Color(226, 193, 68));
        add_stu_but.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(74, 23, 30));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ADD STUDENT");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });
        add_stu_but.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 50));

        button_side.add(add_stu_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 220, 50));

        view_list_but.setBackground(new java.awt.Color(226, 193, 68));
        view_list_but.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(74, 23, 30));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("VIEW STUDENT LIST");
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel26MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel26MouseExited(evt);
            }
        });
        view_list_but.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 50));

        button_side.add(view_list_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 220, 50));

        view_atten_but.setBackground(new java.awt.Color(226, 193, 68));
        view_atten_but.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(74, 23, 30));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("VIEW ATTENDANCE");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });
        view_atten_but.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 50));

        button_side.add(view_atten_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 220, 50));

        add_fac_but.setBackground(new java.awt.Color(226, 193, 68));
        add_fac_but.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(74, 23, 30));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("ADD FACULTY");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
        });
        add_fac_but.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 50));

        button_side.add(add_fac_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 220, 50));

        view_flist_but.setBackground(new java.awt.Color(226, 193, 68));
        view_flist_but.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(74, 23, 30));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("VIEW FACULTY LIST");
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel43MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel43MouseExited(evt);
            }
        });
        view_flist_but.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 50));

        button_side.add(view_flist_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 220, 50));

        logout_pane.setBackground(new java.awt.Color(226, 193, 68));
        logout_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(74, 23, 30));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("LOGOUT");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });
        logout_pane.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 50));

        button_side.add(logout_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 220, 50));

        getContentPane().add(button_side, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 540));

        welcome_side.setBackground(new java.awt.Color(226, 193, 68));
        welcome_side.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                welcome_sideMousePressed(evt);
            }
        });
        welcome_side.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                welcome_sideMouseDragged(evt);
            }
        });
        welcome_side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setBackground(new java.awt.Color(102, 79, 8));
        title.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(226, 193, 68));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("HOME/ADMIN");
        title.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 310, 90));

        close2.setBackground(new java.awt.Color(102, 79, 8));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("X");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
        });

        javax.swing.GroupLayout close2Layout = new javax.swing.GroupLayout(close2);
        close2.setLayout(close2Layout);
        close2Layout.setHorizontalGroup(
            close2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, close2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        close2Layout.setVerticalGroup(
            close2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, close2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        title.add(close2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, -1, -1));

        welcome_side.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(74, 23, 30));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("MANAGE FACULTY");
        welcome_side.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 320, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(74, 23, 30));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("MANAGE STUDENT");
        welcome_side.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 480, 40));

        add_stu_box.setBackground(new java.awt.Color(74, 23, 30));
        add_stu_box.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(226, 193, 68));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel34MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel34MouseExited(evt);
            }
        });
        add_stu_box.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 140));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(226, 193, 68));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("STUDENT");
        add_stu_box.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 160, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(226, 193, 68));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("ADD");
        add_stu_box.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 160, 30));

        welcome_side.add(add_stu_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 160, 140));

        view_list_box.setBackground(new java.awt.Color(74, 23, 30));
        view_list_box.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(226, 193, 68));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel35MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel35MouseExited(evt);
            }
        });
        view_list_box.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 140));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(226, 193, 68));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("LIST");
        view_list_box.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 160, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(226, 193, 68));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("VIEW");
        view_list_box.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 160, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(226, 193, 68));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("STUDENT");
        view_list_box.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 160, 30));

        welcome_side.add(view_list_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 160, 140));

        view_atten_box.setBackground(new java.awt.Color(74, 23, 30));
        view_atten_box.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(226, 193, 68));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel36MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel36MouseExited(evt);
            }
        });
        view_atten_box.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 140));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(226, 193, 68));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("ATTENDANCE");
        view_atten_box.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 160, 30));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(226, 193, 68));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("VIEW");
        view_atten_box.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 160, 30));

        welcome_side.add(view_atten_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 160, 140));

        add_fac_box.setBackground(new java.awt.Color(74, 23, 30));
        add_fac_box.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(226, 193, 68));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel41MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel41MouseExited(evt);
            }
        });
        add_fac_box.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 140));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(226, 193, 68));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("FACULTY");
        add_fac_box.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 160, 30));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(226, 193, 68));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("ADD");
        add_fac_box.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 160, 30));

        welcome_side.add(add_fac_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 160, 140));

        view_flist_box.setBackground(new java.awt.Color(74, 23, 30));
        view_flist_box.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(226, 193, 68));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel42MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel42MouseExited(evt);
            }
        });
        view_flist_box.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 140));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(226, 193, 68));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("VIEW");
        view_flist_box.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 160, 30));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(226, 193, 68));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("FACULTY");
        view_flist_box.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 160, 30));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(226, 193, 68));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("LIST");
        view_flist_box.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 160, 30));

        welcome_side.add(view_flist_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, 160, 140));

        getContentPane().add(welcome_side, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 560, 540));

        stu_add_side.setBackground(new java.awt.Color(226, 193, 68));
        stu_add_side.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stu_add_sideMousePressed(evt);
            }
        });
        stu_add_side.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                stu_add_sideMouseDragged(evt);
            }
        });
        stu_add_side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        clear_pane.setBackground(new java.awt.Color(74, 23, 30));
        clear_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        login_label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        login_label.setForeground(new java.awt.Color(226, 193, 68));
        login_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        login_label.setText("CLEAR");
        login_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                login_labelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                login_labelMouseExited(evt);
            }
        });
        clear_pane.add(login_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 50));

        stu_add_side.add(clear_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 170, 50));

        register_button1.setBackground(new java.awt.Color(74, 23, 30));
        register_button1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        register_label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        register_label.setForeground(new java.awt.Color(226, 193, 68));
        register_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        register_label.setText("REGISTER");
        register_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                register_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                register_labelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                register_labelMouseExited(evt);
            }
        });
        register_button1.add(register_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 50));

        stu_add_side.add(register_button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, 170, 50));

        title1.setBackground(new java.awt.Color(102, 79, 8));
        title1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(226, 193, 68));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("HOME/STUDENT/ADD");
        title1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 310, 90));

        close6.setBackground(new java.awt.Color(102, 79, 8));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("X");
        jLabel45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel45MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel45MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel45MouseExited(evt);
            }
        });

        javax.swing.GroupLayout close6Layout = new javax.swing.GroupLayout(close6);
        close6.setLayout(close6Layout);
        close6Layout.setHorizontalGroup(
            close6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, close6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        close6Layout.setVerticalGroup(
            close6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, close6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        title1.add(close6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, -1, -1));

        stu_add_side.add(title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("ID");
        stu_add_side.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        idf.setBackground(new java.awt.Color(226, 193, 68));
        idf.setEditable(false);
        idf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        idf.setForeground(new java.awt.Color(255, 255, 255));
        idf.setBorder(null);
        idf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idfActionPerformed(evt);
            }
        });
        idf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idfKeyPressed(evt);
            }
        });
        stu_add_side.add(idf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 430, 28));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        stu_add_side.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 430, 10));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("NAME");
        stu_add_side.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        namef.setBackground(new java.awt.Color(226, 193, 68));
        namef.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        namef.setForeground(new java.awt.Color(255, 255, 255));
        namef.setBorder(null);
        namef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namefActionPerformed(evt);
            }
        });
        namef.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                namefKeyPressed(evt);
            }
        });
        stu_add_side.add(namef, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 430, 28));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        stu_add_side.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 430, 10));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("EMAIL");
        stu_add_side.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        emailf.setBackground(new java.awt.Color(226, 193, 68));
        emailf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emailf.setForeground(new java.awt.Color(255, 255, 255));
        emailf.setBorder(null);
        emailf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailfActionPerformed(evt);
            }
        });
        emailf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emailfKeyPressed(evt);
            }
        });
        stu_add_side.add(emailf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 430, 28));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        stu_add_side.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 430, 10));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("PHONE NUMBER");
        stu_add_side.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, -1, -1));

        phonef.setBackground(new java.awt.Color(226, 193, 68));
        phonef.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        phonef.setForeground(new java.awt.Color(255, 255, 255));
        phonef.setBorder(null);
        phonef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phonefActionPerformed(evt);
            }
        });
        phonef.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                phonefKeyPressed(evt);
            }
        });
        stu_add_side.add(phonef, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 430, 28));

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        stu_add_side.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 430, 10));

        getContentPane().add(stu_add_side, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 560, 540));

        stu_list_side.setBackground(new java.awt.Color(226, 193, 68));
        stu_list_side.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stu_list_sideMousePressed(evt);
            }
        });
        stu_list_side.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                stu_list_sideMouseDragged(evt);
            }
        });
        stu_list_side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        stu_list_side.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 500, 260));

        title2.setBackground(new java.awt.Color(102, 79, 8));
        title2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(226, 193, 68));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("HOME/STUDENT/LIST");
        title2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 310, 90));

        close3.setBackground(new java.awt.Color(102, 79, 8));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("X");
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel22MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel22MouseExited(evt);
            }
        });

        javax.swing.GroupLayout close3Layout = new javax.swing.GroupLayout(close3);
        close3.setLayout(close3Layout);
        close3Layout.setHorizontalGroup(
            close3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, close3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        close3Layout.setVerticalGroup(
            close3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, close3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        title2.add(close3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, -1, -1));

        stu_list_side.add(title2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        del_stu_but.setBackground(new java.awt.Color(74, 23, 30));
        del_stu_but.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(226, 193, 68));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("DELETE");
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel24MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel24MouseExited(evt);
            }
        });
        del_stu_but.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 50));

        stu_list_side.add(del_stu_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 430, 150, 50));

        jLabel25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("ID");
        stu_list_side.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        idf1.setBackground(new java.awt.Color(226, 193, 68));
        idf1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        idf1.setForeground(new java.awt.Color(255, 255, 255));
        idf1.setBorder(null);
        idf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idf1ActionPerformed(evt);
            }
        });
        idf1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idf1KeyPressed(evt);
            }
        });
        stu_list_side.add(idf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 300, 28));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        stu_list_side.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 300, 10));

        getContentPane().add(stu_list_side, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 560, 540));

        stu_atten_side.setBackground(new java.awt.Color(226, 193, 68));
        stu_atten_side.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stu_atten_sideMousePressed(evt);
            }
        });
        stu_atten_side.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                stu_atten_sideMouseDragged(evt);
            }
        });
        stu_atten_side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setCellSelectionEnabled(true);
        jScrollPane2.setViewportView(jTable2);

        stu_atten_side.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 500, 260));

        title3.setBackground(new java.awt.Color(102, 79, 8));
        title3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(226, 193, 68));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("HOME/STUDENT/ATTENDANCE");
        title3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 420, 90));

        close4.setBackground(new java.awt.Color(102, 79, 8));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("X");
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel31MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel31MouseExited(evt);
            }
        });

        javax.swing.GroupLayout close4Layout = new javax.swing.GroupLayout(close4);
        close4.setLayout(close4Layout);
        close4Layout.setHorizontalGroup(
            close4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, close4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        close4Layout.setVerticalGroup(
            close4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, close4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        title3.add(close4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, -1, -1));

        stu_atten_side.add(title3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        refresh_but.setBackground(new java.awt.Color(74, 23, 30));
        refresh_but.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(226, 193, 68));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("REFRESH");
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel32MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel32MouseExited(evt);
            }
        });
        refresh_but.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 50));

        stu_atten_side.add(refresh_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 430, 110, 50));

        jLabel33.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("STUDENT ID");
        stu_atten_side.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        idf2.setBackground(new java.awt.Color(226, 193, 68));
        idf2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        idf2.setForeground(new java.awt.Color(255, 255, 255));
        idf2.setBorder(null);
        idf2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idf2ActionPerformed(evt);
            }
        });
        idf2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idf2KeyPressed(evt);
            }
        });
        stu_atten_side.add(idf2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 200, 28));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        stu_atten_side.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 200, 10));

        search_but.setBackground(new java.awt.Color(74, 23, 30));
        search_but.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(226, 193, 68));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("SEARCH");
        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel47MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel47MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel47MouseExited(evt);
            }
        });
        search_but.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 50));

        stu_atten_side.add(search_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, 110, 50));

        getContentPane().add(stu_atten_side, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 560, 540));

        fac_add_side.setBackground(new java.awt.Color(226, 193, 68));
        fac_add_side.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fac_add_sideMousePressed(evt);
            }
        });
        fac_add_side.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                fac_add_sideMouseDragged(evt);
            }
        });
        fac_add_side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        clear_pane1.setBackground(new java.awt.Color(74, 23, 30));
        clear_pane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        login_label1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        login_label1.setForeground(new java.awt.Color(226, 193, 68));
        login_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        login_label1.setText("CLEAR");
        login_label1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login_label1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                login_label1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                login_label1MouseExited(evt);
            }
        });
        clear_pane1.add(login_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 50));

        fac_add_side.add(clear_pane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 170, 50));

        register_button2.setBackground(new java.awt.Color(74, 23, 30));
        register_button2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        register_label1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        register_label1.setForeground(new java.awt.Color(226, 193, 68));
        register_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        register_label1.setText("REGISTER");
        register_label1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                register_label1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                register_label1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                register_label1MouseExited(evt);
            }
        });
        register_button2.add(register_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 50));

        fac_add_side.add(register_button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, 170, 50));

        title4.setBackground(new java.awt.Color(102, 79, 8));
        title4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(226, 193, 68));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("HOME/FACULTY/ADD");
        title4.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 310, 90));

        close7.setBackground(new java.awt.Color(102, 79, 8));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("X");
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel49MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel49MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel49MouseExited(evt);
            }
        });

        javax.swing.GroupLayout close7Layout = new javax.swing.GroupLayout(close7);
        close7.setLayout(close7Layout);
        close7Layout.setHorizontalGroup(
            close7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, close7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        close7Layout.setVerticalGroup(
            close7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, close7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        title4.add(close7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, -1, -1));

        fac_add_side.add(title4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel51.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("NAME");
        fac_add_side.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        namef1.setBackground(new java.awt.Color(226, 193, 68));
        namef1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        namef1.setForeground(new java.awt.Color(255, 255, 255));
        namef1.setBorder(null);
        namef1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namef1ActionPerformed(evt);
            }
        });
        namef1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                namef1KeyPressed(evt);
            }
        });
        fac_add_side.add(namef1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 430, 28));

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        fac_add_side.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 430, 10));

        jLabel52.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("DEPARTMENT");
        fac_add_side.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        depf.setBackground(new java.awt.Color(226, 193, 68));
        depf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        depf.setForeground(new java.awt.Color(255, 255, 255));
        depf.setBorder(null);
        depf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depfActionPerformed(evt);
            }
        });
        depf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                depfKeyPressed(evt);
            }
        });
        fac_add_side.add(depf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 430, 28));

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        fac_add_side.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 430, 10));

        jLabel53.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("DATE OF BIRTH (EX - YYYY-MM-DD)");
        fac_add_side.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        dobf.setBackground(new java.awt.Color(226, 193, 68));
        dobf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dobf.setForeground(new java.awt.Color(255, 255, 255));
        dobf.setBorder(null);
        dobf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dobfActionPerformed(evt);
            }
        });
        dobf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dobfKeyPressed(evt);
            }
        });
        fac_add_side.add(dobf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 430, 28));

        jSeparator10.setForeground(new java.awt.Color(255, 255, 255));
        fac_add_side.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 430, 10));

        jLabel54.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText("ADDRESS");
        fac_add_side.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, -1, -1));

        addressf.setBackground(new java.awt.Color(226, 193, 68));
        addressf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addressf.setForeground(new java.awt.Color(255, 255, 255));
        addressf.setBorder(null);
        addressf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressfActionPerformed(evt);
            }
        });
        addressf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addressfKeyPressed(evt);
            }
        });
        fac_add_side.add(addressf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 430, 28));

        jSeparator11.setForeground(new java.awt.Color(255, 255, 255));
        fac_add_side.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 430, 10));

        jLabel55.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("CONTACT NUMBER");
        fac_add_side.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        numberf.setBackground(new java.awt.Color(226, 193, 68));
        numberf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        numberf.setForeground(new java.awt.Color(255, 255, 255));
        numberf.setBorder(null);
        numberf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberfActionPerformed(evt);
            }
        });
        numberf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                numberfKeyPressed(evt);
            }
        });
        fac_add_side.add(numberf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 180, 28));

        jSeparator12.setForeground(new java.awt.Color(255, 255, 255));
        fac_add_side.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 180, 10));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("INR");
        fac_add_side.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 400, 40, 30));

        salaryf.setBackground(new java.awt.Color(226, 193, 68));
        salaryf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        salaryf.setForeground(new java.awt.Color(255, 255, 255));
        salaryf.setBorder(null);
        salaryf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salaryfActionPerformed(evt);
            }
        });
        salaryf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                salaryfKeyPressed(evt);
            }
        });
        fac_add_side.add(salaryf, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, 140, 28));

        jSeparator13.setForeground(new java.awt.Color(255, 255, 255));
        fac_add_side.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, 180, 10));

        jLabel57.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("SALARY");
        fac_add_side.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, -1, -1));

        getContentPane().add(fac_add_side, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 560, 540));

        fac_list_side.setBackground(new java.awt.Color(226, 193, 68));
        fac_list_side.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fac_list_sideMousePressed(evt);
            }
        });
        fac_list_side.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                fac_list_sideMouseDragged(evt);
            }
        });
        fac_list_side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable3.setCellSelectionEnabled(true);
        jScrollPane3.setViewportView(jTable3);
        jTable3.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        fac_list_side.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 500, 260));

        title5.setBackground(new java.awt.Color(102, 79, 8));
        title5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(226, 193, 68));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("HOME/FACULTY/LIST");
        title5.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 310, 90));

        close5.setBackground(new java.awt.Color(102, 79, 8));

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("X");
        jLabel58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel58MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel58MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel58MouseExited(evt);
            }
        });

        javax.swing.GroupLayout close5Layout = new javax.swing.GroupLayout(close5);
        close5.setLayout(close5Layout);
        close5Layout.setHorizontalGroup(
            close5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, close5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        close5Layout.setVerticalGroup(
            close5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, close5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        title5.add(close5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, -1, -1));

        fac_list_side.add(title5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ref_fac_but.setBackground(new java.awt.Color(74, 23, 30));
        ref_fac_but.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(226, 193, 68));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("REFRESH");
        jLabel59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel59MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel59MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel59MouseExited(evt);
            }
        });
        ref_fac_but.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 50));

        fac_list_side.add(ref_fac_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 430, 100, 50));

        jLabel60.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("ID");
        fac_list_side.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        idf3.setBackground(new java.awt.Color(226, 193, 68));
        idf3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        idf3.setForeground(new java.awt.Color(255, 255, 255));
        idf3.setBorder(null);
        idf3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idf3ActionPerformed(evt);
            }
        });
        idf3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idf3KeyPressed(evt);
            }
        });
        fac_list_side.add(idf3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 130, 28));

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        fac_list_side.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 130, 10));

        search_fac_but.setBackground(new java.awt.Color(74, 23, 30));
        search_fac_but.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(226, 193, 68));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("SEARCH");
        jLabel61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel61MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel61MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel61MouseExited(evt);
            }
        });
        search_fac_but.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 50));

        fac_list_side.add(search_fac_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 100, 50));

        del_fac_but.setBackground(new java.awt.Color(74, 23, 30));
        del_fac_but.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(226, 193, 68));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("DELETE");
        jLabel62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel62MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel62MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel62MouseExited(evt);
            }
        });
        del_fac_but.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 50));

        fac_list_side.add(del_fac_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, 100, 50));

        getContentPane().add(fac_list_side, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 560, 540));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-804)/2, (screenSize.height-545)/2, 804, 545);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
func.setColor2b(home_pane);
func.setColorw(jLabel5);
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
func.setColor1a(home_pane);
func.setColor2a(jLabel5);
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        func.setColor1a(close2);

    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        func.setColor1b(close2);
    }//GEN-LAST:event_jLabel12MouseExited

    private void welcome_sideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_welcome_sideMousePressed
 
    }//GEN-LAST:event_welcome_sideMousePressed

    private void welcome_sideMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_welcome_sideMouseDragged
 
    }//GEN-LAST:event_welcome_sideMouseDragged

    private void button_sideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_sideMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_button_sideMousePressed

    private void button_sideMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_sideMouseDragged
        int x = evt.getXOnScreen(), y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_button_sideMouseDragged

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        welcome_side.setVisible(true);
        stu_add_side.setVisible(false);
        stu_list_side.setVisible(false);
        stu_atten_side.setVisible(false);
        fac_add_side.setVisible(false);
        fac_list_side.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        stu_add_side.setVisible(true);
        welcome_side.setVisible(false);
        stu_list_side.setVisible(false);
        stu_atten_side.setVisible(false);
        fac_add_side.setVisible(false);
        fac_list_side.setVisible(false);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
func.setColor2b(add_stu_but);
func.setColorw(jLabel6);
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
func.setColor1a(add_stu_but);
func.setColor2a(jLabel6);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
func.setColor2b(view_atten_but);
func.setColorw(jLabel7);
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
func.setColor1a(view_atten_but);
func.setColor2a(jLabel7);  
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
func.setColor2b(logout_pane);
func.setColorw(jLabel4);
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        func.setColor1a(logout_pane);
        func.setColor2a(jLabel4);
    }//GEN-LAST:event_jLabel4MouseExited

    private void login_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_labelMouseClicked
        try {
            clear();
        } catch (SQLException ex) {
            Logger.getLogger(admin_home.class.getName()).log(Level.SEVERE, null, ex);
        }
   }//GEN-LAST:event_login_labelMouseClicked

    private void login_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_labelMouseEntered
        func.setColor2b(clear_pane);
        func.setColorw(login_label);
    }//GEN-LAST:event_login_labelMouseEntered

    private void login_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_labelMouseExited
        func.setColor2a(clear_pane);
        func.setColor1a(login_label);
    }//GEN-LAST:event_login_labelMouseExited

    private void register_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_register_labelMouseClicked
        try {
            reg_student();
        } catch (SQLException ex) {
            Logger.getLogger(admin_home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_register_labelMouseClicked

    private void register_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_register_labelMouseEntered
        func.setColor2b(register_button1);
        func.setColorw(register_label);
    }//GEN-LAST:event_register_labelMouseEntered

    private void register_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_register_labelMouseExited
        func.setColor2a(register_button1);
        func.setColor1a(register_label);
    }//GEN-LAST:event_register_labelMouseExited

    private void stu_add_sideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stu_add_sideMousePressed

   }//GEN-LAST:event_stu_add_sideMousePressed

    private void stu_add_sideMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stu_add_sideMouseDragged

   }//GEN-LAST:event_stu_add_sideMouseDragged

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        new login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void stu_list_sideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stu_list_sideMousePressed

   }//GEN-LAST:event_stu_list_sideMousePressed

    private void stu_list_sideMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stu_list_sideMouseDragged

   }//GEN-LAST:event_stu_list_sideMouseDragged

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
func.show_attendance(jTable2);
stu_atten_side.setVisible(true);
stu_list_side.setVisible(false);
welcome_side.setVisible(false);
stu_add_side.setVisible(false);
fac_add_side.setVisible(false);
fac_list_side.setVisible(false);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
func.show_student(jTable1);
stu_list_side.setVisible(true);
welcome_side.setVisible(false);
stu_add_side.setVisible(false);
stu_atten_side.setVisible(false);
fac_add_side.setVisible(false);
fac_list_side.setVisible(false);
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseEntered
func.setColor2b(view_list_but);
func.setColorw(jLabel26);
    }//GEN-LAST:event_jLabel26MouseEntered

    private void jLabel26MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseExited
 func.setColor1a(view_list_but);
func.setColor2a(jLabel26); 
    }//GEN-LAST:event_jLabel26MouseExited

    private void jLabel34MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseEntered
func.setColor2b(add_stu_box);
func.setColorw(jLabel14);
func.setColorw(jLabel15);
    }//GEN-LAST:event_jLabel34MouseEntered

    private void jLabel34MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseExited
func.setColor2a(add_stu_box);
func.setColor1a(jLabel14);
func.setColor1a(jLabel15);
    }//GEN-LAST:event_jLabel34MouseExited

    private void jLabel35MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseEntered
func.setColor2b(view_list_box);
func.setColorw(jLabel16);
func.setColorw(jLabel27);
func.setColorw(jLabel23);
    }//GEN-LAST:event_jLabel35MouseEntered

    private void jLabel35MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseExited
func.setColor2a(view_list_box);
func.setColor1a(jLabel16);
func.setColor1a(jLabel27);
func.setColor1a(jLabel23);
    }//GEN-LAST:event_jLabel35MouseExited

    private void jLabel36MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseEntered
func.setColor2b(view_atten_box);
func.setColorw(jLabel28);
func.setColorw(jLabel29);
    }//GEN-LAST:event_jLabel36MouseEntered

    private void jLabel36MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseExited
func.setColor2a(view_atten_box);
func.setColor1a(jLabel28);
func.setColor1a(jLabel29);
    }//GEN-LAST:event_jLabel36MouseExited

    private void jLabel41MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseEntered
func.setColor2b(add_fac_box);
func.setColorw(jLabel37);
func.setColorw(jLabel38);
    }//GEN-LAST:event_jLabel41MouseEntered

    private void jLabel41MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseExited
func.setColor2a(add_fac_box);
func.setColor1a(jLabel37);
func.setColor1a(jLabel38);
    }//GEN-LAST:event_jLabel41MouseExited

    private void jLabel42MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseEntered
func.setColor2b(view_flist_box);
func.setColorw(jLabel39);
func.setColorw(jLabel40);
func.setColorw(jLabel46);
    }//GEN-LAST:event_jLabel42MouseEntered

    private void jLabel42MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseExited
func.setColor2a(view_flist_box);
func.setColor1a(jLabel39);
func.setColor1a(jLabel40);
func.setColor1a(jLabel46);
    }//GEN-LAST:event_jLabel42MouseExited

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
fac_add_side.setVisible(true);
stu_atten_side.setVisible(false);
stu_list_side.setVisible(false);
welcome_side.setVisible(false);
stu_add_side.setVisible(false);
fac_list_side.setVisible(false);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
func.setColor2b(add_fac_but);
func.setColorw(jLabel8);
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
func.setColor1a(add_fac_but);
func.setColor2a(jLabel8);
    }//GEN-LAST:event_jLabel8MouseExited

    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
func.show_faculty(jTable3);
fac_list_side.setVisible(true);
fac_add_side.setVisible(false);
stu_atten_side.setVisible(false);
stu_list_side.setVisible(false);
welcome_side.setVisible(false);
stu_add_side.setVisible(false);
    }//GEN-LAST:event_jLabel43MouseClicked

    private void jLabel43MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseEntered
func.setColor2b(view_flist_but);
func.setColorw(jLabel43);
    }//GEN-LAST:event_jLabel43MouseEntered

    private void jLabel43MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseExited
func.setColor1a(view_flist_but);
func.setColor2a(jLabel43);
    }//GEN-LAST:event_jLabel43MouseExited

    private void jLabel45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel45MouseClicked
System.exit(0);
    }//GEN-LAST:event_jLabel45MouseClicked

    private void jLabel45MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel45MouseEntered
func.setColor1a(close6);
    }//GEN-LAST:event_jLabel45MouseEntered

    private void jLabel45MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel45MouseExited
func.setColor1b(close6);
    }//GEN-LAST:event_jLabel45MouseExited

    private void idfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idfActionPerformed

    private void idfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idfKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                reg_student();
            } catch (SQLException ex) {
                Logger.getLogger(admin_home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_idfKeyPressed

    private void namefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namefActionPerformed

    private void namefKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namefKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                reg_student();
            } catch (SQLException ex) {
                Logger.getLogger(admin_home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_namefKeyPressed

    private void emailfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailfActionPerformed

    private void emailfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailfKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                reg_student();
            } catch (SQLException ex) {
                Logger.getLogger(admin_home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_emailfKeyPressed

    private void phonefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phonefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phonefActionPerformed

    private void phonefKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phonefKeyPressed
if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try {
                reg_student();
            } catch (SQLException ex) {
                Logger.getLogger(admin_home.class.getName()).log(Level.SEVERE, null, ex);
            }
}
    }//GEN-LAST:event_phonefKeyPressed

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        stu_add_side.setVisible(true);
        welcome_side.setVisible(false);
        stu_list_side.setVisible(false);
        stu_atten_side.setVisible(false);
        fac_add_side.setVisible(false);
        fac_list_side.setVisible(false);
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
System.exit(0);
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel22MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseEntered
func.setColor1a(close3);
    }//GEN-LAST:event_jLabel22MouseEntered

    private void jLabel22MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseExited
func.setColor1b(close3);
    }//GEN-LAST:event_jLabel22MouseExited

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
    rm_student();
    func.show_student(jTable1);
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel24MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseEntered
func.setColor2b(del_stu_but);
func.setColorw(jLabel24);
    }//GEN-LAST:event_jLabel24MouseEntered

    private void jLabel24MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseExited
func.setColor2a(del_stu_but);
func.setColor1a(jLabel24);
    }//GEN-LAST:event_jLabel24MouseExited

    private void idf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idf1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idf1ActionPerformed

    private void idf1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idf1KeyPressed
    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            rm_student();
            func.show_student(jTable1);
    }
    }//GEN-LAST:event_idf1KeyPressed

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
func.show_student(jTable1);
stu_list_side.setVisible(true);
welcome_side.setVisible(false);
stu_add_side.setVisible(false);
stu_atten_side.setVisible(false);
fac_add_side.setVisible(false);
fac_list_side.setVisible(false);
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
System.exit(0);
    }//GEN-LAST:event_jLabel31MouseClicked

    private void jLabel31MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseEntered
func.setColor1a(close4);
    }//GEN-LAST:event_jLabel31MouseEntered

    private void jLabel31MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseExited
func.setColor1b(close4);
    }//GEN-LAST:event_jLabel31MouseExited

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
idf2.setText("");
func.show_attendance(jTable2);
    }//GEN-LAST:event_jLabel32MouseClicked

    private void jLabel32MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseEntered
func.setColor2b(refresh_but);
func.setColorw(jLabel32);
    }//GEN-LAST:event_jLabel32MouseEntered

    private void jLabel32MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseExited
func.setColor2a(refresh_but);
func.setColor1a(jLabel32);
    }//GEN-LAST:event_jLabel32MouseExited

    private void idf2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idf2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idf2ActionPerformed

    private void idf2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idf2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_idf2KeyPressed

    private void stu_atten_sideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stu_atten_sideMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_stu_atten_sideMousePressed

    private void stu_atten_sideMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stu_atten_sideMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_stu_atten_sideMouseDragged

    private void jLabel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseClicked
String id = idf2.getText().toUpperCase().trim();
func.show_attendance(jTable2, id);
    }//GEN-LAST:event_jLabel47MouseClicked

    private void jLabel47MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseEntered
func.setColor2b(search_but);
func.setColorw(jLabel47);
    }//GEN-LAST:event_jLabel47MouseEntered

    private void jLabel47MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseExited
func.setColor2a(search_but);
func.setColor1a(jLabel47);
    }//GEN-LAST:event_jLabel47MouseExited

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
func.show_attendance(jTable2);
stu_atten_side.setVisible(true);
stu_list_side.setVisible(false);
welcome_side.setVisible(false);
stu_add_side.setVisible(false);
fac_add_side.setVisible(false);
fac_list_side.setVisible(false);
    }//GEN-LAST:event_jLabel36MouseClicked

    private void login_label1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_label1MouseClicked
clear2();
    }//GEN-LAST:event_login_label1MouseClicked

    private void login_label1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_label1MouseEntered
func.setColor2b(clear_pane1);
func.setColorw(login_label1);
    }//GEN-LAST:event_login_label1MouseEntered

    private void login_label1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_label1MouseExited
func.setColor2a(clear_pane1);
func.setColor1a(login_label1);
    }//GEN-LAST:event_login_label1MouseExited

    private void register_label1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_register_label1MouseClicked
        try {
            reg_faculty();
            
        } catch (SQLException ex) {
            Logger.getLogger(admin_home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_register_label1MouseClicked

    private void register_label1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_register_label1MouseEntered
func.setColor2b(register_button2);
func.setColorw(register_label1);
    }//GEN-LAST:event_register_label1MouseEntered

    private void register_label1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_register_label1MouseExited
func.setColor2a(register_button2);
func.setColor1a(register_label1);
    }//GEN-LAST:event_register_label1MouseExited

    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked
System.exit(0);
    }//GEN-LAST:event_jLabel49MouseClicked

    private void jLabel49MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseEntered
func.setColor1a(close7);
    }//GEN-LAST:event_jLabel49MouseEntered

    private void jLabel49MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseExited
func.setColor1b(close7);
    }//GEN-LAST:event_jLabel49MouseExited

    private void namef1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namef1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namef1ActionPerformed

    private void namef1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namef1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_namef1KeyPressed

    private void depfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_depfActionPerformed

    private void depfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_depfKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_depfKeyPressed

    private void dobfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dobfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dobfActionPerformed

    private void dobfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dobfKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_dobfKeyPressed

    private void fac_add_sideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fac_add_sideMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_fac_add_sideMousePressed

    private void fac_add_sideMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fac_add_sideMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_fac_add_sideMouseDragged

    private void addressfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressfActionPerformed

    private void addressfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressfKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressfKeyPressed

    private void numberfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberfActionPerformed

    private void numberfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numberfKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberfKeyPressed

    private void salaryfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salaryfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salaryfActionPerformed

    private void salaryfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salaryfKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_salaryfKeyPressed

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
fac_add_side.setVisible(true);
stu_atten_side.setVisible(false);
stu_list_side.setVisible(false);
welcome_side.setVisible(false);
stu_add_side.setVisible(false);
fac_list_side.setVisible(false);
    }//GEN-LAST:event_jLabel41MouseClicked

    private void jLabel58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel58MouseClicked
System.exit(0);
    }//GEN-LAST:event_jLabel58MouseClicked

    private void jLabel58MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel58MouseEntered
func.setColor1a(close5);
    }//GEN-LAST:event_jLabel58MouseEntered

    private void jLabel58MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel58MouseExited
func.setColor1b(close5);
    }//GEN-LAST:event_jLabel58MouseExited

    private void jLabel59MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel59MouseClicked
func.show_faculty(jTable3);
idf3.setText("");
    }//GEN-LAST:event_jLabel59MouseClicked

    private void jLabel59MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel59MouseEntered
func.setColor2b(ref_fac_but);
func.setColorw(jLabel59);
    }//GEN-LAST:event_jLabel59MouseEntered

    private void jLabel59MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel59MouseExited
func.setColor2a(ref_fac_but);
func.setColor1a(jLabel59);
    }//GEN-LAST:event_jLabel59MouseExited

    private void idf3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idf3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idf3ActionPerformed

    private void idf3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idf3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_idf3KeyPressed

    private void fac_list_sideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fac_list_sideMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_fac_list_sideMousePressed

    private void fac_list_sideMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fac_list_sideMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_fac_list_sideMouseDragged

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
func.show_faculty(jTable3);
fac_list_side.setVisible(true);
fac_add_side.setVisible(false);
stu_atten_side.setVisible(false);
stu_list_side.setVisible(false);
welcome_side.setVisible(false);
stu_add_side.setVisible(false);
    }//GEN-LAST:event_jLabel42MouseClicked

    private void jLabel61MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseClicked
        String id = idf3.getText();    
        func.show_faculty(jTable3, id);
    }//GEN-LAST:event_jLabel61MouseClicked

    private void jLabel61MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseEntered
func.setColor2b(search_fac_but);
func.setColorw(jLabel61);
    }//GEN-LAST:event_jLabel61MouseEntered

    private void jLabel61MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseExited
func.setColor2a(search_fac_but);
func.setColor1a(jLabel61);
    }//GEN-LAST:event_jLabel61MouseExited

    private void jLabel62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel62MouseClicked
remove_faculty();
    }//GEN-LAST:event_jLabel62MouseClicked

    private void jLabel62MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel62MouseEntered
func.setColor2b(del_fac_but);
func.setColorw(jLabel62);
    }//GEN-LAST:event_jLabel62MouseEntered

    private void jLabel62MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel62MouseExited
func.setColor2a(del_fac_but);
func.setColor1a(jLabel62);
    }//GEN-LAST:event_jLabel62MouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(admin_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new admin_home().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(admin_home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel add_fac_box;
    private javax.swing.JPanel add_fac_but;
    private javax.swing.JPanel add_stu_box;
    private javax.swing.JPanel add_stu_but;
    private javax.swing.JTextField addressf;
    private javax.swing.JPanel button_side;
    private javax.swing.JPanel clear_pane;
    private javax.swing.JPanel clear_pane1;
    public static javax.swing.JPanel close2;
    public static javax.swing.JPanel close3;
    public static javax.swing.JPanel close4;
    public static javax.swing.JPanel close5;
    public static javax.swing.JPanel close6;
    public static javax.swing.JPanel close7;
    private javax.swing.JPanel del_fac_but;
    private javax.swing.JPanel del_stu_but;
    private javax.swing.JTextField depf;
    private javax.swing.JTextField dobf;
    private javax.swing.JTextField emailf;
    private javax.swing.JPanel fac_add_side;
    private javax.swing.JPanel fac_list_side;
    private javax.swing.JPanel home_pane;
    private javax.swing.JTextField idf;
    private javax.swing.JTextField idf1;
    private javax.swing.JTextField idf2;
    private javax.swing.JTextField idf3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel login_label;
    private javax.swing.JLabel login_label1;
    private javax.swing.JPanel logout_pane;
    private javax.swing.JTextField namef;
    private javax.swing.JTextField namef1;
    private javax.swing.JTextField numberf;
    private javax.swing.JTextField phonef;
    private javax.swing.JPanel ref_fac_but;
    private javax.swing.JPanel refresh_but;
    private javax.swing.JPanel register_button1;
    private javax.swing.JPanel register_button2;
    private javax.swing.JLabel register_label;
    private javax.swing.JLabel register_label1;
    private javax.swing.JTextField salaryf;
    private javax.swing.JPanel search_but;
    private javax.swing.JPanel search_fac_but;
    private javax.swing.JPanel stu_add_side;
    private javax.swing.JPanel stu_atten_side;
    private javax.swing.JPanel stu_list_side;
    private javax.swing.JPanel title;
    private javax.swing.JPanel title1;
    private javax.swing.JPanel title2;
    private javax.swing.JPanel title3;
    private javax.swing.JPanel title4;
    private javax.swing.JPanel title5;
    private javax.swing.JPanel view_atten_box;
    private javax.swing.JPanel view_atten_but;
    private javax.swing.JPanel view_flist_box;
    private javax.swing.JPanel view_flist_but;
    private javax.swing.JPanel view_list_box;
    private javax.swing.JPanel view_list_but;
    private javax.swing.JPanel welcome_side;
    // End of variables declaration//GEN-END:variables
}
