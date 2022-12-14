
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bibek
 */
public class login extends javax.swing.JFrame {
    int xMouse,yMouse;
    String usrc,passc;
    /**
     * Creates new form login
     */
    public login() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        
        func.show_pass(passf,jcb1);
    }
    
    private void login_execute()
    {
        String usrc = "", passc = "";
        if(usname.getText().trim().length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter Username");
        else if(passf.getText().length()==0)
            JOptionPane.showMessageDialog(this, "Please Enter Password");
        else if(admin_button.isEnabled())
        {
            try
            {
                Class.forName("java.sql.DriverManager");
                Connection con = DriverManager.getConnection(func.url(),func.user(),func.pass());
                Statement stm = con.createStatement();
                String q = "select * from admin_login where admin_id='"+usname.getText().trim()+"' and password='"+passf.getText()+"';";
                ResultSet rs = stm.executeQuery(q);
                if(rs.next())
                {
                    usrc=rs.getString("admin_id");
                    passc=rs.getString("password");
                }

                if(usname.getText().equals(usrc) && passf.getText().equals(passc)) 
                {
                    JOptionPane.showMessageDialog(this, "LOGIN SUCCESSFULL");
                    admin_home ah = new admin_home();
                    ah.setVisible(true);
                    this.dispose();
                }
                else 
                    JOptionPane.showMessageDialog(this, "Invalid Username/Password");
                    
             }
             catch(Exception e)
             {
                JOptionPane.showMessageDialog(this, e.getMessage());
             }
        }
        else
        { 
            try
            {
                Class.forName("java.sql.DriverManager");
                Connection con = DriverManager.getConnection(func.url(),func.user(),func.pass());
                Statement stm = con.createStatement();
                String q = "select * from faculty_login where fac_id='"+usname.getText()+"' and password='"+passf.getText()+"';";
                ResultSet rs = stm.executeQuery(q);
                if(rs.next())
                {
                    usrc=rs.getString("fac_id");
                    passc=rs.getString("password");
                }

                if(usname.getText().equals(usrc) && passf.getText().equals(passc)) 
                {
                    JOptionPane.showMessageDialog(this, "LOGIN SUCCESSFULL");
                    new faculty_home().setVisible(true);
                    this.dispose();
                }
                else 
                    JOptionPane.showMessageDialog(this, "Invalid Username/Password");
                    
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

        label_side = new javax.swing.JPanel();
        close_button = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        about_pane = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        login_side = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        usname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        passf = new javax.swing.JPasswordField();
        jcb1 = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        fac_button = new javax.swing.JPanel();
        student_label = new javax.swing.JLabel();
        admin_button = new javax.swing.JPanel();
        admin_label = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        login_pane = new javax.swing.JPanel();
        login_label = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        label_side.setBackground(new java.awt.Color(74, 23, 30));
        label_side.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_sideMousePressed(evt);
            }
        });
        label_side.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_sideMouseDragged(evt);
            }
        });
        label_side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        close_button.setBackground(new java.awt.Color(74, 23, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("X");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel14MouseExited(evt);
            }
        });

        javax.swing.GroupLayout close_buttonLayout = new javax.swing.GroupLayout(close_button);
        close_button.setLayout(close_buttonLayout);
        close_buttonLayout.setHorizontalGroup(
            close_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        close_buttonLayout.setVerticalGroup(
            close_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, close_buttonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        label_side.add(close_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 40, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(226, 193, 68));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SYSTEM");
        label_side.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 280, 50));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(226, 193, 68));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("MANAGEMENT");
        label_side.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 280, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(226, 193, 68));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ATTENDANCE");
        label_side.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 280, 50));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(226, 193, 68));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("By Rohan Singh");
        label_side.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 280, 50));

        about_pane.setBackground(new java.awt.Color(226, 193, 68));
        about_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(74, 23, 30));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ABOUT");
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
        about_pane.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 50));

        label_side.add(about_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 230, 50));

        login_side.setBackground(new java.awt.Color(226, 193, 68));
        login_side.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                login_sideMousePressed(evt);
            }
        });
        login_side.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                login_sideMouseDragged(evt);
            }
        });
        login_side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("ID");
        login_side.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        usname.setBackground(new java.awt.Color(226, 193, 68));
        usname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usname.setForeground(new java.awt.Color(255, 255, 255));
        usname.setBorder(null);
        usname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usnameActionPerformed(evt);
            }
        });
        usname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usnameKeyPressed(evt);
            }
        });
        login_side.add(usname, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 270, 28));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("PASSWORD");
        login_side.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        passf.setBackground(new java.awt.Color(226, 193, 68));
        passf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        passf.setForeground(new java.awt.Color(255, 255, 255));
        passf.setBorder(null);
        passf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passfActionPerformed(evt);
            }
        });
        passf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passfKeyPressed(evt);
            }
        });
        login_side.add(passf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 150, 29));

        jcb1.setBackground(new java.awt.Color(226, 193, 68));
        jcb1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jcb1.setText("Show Password");
        jcb1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jcb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb1ActionPerformed(evt);
            }
        });
        login_side.add(jcb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, -1, 29));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        login_side.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 270, 10));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        login_side.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 270, 10));

        fac_button.setBackground(new java.awt.Color(74, 23, 30));
        fac_button.setEnabled(false);

        student_label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        student_label.setForeground(new java.awt.Color(226, 193, 68));
        student_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        student_label.setText("FACULTY");
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

        javax.swing.GroupLayout fac_buttonLayout = new javax.swing.GroupLayout(fac_button);
        fac_button.setLayout(fac_buttonLayout);
        fac_buttonLayout.setHorizontalGroup(
            fac_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(student_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        fac_buttonLayout.setVerticalGroup(
            fac_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(student_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        login_side.add(fac_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 140, 40));

        admin_button.setBackground(new java.awt.Color(196, 90, 82));

        admin_label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        admin_label.setForeground(new java.awt.Color(255, 255, 255));
        admin_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        admin_label.setText("ADMIN");
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

        javax.swing.GroupLayout admin_buttonLayout = new javax.swing.GroupLayout(admin_button);
        admin_button.setLayout(admin_buttonLayout);
        admin_buttonLayout.setHorizontalGroup(
            admin_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_label, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        admin_buttonLayout.setVerticalGroup(
            admin_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        login_side.add(admin_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 130, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(74, 23, 30));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("WELCOME BACK");
        login_side.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 340, 30));

        login_pane.setBackground(new java.awt.Color(74, 23, 30));

        login_label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        login_label.setForeground(new java.awt.Color(226, 193, 68));
        login_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        login_label.setText("LOGIN");
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

        javax.swing.GroupLayout login_paneLayout = new javax.swing.GroupLayout(login_pane);
        login_pane.setLayout(login_paneLayout);
        login_paneLayout.setHorizontalGroup(
            login_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login_label, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );
        login_paneLayout.setVerticalGroup(
            login_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        login_side.add(login_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 270, 50));
        login_pane.getAccessibleContext().setAccessibleName("");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(74, 23, 30));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("SIGN IN TO CONTINUE");
        login_side.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 340, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(login_side, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(label_side, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login_side, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(label_side, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-660)/2, (screenSize.height-470)/2, 660, 470);
    }// </editor-fold>//GEN-END:initComponents

    private void usnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usnameActionPerformed

    private void jcb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb1ActionPerformed
func.show_pass(passf,jcb1);
    }//GEN-LAST:event_jcb1ActionPerformed

    private void passfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passfActionPerformed

    private void usnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usnameKeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER)
{
    login_execute();
}
    }//GEN-LAST:event_usnameKeyPressed

    private void passfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passfKeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER)
{
    login_execute();
}
    }//GEN-LAST:event_passfKeyPressed

    private void student_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_labelMouseClicked
