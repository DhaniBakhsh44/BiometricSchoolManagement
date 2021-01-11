package jFrames;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends javax.swing.JFrame {

    String rootPath;
    
    public MainFrame() {
        initComponents();
        rootPath=new java.io.File("").getAbsolutePath();
        loadFirst();
    }
    
    private void loadFirst(){
        
    }//End of loadFirst method
    
    private ImageIcon getScaledIcon(String path,int width,int height){
        path=new File(path).getAbsolutePath();
        
        ImageIcon icon=new ImageIcon(path);
        Image image=icon.getImage();
        image=image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        return new ImageIcon(image);
    }//End of getScaledIcon method
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        feesManagementBtn = new javax.swing.JButton();
        teacherRegisterBtn = new javax.swing.JButton();
        teacherAttendanceBtn = new javax.swing.JButton();
        studentRegisterBtn = new javax.swing.JButton();
        studentAttendanceBtn = new javax.swing.JButton();
        reportsBtn = new javax.swing.JButton();
        examinationBtn = new javax.swing.JButton();
        teachersSalaryBtn = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        settingsBtn = new javax.swing.JButton();
        classesTermsBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        studentsAttendanceMenu = new javax.swing.JMenuItem();
        studentsRegistrationMenu = new javax.swing.JMenuItem();
        teachresAttendanceMenu = new javax.swing.JMenuItem();
        teachersRegisterMenu = new javax.swing.JMenuItem();
        teachersRegisterMenu1 = new javax.swing.JMenuItem();
        examinationMenu = new javax.swing.JMenuItem();
        feesManagementMenu = new javax.swing.JMenuItem();
        teachersSalaryMenu = new javax.swing.JMenuItem();
        reportsMenu = new javax.swing.JMenuItem();
        settingMenu = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biometric School Management System");
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Developers Info ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Junaid Ahmed 2K17/SWEE/29");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 33, 176, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Nadeem Chandio 2K17/SWEE/60");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 176, -1));

        feesManagementBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        feesManagementBtn.setText("Fees Management");

        teacherRegisterBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        teacherRegisterBtn.setText("Teacher Register");
        teacherRegisterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherRegisterBtnActionPerformed(evt);
            }
        });

        teacherAttendanceBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        teacherAttendanceBtn.setText("Teacher Attendance");
        teacherAttendanceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherAttendanceBtnActionPerformed(evt);
            }
        });

        studentRegisterBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        studentRegisterBtn.setText("Student Register");
        studentRegisterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentRegisterBtnActionPerformed(evt);
            }
        });

        studentAttendanceBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        studentAttendanceBtn.setText("Student Attendance");
        studentAttendanceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentAttendanceBtnActionPerformed(evt);
            }
        });

        reportsBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        reportsBtn.setText("Reports");

        examinationBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        examinationBtn.setText("Examination");
        examinationBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examinationBtnActionPerformed(evt);
            }
        });

        teachersSalaryBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        teachersSalaryBtn.setText("Teachers Salary");

        jButton7.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jButton7.setText("Exit");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        settingsBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        settingsBtn.setText("Settings");

        classesTermsBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        classesTermsBtn.setText("Classes Management");
        classesTermsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classesTermsBtnActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        studentsAttendanceMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        studentsAttendanceMenu.setText("Students Attendance");
        studentsAttendanceMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentsAttendanceMenuActionPerformed(evt);
            }
        });
        jMenu1.add(studentsAttendanceMenu);

        studentsRegistrationMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        studentsRegistrationMenu.setText("Students Register");
        studentsRegistrationMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentsRegistrationMenuActionPerformed(evt);
            }
        });
        jMenu1.add(studentsRegistrationMenu);

        teachresAttendanceMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        teachresAttendanceMenu.setText("Teachers Attendance");
        teachresAttendanceMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teachresAttendanceMenuActionPerformed(evt);
            }
        });
        jMenu1.add(teachresAttendanceMenu);

        teachersRegisterMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        teachersRegisterMenu.setText("Teachers Register");
        teachersRegisterMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teachersRegisterMenuActionPerformed(evt);
            }
        });
        jMenu1.add(teachersRegisterMenu);

        teachersRegisterMenu1.setText("Classes & Terms");
        teachersRegisterMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teachersRegisterMenu1ActionPerformed(evt);
            }
        });
        jMenu1.add(teachersRegisterMenu1);

        examinationMenu.setText("Examination");
        examinationMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examinationMenuActionPerformed(evt);
            }
        });
        jMenu1.add(examinationMenu);

        feesManagementMenu.setText("Fees Management");
        feesManagementMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feesManagementMenuActionPerformed(evt);
            }
        });
        jMenu1.add(feesManagementMenu);

        teachersSalaryMenu.setText("Teachers Salary");
        teachersSalaryMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teachersSalaryMenuActionPerformed(evt);
            }
        });
        jMenu1.add(teachersSalaryMenu);

        reportsMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        reportsMenu.setText("Reports");
        reportsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsMenuActionPerformed(evt);
            }
        });
        jMenu1.add(reportsMenu);

        settingMenu.setText("Settings");
        settingMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingMenuActionPerformed(evt);
            }
        });
        jMenu1.add(settingMenu);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem11.setText("Exit");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuBar1.add(jMenu1);

        jMenu8.setText("Help");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem2.setText("About Us");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem2);

        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(feesManagementBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(teacherRegisterBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(teacherAttendanceBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(studentAttendanceBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(teachersSalaryBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(classesTermsBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentRegisterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(examinationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reportsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(settingsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teacherAttendanceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentAttendanceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentRegisterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classesTermsBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teacherRegisterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(examinationBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(feesManagementBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teachersSalaryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(settingsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        
    }//GEN-LAST:event_formComponentResized

    private void studentsAttendanceMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentsAttendanceMenuActionPerformed
        
    }//GEN-LAST:event_studentsAttendanceMenuActionPerformed

    private void studentsRegistrationMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentsRegistrationMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentsRegistrationMenuActionPerformed

    private void teachresAttendanceMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachresAttendanceMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teachresAttendanceMenuActionPerformed

    private void teachersRegisterMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachersRegisterMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teachersRegisterMenuActionPerformed

    private void examinationMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examinationMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_examinationMenuActionPerformed

    private void feesManagementMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feesManagementMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_feesManagementMenuActionPerformed

    private void teachersSalaryMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachersSalaryMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teachersSalaryMenuActionPerformed

    private void reportsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reportsMenuActionPerformed

    private void settingMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingMenuActionPerformed
        JOptionPane.showMessageDialog(null,"This feature is added soon!");
    }//GEN-LAST:event_settingMenuActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JOptionPane.showMessageDialog(null,"Designed and Developed By\nJunaid Ahmed 2k17/SWEE/29"
                + "\nNadeem Chandio 2k17/SWEE/60"
                + "\nContact: 03000090822");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void teachersRegisterMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachersRegisterMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teachersRegisterMenu1ActionPerformed

    private void classesTermsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classesTermsBtnActionPerformed
        new ClassesManagementFrame().setVisible(true);
    }//GEN-LAST:event_classesTermsBtnActionPerformed

    private void studentRegisterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentRegisterBtnActionPerformed
        new StudentRegisterFrame().setVisible(true);
    }//GEN-LAST:event_studentRegisterBtnActionPerformed

    private void teacherRegisterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherRegisterBtnActionPerformed
        new TeacherRegisterFrame().setVisible(true);
    }//GEN-LAST:event_teacherRegisterBtnActionPerformed

    private void examinationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examinationBtnActionPerformed
        new ExamsManagementFrame().setVisible(true);
    }//GEN-LAST:event_examinationBtnActionPerformed

    private void teacherAttendanceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherAttendanceBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherAttendanceBtnActionPerformed

    private void studentAttendanceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentAttendanceBtnActionPerformed
   new StudentsAttendanceFrame().setVisible(true);
    }//GEN-LAST:event_studentAttendanceBtnActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton classesTermsBtn;
    private javax.swing.JButton examinationBtn;
    private javax.swing.JMenuItem examinationMenu;
    private javax.swing.JButton feesManagementBtn;
    private javax.swing.JMenuItem feesManagementMenu;
    private javax.swing.JButton jButton7;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton reportsBtn;
    private javax.swing.JMenuItem reportsMenu;
    private javax.swing.JMenuItem settingMenu;
    private javax.swing.JButton settingsBtn;
    private javax.swing.JButton studentAttendanceBtn;
    private javax.swing.JButton studentRegisterBtn;
    private javax.swing.JMenuItem studentsAttendanceMenu;
    private javax.swing.JMenuItem studentsRegistrationMenu;
    private javax.swing.JButton teacherAttendanceBtn;
    private javax.swing.JButton teacherRegisterBtn;
    private javax.swing.JMenuItem teachersRegisterMenu;
    private javax.swing.JMenuItem teachersRegisterMenu1;
    private javax.swing.JButton teachersSalaryBtn;
    private javax.swing.JMenuItem teachersSalaryMenu;
    private javax.swing.JMenuItem teachresAttendanceMenu;
    // End of variables declaration//GEN-END:variables
}
