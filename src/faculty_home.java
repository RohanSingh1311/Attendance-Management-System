import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableModel;

public class faculty_home extends javax.swing.JFrame {
    int xMouse, yMouse;
    int getValue;
    /**
     * Creates new form student_home
     */
    public faculty_home() throws SQLException {
        initComponents();
        setBackground(new Color(0,0,0,0));
        add_checkbox();
        cb1.setUI(new MyComboBoxUI());
    }
    
    public static class MyComboBoxUI extends BasicComboBoxUI {
        @Override
        protected void installDefaults() {
            super.installDefaults();
            LookAndFeel.uninstallBorder(comboBox);
        }

        @Override
        public void configureArrowButton() {
            super.configureArrowButton(); //Do not forget this!
            arrowButton.setBackground(new Color(74,23,30));
            arrowButton.setForeground(Color.WHITE);
        }
    }
    
    public int check_rec(String Query) throws SQLException{
        Connection con = DriverManager.getConnection(func.url(),func.user(),func.pass());
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(Query);
            if(rs.next())
            {
                return Integer.parseInt(rs.getString(1));
            }
        }
        catch(Exception e)
        {
            //
        }
        return -1;
    }

    public void clear() throws SQLException{
        cb1.setSelectedIndex(0);
        cb1.setUI(new MyComboBoxUI());
        namef.setText("");
        datef.setText("");
    }
    public void add_checkbox(){
        try
            {
                Class.forName("java.sql.DriverManager");
                Connection con = DriverManager.getConnection(func.url(),func.user(),func.pass());
                Statement stm = con.createStatement();
                String q = "select * from student_data;";
                ResultSet rs = stm.executeQuery(q);
                while(rs.next())
                {
                    cb1.addItem(rs.getString(1));
                }
                    
             }
             catch(Exception e)
             {
                JOptionPane.showMessageDialog(this, e.getMessage());
             }
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
    
     public void check_stu(String n){
        try
            {
                Class.forName("java.sql.DriverManager");
                Connection con = DriverManager.getConnection(func.url(),func.user(),func.pass());
                Statement stm = con.createStatement();
                String q = "select * from student_data where stu_id ='"+n+"';";
                ResultSet rs = stm.executeQuery(q);
                if(rs.next())
                {
                    namef.setText(rs.getString(2));
                }
                    
             }
             catch(Exception e)
             {
                JOptionPane.showMessageDialog(this, e.getMessage());
             }
    }
    
    public void mark_attendance() throws SQLException{
        int ind = cb1.getSelectedIndex();
        String details[]= new String[4];
        
            details[0] = (String) cb1.getItemAt(ind);
            details[1] = namef.getText().trim();
            details[2] = datef.getText().trim();
            details[3] = present_button.isEnabled()?"Present":"Absent";
        

        if(ind==0)
            JOptionPane.showMessageDialog(this, "Please Select ID");
        else if(details[2].length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter Date");
        
        else if(check_rec("select count(*) from student_attendance where stu_id = '"+details[0]+"' and atten_date = '"+details[2]+"';")>0)
            JOptionPane.showMessageDialog(this, "Attendance Already Done");
        
        else{
            
            try{
                Class.forName("java.sql.DriverManager");
                Connection con = DriverManager.getConnection(func.url(),func.user(),func.pass());
                
                PreparedStatement psmnt = con.prepareCall("insert into student_attendance values(?,?,?,?)");
                
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
        logout_pane = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        welcome_side = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        close2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        mark_atten_box = new javax.swing.JPanel();
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
        mark_att_side = new javax.swing.JPanel();
        clear_pane = new javax.swing.JPanel();
        login_label = new javax.swing.JLabel();
        register_button1 = new javax.swing.JPanel();
        register_label = new javax.swing.JLabel();
        title1 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        close6 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        namef = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        datef = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        cb1 = new javax.swing.JComboBox();
        absent_button = new javax.swing.JPanel();
        student_label = new javax.swing.JLabel();
        present_button = new javax.swing.JPanel();
        admin_label = new javax.swing.JLabel();
        cur_date = new javax.swing.JPanel();
        c_d = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
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
        jLabel6.setText("MARK ATTENDANCE");
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

        button_side.add(view_list_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 220, 50));

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

        button_side.add(view_atten_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 220, 50));

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
        jLabel9.setText("HOME/FACULTY");
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

        mark_atten_box.setBackground(new java.awt.Color(74, 23, 30));
        mark_atten_box.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        mark_atten_box.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(226, 193, 68));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("ATTENDANCE");
        mark_atten_box.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 180, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(226, 193, 68));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("MARK");
        mark_atten_box.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 180, 30));

        welcome_side.add(mark_atten_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 180, 160));

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
        view_list_box.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 160));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(226, 193, 68));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("LIST");
        view_list_box.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 180, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(226, 193, 68));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("VIEW");
        view_list_box.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 180, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(226, 193, 68));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("STUDENT");
        view_list_box.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 180, 30));

        welcome_side.add(view_list_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 180, 160));

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
        view_atten_box.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 160));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(226, 193, 68));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("ATTENDANCE");
        view_atten_box.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 180, 30));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(226, 193, 68));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("VIEW");
        view_atten_box.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 180, 30));

        welcome_side.add(view_atten_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 350, 180, 160));

        getContentPane().add(welcome_side, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 560, 540));

        mark_att_side.setBackground(new java.awt.Color(226, 193, 68));
        mark_att_side.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mark_att_sideMousePressed(evt);
            }
        });
        mark_att_side.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                mark_att_sideMouseDragged(evt);
            }
        });
        mark_att_side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        mark_att_side.add(clear_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 170, 50));

        register_button1.setBackground(new java.awt.Color(74, 23, 30));
        register_button1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        register_label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        register_label.setForeground(new java.awt.Color(226, 193, 68));
        register_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        register_label.setText("SUBMIT");
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

        mark_att_side.add(register_button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, 170, 50));

        title1.setBackground(new java.awt.Color(102, 79, 8));
        title1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(226, 193, 68));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("HOME/MARK ATTENDANCE");
        title1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 360, 90));

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

        mark_att_side.add(title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("STUDENT ID");
        mark_att_side.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("NAME");
        mark_att_side.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        namef.setBackground(new java.awt.Color(226, 193, 68));
        namef.setEditable(false);
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
        mark_att_side.add(namef, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 430, 28));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        mark_att_side.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 430, 10));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("STATUS");
        mark_att_side.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        datef.setBackground(new java.awt.Color(226, 193, 68));
        datef.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        datef.setForeground(new java.awt.Color(255, 255, 255));
        datef.setBorder(null);
        datef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datefActionPerformed(evt);
            }
        });
        datef.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                datefKeyPressed(evt);
            }
        });
        mark_att_side.add(datef, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 260, 28));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        mark_att_side.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 260, 10));

        cb1.setBackground(new java.awt.Color(74, 23, 30));
        cb1.setForeground(new java.awt.Color(226, 193, 68));
        cb1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select ID" }));
        cb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb1ActionPerformed(evt);
            }
        });
        mark_att_side.add(cb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 430, 30));

        absent_button.setBackground(new java.awt.Color(74, 23, 30));
        absent_button.setEnabled(false);

        student_label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        student_label.setForeground(new java.awt.Color(226, 193, 68));
        student_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        student_label.setText("ABSENT");
        student_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                student_labelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                student_labelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout absent_buttonLayout = new javax.swing.GroupLayout(absent_button);
        absent_button.setLayout(absent_buttonLayout);
        absent_buttonLayout.setHorizontalGroup(
            absent_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(student_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        absent_buttonLayout.setVerticalGroup(
            absent_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(student_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        mark_att_side.add(absent_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 160, 40));

        present_button.setBackground(new java.awt.Color(196, 90, 82));

        admin_label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        admin_label.setForeground(new java.awt.Color(255, 255, 255));
        admin_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        admin_label.setText("PRESENT");
        admin_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_labelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_labelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout present_buttonLayout = new javax.swing.GroupLayout(present_button);
        present_button.setLayout(present_buttonLayout);
        present_buttonLayout.setHorizontalGroup(
            present_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        present_buttonLayout.setVerticalGroup(
            present_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        mark_att_side.add(present_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 150, 40));

        cur_date.setBackground(new java.awt.Color(74, 23, 30));
        cur_date.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        c_d.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        c_d.setForeground(new java.awt.Color(226, 193, 68));
        c_d.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c_d.setText("CURRENT DATE");
        c_d.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c_dMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                c_dMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                c_dMouseExited(evt);
            }
        });
        cur_date.add(c_d, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 40));

        mark_att_side.add(cur_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 160, 40));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("DATE (YYYY-MM-DD)");
        mark_att_side.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        getContentPane().add(mark_att_side, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 560, 540));

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

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-804)/2, (screenSize.height-546)/2, 804, 546);
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
        mark_att_side.setVisible(false);
        stu_list_side.setVisible(false);
        stu_atten_side.setVisible(false);

    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        mark_att_side.setVisible(true);
        welcome_side.setVisible(false);
        stu_list_side.setVisible(false);
        stu_atten_side.setVisible(false);

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
            Logger.getLogger(faculty_home.class.getName()).log(Level.SEVERE, null, ex);
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
            mark_attendance();
        } catch (SQLException ex) {
            Logger.getLogger(faculty_home.class.getName()).log(Level.SEVERE, null, ex);
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

    private void mark_att_sideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mark_att_sideMousePressed

   }//GEN-LAST:event_mark_att_sideMousePressed

    private void mark_att_sideMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mark_att_sideMouseDragged

   }//GEN-LAST:event_mark_att_sideMouseDragged

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
mark_att_side.setVisible(false);

    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