if(!fac_button.isEnabled()){
    fac_button.setEnabled(true);
    admin_button.setEnabled(false);
    
    func.setColor2b(fac_button);
    func.setColorw(student_label);
    func.setColor1a(admin_label);
    func.setColor2a(admin_button);
}
    }//GEN-LAST:event_student_labelMouseClicked

    private void student_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_labelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_student_labelMouseEntered

    private void student_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_labelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_student_labelMouseExited

    private void admin_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_labelMouseClicked
if(!admin_button.isEnabled()){
    admin_button.setEnabled(true);
    fac_button.setEnabled(false);
    
    func.setColor2b(admin_button);
    func.setColorw(admin_label);
    func.setColor1a(student_label);
    func.setColor2a(fac_button);
}
    }//GEN-LAST:event_admin_labelMouseClicked

    private void admin_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_labelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_admin_labelMouseEntered

    private void admin_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_labelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_admin_labelMouseExited

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
System.exit(0);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
        func.setColor2b(close_button);
    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
        func.setColor2a(close_button);
    }//GEN-LAST:event_jLabel14MouseExited

    private void label_sideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_sideMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_label_sideMousePressed

    private void label_sideMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_sideMouseDragged
        int x = evt.getXOnScreen(), y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse); 
    }//GEN-LAST:event_label_sideMouseDragged

    private void login_sideMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_sideMouseDragged

    }//GEN-LAST:event_login_sideMouseDragged

    private void login_sideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_sideMousePressed
 
    }//GEN-LAST:event_login_sideMousePressed

    private void login_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_labelMouseClicked
    login_execute();
    }//GEN-LAST:event_login_labelMouseClicked

    private void login_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_labelMouseEntered
 func.setColor2b(login_pane);
 func.setColorw(login_label);
 
    }//GEN-LAST:event_login_labelMouseEntered

    private void login_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_labelMouseExited
 func.setColor2a(login_pane);
 func.setColor1a(login_label);
  
    }//GEN-LAST:event_login_labelMouseExited

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
func.setColor2b(about_pane);
func.setColorw(jLabel6);
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
func.setColor1a(about_pane);
func.setColor2a(jLabel6);
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
new about().setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel about_pane;
    private javax.swing.JPanel admin_button;
    private javax.swing.JLabel admin_label;
    private javax.swing.JPanel close_button;
    private javax.swing.JPanel fac_button;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JCheckBox jcb1;
    private javax.swing.JPanel label_side;
    private javax.swing.JLabel login_label;
    private javax.swing.JPanel login_pane;
    private javax.swing.JPanel login_side;
    private javax.swing.JPasswordField passf;
    private javax.swing.JLabel student_label;
    private javax.swing.JTextField usname;
    // End of variables declaration//GEN-END:variables
}
