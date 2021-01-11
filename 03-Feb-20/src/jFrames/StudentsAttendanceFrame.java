/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jFrames;

import DBManager.DatabaseManager;
import beans.*;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author IB
 */
public class StudentsAttendanceFrame extends javax.swing.JFrame {

    /**
     * Creates new form AttendanceFrame
     */
    String dateFormat="dd/MM/yyyy hh:mm";
 public StudentsAttendanceFrame() {
        initComponents();
       // setIconImage(new ImageIcon(new java.io.File("data/check.png").getAbsolutePath()).getImage());
        try{
            Vector cls=DatabaseManager.getAllScheme();
            if(cls!=null){
                for(Object c:cls)
                    classesCombo.addItem(c);
            }
            
            //refreshList();
           // messageArea.setText(new String(new FileInputStream("data/message.txt").readAllBytes()));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        new Thread(new Runnable(){
            public void run(){
                while(true){
                    dateField.setText(new SimpleDateFormat(dateFormat).format(new Date()));
                    try{Thread.sleep(1000*60);}catch(Exception e){}
                }
            }
        }).start();
    }
    
 private void getAttendanceList(){
      SchemeBean schemeBean=(SchemeBean)classesCombo.getSelectedItem();
        if(schemeBean==null)return;
        try{
           Vector attendacen=DatabaseManager.getAttendanceBySchemeId(schemeBean.getSchemeId());
           if(attendacen!=null)
            attendanceList.setListData(attendacen);
            }catch(Exception e){
                e.printStackTrace();
            }
}
    
 private beans.AttendanceBean getFieldsDataOfAttendance(){
        beans.AttendanceBean bean=new beans.AttendanceBean();
        try{
            bean.setStatus(1);
            bean.setClassId(((beans.SchemeBean)classesCombo.getSelectedItem()).getClassId());
            bean.setRemarks(remarksField.getText());            
            bean.setAttendanceDate(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
            bean.setSchemId(((beans.SchemeBean)classesCombo.getSelectedItem()).getSchemeId());
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return bean;
    }//End of getFieldsDatOfAttendace method
          
 private void studentInTable(){
        SchemeBean schemeBean=(SchemeBean)classesCombo.getSelectedItem();
        if(schemeBean==null)return;
        if(classesCombo.getSelectedIndex()>-1)
            try{
            Vector v=DatabaseManager.getStudentClassByClassId(schemeBean.getClassId(), schemeBean.getSchemeId());
             Vector stds=new Vector();
          for(int i=0; i<v.size(); i++){
            StudentClassBean stdClassBean=(StudentClassBean)v.elementAt(i);
            StudentRegistrationBean stdReg=DatabaseManager.getStudentRegistrationByRollNo(stdClassBean.getRollNo());
                stds.addElement(stdReg);
            }
           loadTableData(stds);
           getAttendanceList();            
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
 private void addAttendacen(){
       beans.AttendanceBean attendanceBean=getFieldsDataOfAttendance();
       Toolkit.getDefaultToolkit().beep();
       int option=JOptionPane.showConfirmDialog(null, "Are you sure to submit?","Warning", JOptionPane.OK_CANCEL_OPTION);
       if(option==JOptionPane.OK_OPTION)   { 
           try {
//*******************************INSERT DATA INTO ATTENDACE TABLE***************************               
               int row=DatabaseManager.addAttendance(attendanceBean);
 //******************************INSERT DATA INTO ATTENDANCE DETAILS TABLE*******************              
              int addDetails=0;  
              if(row>0){
                           
                    for(int i=0;i<attendanceTable.getModel().getRowCount();i++){
                         int roll_no=Integer.parseInt((String)attendanceTable.getModel().getValueAt(i, 0));
                         StudentRegistrationBean name=(StudentRegistrationBean)attendanceTable.getModel().getValueAt(i, 1);
                         String fatherName=(String)attendanceTable.getModel().getValueAt(i, 2);
                         boolean status=(boolean)attendanceTable.getModel().getValueAt(i, 3);
                         beans.AttendanceDetailBean attendanceDetails=new beans.AttendanceDetailBean();
                         attendanceDetails.setClassId(((beans.SchemeBean)classesCombo.getSelectedItem()).getClassId());
                         attendanceDetails.setSchemeId(((beans.SchemeBean)classesCombo.getSelectedItem()).getSchemeId());
                         attendanceDetails.setAttendanceDate(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
                         attendanceDetails.setRollNo(roll_no);
                       if(status==true)
                           attendanceDetails.setStatus(1);
                       else
                           attendanceDetails.setStatus(0);                     
                       addDetails=DatabaseManager.addAttendanceDetail(attendanceDetails);   
                    }// end of loop
                }// end of if
            
               if(addDetails>0)
                        JOptionPane.showMessageDialog(this,"Attendace Insert Successfully..");
                 getAttendanceList();
                  } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,"Attendace Already Taken..");
                Logger.getLogger(StudentsAttendanceFrame.class.getName()).log(Level.SEVERE, null, ex);
           }     
       }
}

 private void updateAttendance(){
        beans.AttendanceBean getFieldData=getFieldsDataOfAttendance();
        beans.AttendanceBean attendanceBean=(beans.AttendanceBean)attendanceList.getSelectedValue();
        
        if(attendanceBean==null){       
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(this,"Kindly Select Attendance Date From List..");return;}
            getFieldData.setAttendanceDate(attendanceBean.getAttendanceDate());
        
         try{

                int row=0;
                for(int i=0;i<attendanceTable.getModel().getRowCount();i++){
                int roll_no=Integer.parseInt((String)attendanceTable.getModel().getValueAt(i, 0));
                    StudentRegistrationBean name=(StudentRegistrationBean)attendanceTable.getModel().getValueAt(i, 1);
                    String fatherName=(String)attendanceTable.getModel().getValueAt(i, 2);
                    boolean status=(boolean)attendanceTable.getModel().getValueAt(i, 3);
                      
                    beans.AttendanceDetailBean attendanceDetails=new beans.AttendanceDetailBean();
                    attendanceDetails.setClassId(((beans.SchemeBean)classesCombo.getSelectedItem()).getClassId());
                    attendanceDetails.setSchemeId(((beans.SchemeBean)classesCombo.getSelectedItem()).getSchemeId());
                    attendanceDetails.setAttendanceDate(attendanceBean.getAttendanceDate());
                    attendanceDetails.setRollNo(roll_no);
                    if(status==true)
                        attendanceDetails.setStatus(1);
                    else
                       attendanceDetails.setStatus(0); 
                    
                  row+=DatabaseManager.updateAttendanceDetailByRollNo(attendanceDetails,roll_no);              
                }
                
                if(row>0)JOptionPane.showMessageDialog(this,"Attendace Update Scuessfully..");
                   
                    }catch(Exception e){
                e.printStackTrace();
            }        
}

private void deleteStudent(){
    AttendanceBean attenBean=(AttendanceBean)attendanceList.getSelectedValue();
    if(attenBean==null){Toolkit.getDefaultToolkit().beep(); JOptionPane.showMessageDialog(this, "Kindly Select Date for Delete Record .."); return;}
    try{

       int row=DatabaseManager.deleteAttendanceDetailByAttendanceDate(attenBean.getSchemId(),attenBean.getAttendanceDate());
       int rows=DatabaseManager.deleteAttendanceByAttendanceDate(attenBean.getSchemId(),attenBean.getAttendanceDate());
       if(row>0 && rows>0)
           JOptionPane.showMessageDialog(this,"Delete Sucessfully..."+row);
       getAttendanceList();
    }catch(Exception e){
    e.printStackTrace();
    }
    }

private void loadTableData(Vector v){
        String colNames[]={"Roll-No","Name","FatherName","Presnt/Status"};
        
        if(v!=null){
            Object data[][]=new Object[v.size()][colNames.length];
            
            for(int i=0;i<data.length;i++){
            StudentRegistrationBean std=(StudentRegistrationBean)v.elementAt(i);
          
            int j=0;
                data[i][j++]=""+std.getRollNo();
                data[i][j++]=std;
                data[i][j++]=std.getFatherName();
               
                // checking status of stduenet which are in stuent list by date
                if(std.getPreviousRollNo()==1){
                data[i][j++]=true;
                }
                else{ 
                    data[i][j++]=false;
                
                }
               // data[i][j++]=std.getRemarks().split(":").length==2?std.getRemarks().split(":")[1]:"---";//For Father Name
                //data[i][j++]=std.getAttendanceStatus().equalsIgnoreCase("true")?true:false;
            }
            
            attendanceTable.setModel(new javax.swing.table.DefaultTableModel(data,colNames){
                boolean[] canEdit = new boolean []{false,false,false,true};
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
                
                public Class getColumnClass(int column) {
                    switch (column) {
                        case 0:
                        case 1:
                        case 2:                       
                            return String.class;
                        default:
                            return Boolean.class;
                    }
                }
            });
        }else{
            attendanceTable.setModel(new javax.swing.table.DefaultTableModel(null,colNames));
        }
        
        attendanceTable.setRowHeight(25);
        attendanceTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        attendanceTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        attendanceTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        attendanceTable.getColumnModel().getColumn(3).setPreferredWidth(200);
    }
 
private void clearFieldsData(){
        //classesCombo.setSelectedIndex(0);
        dateField.setText(new SimpleDateFormat(dateFormat).format(new java.util.Date()));
        remarksField.setText("");
        studentInTable();
    }//End of clearFieldsData method
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        attendanceTable = new javax.swing.JTable();
        nameLabel1 = new javax.swing.JLabel();
        submitBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        attendanceList = new javax.swing.JList<Object>();
        jLabel2 = new javax.swing.JLabel();
        classesCombo = new javax.swing.JComboBox<Object>();
        nameLabel2 = new javax.swing.JLabel();
        nameLabel3 = new javax.swing.JLabel();
        remarksField = new javax.swing.JTextField();
        dateField = new javax.swing.JTextField();
        nameLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Student Attendance");

        attendanceTable.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        attendanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(attendanceTable);

        nameLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        nameLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel1.setText("STUDENTS ATTENDANCE");
        nameLabel1.setToolTipText("");

        submitBtn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        submitBtn.setText("SUBMIT");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        updateBtn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        updateBtn.setText("UPDATE");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        deleteBtn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        clearBtn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        exitBtn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        attendanceList.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        attendanceList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                attendanceListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(attendanceList);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        classesCombo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        classesCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        classesCombo.setToolTipText("");
        classesCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classesComboActionPerformed(evt);
            }
        });

        nameLabel2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        nameLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameLabel2.setText("Class:");
        nameLabel2.setToolTipText("");

        nameLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        nameLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameLabel3.setText("Remarks");
        nameLabel3.setToolTipText("");

        remarksField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        dateField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dateField.setEnabled(false);

        nameLabel4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        nameLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameLabel4.setText("Date");
        nameLabel4.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(nameLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(classesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nameLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(remarksField, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(nameLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)))
                        .addGap(0, 278, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel2)
                    .addComponent(classesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel3)
                    .addComponent(remarksField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel4)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                        .addComponent(jScrollPane2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void classesComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classesComboActionPerformed
     studentInTable();
    }//GEN-LAST:event_classesComboActionPerformed
    private void setFieldsData(beans.AttendanceBean bean){
        if(bean!=null){
            dateField.setText(bean.getAttendanceDate());
            remarksField.setText(bean.getRemarks());
        }
    }//End of setFieldsData method
    
    private beans.AttendanceBean getFieldsData(){
        beans.AttendanceBean bean=new beans.AttendanceBean();
        
        bean.setRemarks(remarksField.getText());
        if(attendanceList.getSelectedIndex()>0)
            bean.setAttendanceDate(attendanceList.getSelectedValue().toString());
        
        return bean;
    }//End of getFieldsData method

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        addAttendacen();
    }//GEN-LAST:event_submitBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        updateAttendance();
    }//GEN-LAST:event_updateBtnActionPerformed
    
    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        dispose();
    }//GEN-LAST:event_exitBtnActionPerformed

    private void attendanceListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_attendanceListValueChanged
           beans.AttendanceBean attendanceBean=(AttendanceBean)attendanceList.getSelectedValue();
           if(attendanceBean==null)return;
           try{
           Vector v=DatabaseManager.getAttendanceDetailByAttendanceDate(attendanceBean.getSchemId() ,attendanceBean.getAttendanceDate());
           Vector tempStds=new Vector();
           for(int i=0; i<v.size(); i++){
            AttendanceDetailBean attenBean=(AttendanceDetailBean)v.elementAt(i);
            StudentRegistrationBean stdFromReg=DatabaseManager.getStudentRegistrationByRollNo(attenBean.getRollNo());
            stdFromReg.setPreviousRollNo(attenBean.getStatus());
            tempStds.addElement(stdFromReg);
    
           }
           loadTableData(tempStds);
            }catch(java.sql.SQLException e){
                e.printStackTrace();
            }
    }//GEN-LAST:event_attendanceListValueChanged
    
    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        Toolkit.getDefaultToolkit().beep();
        int opt=JOptionPane.showConfirmDialog(this,"Are You Sure Want to Delete?","Delete Message",JOptionPane.YES_NO_OPTION);
        if(opt==0)
        deleteStudent();
    }//GEN-LAST:event_deleteBtnActionPerformed
    
    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearFieldsData();
        
    }//GEN-LAST:event_clearBtnActionPerformed

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
            java.util.logging.Logger.getLogger(StudentsAttendanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentsAttendanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentsAttendanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentsAttendanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentsAttendanceFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Object> attendanceList;
    private javax.swing.JTable attendanceTable;
    private javax.swing.JComboBox<Object> classesCombo;
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField dateField;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JLabel nameLabel2;
    private javax.swing.JLabel nameLabel3;
    private javax.swing.JLabel nameLabel4;
    private javax.swing.JTextField remarksField;
    private javax.swing.JButton submitBtn;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}