func.show_student(jTable1);
stu_list_side.setVisible(true);
welcome_side.setVisible(false);
mark_att_side.setVisible(false);
stu_atten_side.setVisible(false);

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
func.setColor2b(mark_atten_box);
func.setColorw(jLabel14);
func.setColorw(jLabel15);
    }//GEN-LAST:event_jLabel34MouseEntered

    private void jLabel34MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseExited
func.setColor2a(mark_atten_box);
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

    private void jLabel45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel45MouseClicked
System.exit(0);
    }//GEN-LAST:event_jLabel45MouseClicked

    private void jLabel45MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel45MouseEntered
func.setColor1a(close6);
    }//GEN-LAST:event_jLabel45MouseEntered

    private void jLabel45MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel45MouseExited
func.setColor1b(close6);
    }//GEN-LAST:event_jLabel45MouseExited

    private void namefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namefActionPerformed

    private void namefKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namefKeyPressed

    }//GEN-LAST:event_namefKeyPressed

    private void datefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datefActionPerformed

    private void datefKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datefKeyPressed
 
    }//GEN-LAST:event_datefKeyPressed

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        mark_att_side.setVisible(true);
        welcome_side.setVisible(false);
        stu_list_side.setVisible(false);
        stu_atten_side.setVisible(false);
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
mark_att_side.setVisible(false);
stu_atten_side.setVisible(false);
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
mark_att_side.setVisible(false);
    }//GEN-LAST:event_jLabel36MouseClicked

    private void cb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb1ActionPerformed
