/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jFrames;

import beans.*;
import DBManager.DatabaseManager;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import resources.Resources;

/**
 *
 * @author IB
 */

public class StudentTermFrame extends javax.swing.JFrame {
    public StudentTermFrame() {
        initComponents();
        try{
        Vector classesVec=DatabaseManager.getClasses();
             loadComboBox(classesVec,classCombo);
    }catch(Exception e){e.printStackTrace();}
    }
    
        private void loadComboBox(Vector<Object> obj,JComboBox combo){
        if(obj!=null)
            for(Object o:obj)
             combo.addItem(o);
    }
        
 private void getTerm(int classId,int schemeId){
         try{
         Vector v=DatabaseManager.getTermByClassId(classId,schemeId);
         if(v.isEmpty()){Toolkit.getDefaultToolkit().beep();clearField();return;}
           loadComboBox(v,termCombo);
         }catch(Exception e){
             e.printStackTrace();
         }
     }
 
     private void getStudentClassInCombo(){
          ClassesBean classBean=(ClassesBean)classCombo.getSelectedItem();
          SchemeBean schemBean=(SchemeBean)schemeCombo.getSelectedItem();
           if(classBean==null)return;
           if(schemBean==null)return;
           termCombo.removeAllItems();
           
 //**********GET TERM IN TERM COMBO******
           getTerm(classBean.getClassId(),schemBean.getSchemeId());
           
           stdCombo.removeAllItems();
         try{
           Vector stdClass=DatabaseManager.getStudentClassByClassId(classBean.getClassId(),schemBean.getSchemeId());
           Vector temp=new Vector();
           for(int i=0; i<stdClass.size(); i++){
              StudentClassBean bean=(StudentClassBean)stdClass.elementAt(i);   
              StudentRegistrationBean stdReg=DatabaseManager.getStudentRegistrationByRollNo(bean.getRollNo());
              temp.addElement(stdReg);    
           }
           loadComboBox(temp,stdCombo);
         }catch(Exception e){
             e.printStackTrace();
         }
     }
     
     private void getListData(){
        ClassesBean classBean=(ClassesBean)classCombo.getSelectedItem();
        SchemeBean schemBean=(SchemeBean)schemeCombo.getSelectedItem();
        TermBean termBean=(TermBean)termCombo.getSelectedItem();    
            if(classBean==null)return;
            if(schemBean==null)return;  
            if(termBean==null)return;
         try{
             String a[]={"Empty Record"};
             Vector v=DatabaseManager.getStudentTerms(classBean.getClassId(),schemBean.getSchemeId(),termBean.getTerm());
             Vector temp=new Vector();
             if(v.isEmpty())stdList.setListData(a);
             else{
                for(int i=0; i<v.size(); i++){
                   StudentTermBean bean=(StudentTermBean)v.elementAt(i);
                   StudentRegistrationBean stdBean=DatabaseManager.getStudentRegistrationByRollNo(bean.getRollNo());
                   temp.addElement(stdBean);                              
                }
                stdList.setListData(temp);   
             }
         }catch(Exception e){
             e.printStackTrace();
        }
    }
     
    private void addStudentTerm(int classId,int schemeId, int term,int rollNo){
     try{
        StudentTermBean termBean=new StudentTermBean();
         termBean.setClassId(classId);
         termBean.setTerm(term);
         termBean.setRollNo(rollNo);
         termBean.setStatus(1);
         termBean.setSchemeId(schemeId);
         int rows=DatabaseManager.addStudentTerm(termBean);
         if(rows>0)JOptionPane.showMessageDialog(this,"Data Record Insert Sucessfully..!!");
         getListData();   
         }catch(Exception e){
              Toolkit.getDefaultToolkit().beep(); 
             JOptionPane.showMessageDialog(this,"This student is already insert in this term..");
             e.printStackTrace();
         }
     }
    
    private void clearField(){
        String a[]={"Empty Record"};
        stdList.setListData(a);
        surnameField.setText("");
        fatherNameField.setText("");
        mobileField.setText("");
        sexField.setText("");
    }