int index = cb1.getSelectedIndex();
if(index!=0)
{
    String n = (String) cb1.getItemAt(index);
    check_stu(n);
}
else
{
            try {
                clear();
            } catch (SQLException ex) {
                Logger.getLogger(faculty_home.class.getName()).log(Level.SEVERE, null, ex);
            }
}
    }//GEN-LAST:event_cb1ActionPerformed

    private void student_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_labelMouseClicked
        if (!absent_button.isEnabled()) {
            absent_button.setEnabled(true);
            present_button.setEnabled(false);
            
            func.setColor2b(absent_button);
            func.setColorw(student_label);
            func.setColor1a(admin_label);
            func.setColor2a(present_button);
        }
    }//GEN-LAST:event_student_labelMouseClicked

    private void student_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_labelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_student_labelMouseEntered

    private void student_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_labelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_student_labelMouseExited

    private void admin_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_labelMouseClicked
        if (!present_button.isEnabled()) {
            present_button.setEnabled(true);
            absent_button.setEnabled(false);
            
            func.setColor2b(present_button);
            func.setColorw(admin_label);
            func.setColor1a(student_label);
            func.setColor2a(absent_button);
        }
    }//GEN-LAST:event_admin_labelMouseClicked

    private void admin_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_labelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_admin_labelMouseEntered

    private void admin_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_labelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_admin_labelMouseExited

    private void c_dMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c_dMouseClicked
java.util.Date date = new java.util.Date();
String m= new SimpleDateFormat("yyyy-MM-dd").format(date);
datef.setText(m);        // TODO add your handling code here:
    }//GEN-LAST:event_c_dMouseClicked

    private void c_dMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c_dMouseEntered
        func.setColor2b(cur_date);
        func.setColorw(c_d);
    }//GEN-LAST:event_c_dMouseEntered

    private void c_dMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c_dMouseExited
        func.setColor2a(cur_date);
        func.setColor1a(c_d);
    }//GEN-LAST:event_c_dMouseExited

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
            java.util.logging.Logger.getLogger(faculty_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(faculty_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(faculty_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(faculty_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new faculty_home().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(faculty_home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel absent_button;
    private javax.swing.JPanel add_stu_but;
    private javax.swing.JLabel admin_label;
    private javax.swing.JPanel button_side;
    private javax.swing.JLabel c_d;
    private javax.swing.JComboBox cb1;
    private javax.swing.JPanel clear_pane;
    public static javax.swing.JPanel close2;
    public static javax.swing.JPanel close3;
    public static javax.swing.JPanel close4;
    public static javax.swing.JPanel close6;
    private javax.swing.JPanel cur_date;
    private javax.swing.JTextField datef;
    private javax.swing.JPanel del_stu_but;
    private javax.swing.JPanel home_pane;
    private javax.swing.JTextField idf1;
    private javax.swing.JTextField idf2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel login_label;
    private javax.swing.JPanel logout_pane;
    private javax.swing.JPanel mark_att_side;
    private javax.swing.JPanel mark_atten_box;
    private javax.swing.JTextField namef;
    private javax.swing.JPanel present_button;
    private javax.swing.JPanel refresh_but;
    private javax.swing.JPanel register_button1;
    private javax.swing.JLabel register_label;
    private javax.swing.JPanel search_but;
    private javax.swing.JPanel stu_atten_side;
    private javax.swing.JPanel stu_list_side;
    private javax.swing.JLabel student_label;
    private javax.swing.JPanel title;
    private javax.swing.JPanel title1;
    private javax.swing.JPanel title2;
    private javax.swing.JPanel title3;
    private javax.swing.JPanel view_atten_box;
    private javax.swing.JPanel view_atten_but;
    private javax.swing.JPanel view_list_box;
    private javax.swing.JPanel view_list_but;
    private javax.swing.JPanel welcome_side;
    // End of variables declaration//GEN-END:variables
}