    private void deleteStudentFromTerm(){
        SchemeBean schemeBean=(SchemeBean)schemeCombo.getSelectedItem();
        TermBean termBean=(TermBean)termCombo.getSelectedItem();
        StudentRegistrationBean stdBean=(StudentRegistrationBean)stdList.getSelectedValue();
        if(stdBean==null){
             Toolkit.getDefaultToolkit().beep();
             JOptionPane.showMessageDialog(this,"Kindly Select Student From List");
        }
            try{
              int rows=DatabaseManager.deleteStudentTermByRollNo(schemeBean.getSchemeId(),termBean.getTerm(),stdBean.getRollNo());
              if(rows>0)JOptionPane.showMessageDialog(this,"Record Delete Sucessfully...");
                getListData(); 
            }catch(Exception e){
                e.printStackTrace();
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

        jPanel2 = new javax.swing.JPanel();
        fatherNameLabel = new javax.swing.JLabel();
        fatherNameField = new javax.swing.JTextField();
        mobileLabel = new javax.swing.JLabel();
        surnameField = new javax.swing.JTextField();
        sexLabel = new javax.swing.JLabel();
        surnameLabel = new javax.swing.JLabel();
        mobileField = new javax.swing.JTextField();
        sexField = new javax.swing.JTextField();
        stdCombo = new javax.swing.JComboBox<beans.ClassesBean>();
        jLabel37 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stdList = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        classCombo = new javax.swing.JComboBox<beans.ClassesBean>();
        addBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        termCombo = new javax.swing.JComboBox<beans.ClassesBean>();
        deleteBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        schemeCombo = new javax.swing.JComboBox<beans.ClassesBean>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Student Class");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 14))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(685, 700));
        jPanel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                jPanel2ComponentMoved(evt);
            }
        });

        fatherNameLabel.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        fatherNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fatherNameLabel.setText("F/Name:");

        fatherNameField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        fatherNameField.setEnabled(false);

        mobileLabel.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        mobileLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mobileLabel.setText("Mob#:");

        surnameField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        surnameField.setEnabled(false);

        sexLabel.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        sexLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sexLabel.setText("Sex:");

        surnameLabel.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        surnameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        surnameLabel.setText("Surname:");

        mobileField.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        mobileField.setEnabled(false);

        sexField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        sexField.setEnabled(false);

        stdCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        stdCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stdComboActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("Name:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(0, 0, 0)
                        .addComponent(stdCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(surnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(surnameField, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fatherNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(sexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mobileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(fatherNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                                    .addComponent(mobileField))
                                .addComponent(sexField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(141, 141, 141))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stdCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(surnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fatherNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fatherNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mobileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sexField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Students", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 12))); // NOI18N

        stdList.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jScrollPane1.setViewportView(stdList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Class Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 12))); // NOI18N

        jLabel36.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("Class:");

        classCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        classCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classCombo, 0, 308, Short.MAX_VALUE)
                .addGap(143, 143, 143))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        addBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        updateBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        updateBtn.setText("UPDATE");
        updateBtn.setEnabled(false);

        clearBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        clearBtn.setText("CLEAR");
        clearBtn.setEnabled(false);

        exitBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Term Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 12))); // NOI18N

        jLabel39.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("Term:");

        termCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        termCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                termComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(termCombo, 0, 308, Short.MAX_VALUE)
                .addGap(143, 143, 143))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(termCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        deleteBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Scheme", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 12))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Scheme:");

        schemeCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        schemeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schemeComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(schemeCombo, 0, 308, Short.MAX_VALUE)
                .addGap(143, 143, 143))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schemeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void jPanel2ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel2ComponentMoved
    
    }//GEN-LAST:event_jPanel2ComponentMoved

    private void classComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classComboActionPerformed
      ClassesBean classBean=(ClassesBean)classCombo.getSelectedItem();
   if(classBean==null)return; 
   try{
       schemeCombo.removeAllItems();
       Vector v=DatabaseManager.getSchemeByClassId(classBean.getClassId());
         loadComboBox(v,schemeCombo);
   }catch(Exception e){
       e.printStackTrace();
   }
     getStudentClassInCombo();
    }//GEN-LAST:event_classComboActionPerformed

    private void stdComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stdComboActionPerformed
        StudentRegistrationBean stdRegBean=(StudentRegistrationBean)stdCombo.getSelectedItem();
        if(stdRegBean==null)return;
        surnameField.setText(stdRegBean.getSurname());
        sexField.setText(stdRegBean.getGender());
        fatherNameField.setText(stdRegBean.getFatherName());
        mobileField.setText(stdRegBean.getMobileNo());
    }//GEN-LAST:event_stdComboActionPerformed

    private void termComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_termComboActionPerformed
   
     getListData();   
    }//GEN-LAST:event_termComboActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
      dispose();
    }//GEN-LAST:event_exitBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        ClassesBean classesBean=(ClassesBean)classCombo.getSelectedItem();
        SchemeBean schemeBean=(SchemeBean)schemeCombo.getSelectedItem();
        TermBean termBean=(TermBean)termCombo.getSelectedItem();
        StudentRegistrationBean stdBean=(StudentRegistrationBean)stdCombo.getSelectedItem();
        if(classesBean==null)return;if(termBean==null)return;
        if(stdBean==null)return; if(schemeBean==null)return;
            addStudentTerm(classesBean.getClassId(),schemeBean.getSchemeId(),termBean.getTerm(),stdBean.getRollNo());
                      
    }//GEN-LAST:event_addBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
         Toolkit.getDefaultToolkit().beep();
        int opt=JOptionPane.showConfirmDialog(this,"Are Sure Want to Delete?","Delete Message..",JOptionPane.YES_NO_OPTION);
        if(opt==0)
            deleteStudentFromTerm();
      
    }//GEN-LAST:event_deleteBtnActionPerformed

    
    private void schemeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schemeComboActionPerformed
        getStudentClassInCombo();
    }//GEN-LAST:event_schemeComboActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(StudentTermFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentTermFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentTermFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentTermFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentTermFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JComboBox<beans.ClassesBean> classCombo;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JTextField fatherNameField;
    private javax.swing.JLabel fatherNameLabel;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mobileField;
    private javax.swing.JLabel mobileLabel;
    private javax.swing.JComboBox<beans.ClassesBean> schemeCombo;
    private javax.swing.JTextField sexField;
    private javax.swing.JLabel sexLabel;
    private javax.swing.JComboBox<beans.ClassesBean> stdCombo;
    private javax.swing.JList stdList;
    private javax.swing.JTextField surnameField;
    private javax.swing.JLabel surnameLabel;
    private javax.swing.JComboBox<beans.ClassesBean> termCombo;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}

