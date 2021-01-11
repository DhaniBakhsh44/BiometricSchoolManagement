/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jFrames;

import beans.*;
import beans.TermBean;
import DBManager.DatabaseManager;
import beans.SchemeBean;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import resources.*;
import java.util.*;

/**
 *
 * @author dhani
 */
public class MarksheetFrame extends javax.swing.JFrame {

    DefaultTableModel model;
     DefaultTableModel model2;
    
    public MarksheetFrame() {
         initComponents();
         tableConfig();
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
    private void getTerm(){
        ClassesBean beanClass=(ClassesBean)classCombo.getSelectedItem(); 
        SchemeBean beanSchem=(SchemeBean)schemeCombo.getSelectedItem();
            if(beanClass==null)return; if(beanSchem==null)return;
           try{
               termCombo.removeAllItems();
               Vector v=DatabaseManager.getTermByClassId(beanClass.getClassId(),beanSchem.getSchemeId());
               loadComboBox(v,termCombo);
           }catch(Exception e){
            e.printStackTrace();
           }
         }
private void getStudentInTable(){
       ClassesBean beanClass=(ClassesBean)classCombo.getSelectedItem(); if(beanClass==null)return;
       SchemeBean beanSchem=(SchemeBean)schemeCombo.getSelectedItem();if(beanSchem==null)return;
       TermBean termBean=(TermBean)termCombo.getSelectedItem();if(termBean==null)return;
       ExamListBean examListBean=(ExamListBean)examListCombo.getSelectedItem();if(examListBean==null)return;     
        try {
            Vector v=DatabaseManager.getExamListDetails(beanClass.getClassId(), beanSchem.getSchemeId(), termBean.getTerm(),examListBean.getExamList());
            model.getDataVector().removeAllElements();
            for(int i=0; i<v.size(); i++ ){
              ExamListDetailBean bean=(ExamListDetailBean)v.elementAt(i);
              StudentRegistrationBean stdRegBean=DatabaseManager.getStudentRegistrationByRollNo(bean.getRollNo());
              model.addRow(new Object[]{stdRegBean.getStudentName(),stdRegBean.getFatherName(),stdRegBean.getRollNo()});
            }
        } catch (Exception ex) {
            Logger.getLogger(MarksheetFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
   
  private void clearField(){
       nameField.setText("");
        fNameField.setText("");
        classField.setText("");  
        rollNoField.setText("");
  }
  
  private void getSubjectInTable(){
      try{
          ClassesBean beanClass=(ClassesBean)classCombo.getSelectedItem(); if(beanClass==null)return;
            SchemeBean beanSchem=(SchemeBean)schemeCombo.getSelectedItem();if(beanSchem==null)return;
            
          Vector v=DatabaseManager.getCoursesByClassId(beanClass.getClassId(),beanSchem.getSchemeId());
           model2.getDataVector().removeAllElements();
            for(int i=0; i<v.size(); i++ ){
              CourseBean courseBean=(CourseBean)v.elementAt(i);
              model2.addRow(new Object[]{courseBean.getCourseTitle(),courseBean.getMinMarks(),courseBean.getMaxMarks(),0,courseBean.getCourseNo()});
            }          
      }catch(Exception e){
          e.printStackTrace();
      }   
  }
private void tableConfig(){
        this.model = (DefaultTableModel)stdTable.getModel();
        this.model2 = (DefaultTableModel)subTable.getModel();
        stdTable.setFont(new Font("Times New Roman",Font.PLAIN, 12));
        stdTable.getTableHeader().setFont(new Font("Courier New", Font.BOLD, 14));
        stdTable.setRowHeight(20);
        subTable.getTableHeader().setFont(new Font("Courier New", Font.BOLD, 14));
        subTable.setFont(new Font("Times New Roman",Font.PLAIN, 12));
        subTable.setRowHeight(20);
        subTable.removeColumn(subTable.getColumnModel().getColumn(4));

}
  private void addMarksheet(){
      ClassesBean beanClass=(ClassesBean)classCombo.getSelectedItem(); 
        if(beanClass==null)return;
        SchemeBean beanSchem=(SchemeBean)schemeCombo.getSelectedItem();
        if(beanSchem==null)return;
        TermBean termBean=(TermBean)termCombo.getSelectedItem();
        if(termBean==null)return;
        ExamListBean examListBean=(ExamListBean)examListCombo.getSelectedItem();
        if(examListBean==null)return;
   
        int rollNo=Integer.parseInt(rollNoField.getText().trim());
        int row=subTable.getModel().getRowCount();
        int cellRow=0,totalObtain=0,totalMarks=0;
    
        float percentage=0;
        for(int i=0; i<row; i++){    
        String subjects =(String)subTable.getModel().getValueAt(i,0).toString();
        int minMarks =(int) subTable.getModel().getValueAt(i,1);
        int maxMarks =(int)subTable.getModel().getValueAt(i,2);
        int obtain=Integer.parseInt(subTable.getModel().getValueAt(i,3).toString());
        totalObtain+=obtain;
        totalMarks+=maxMarks;
   }// end of for loop
    percentage=(totalObtain*100)/totalMarks;
    try{
         MarksheetBean markBean=new MarksheetBean();
         markBean.setSchemeId(beanSchem.getSchemeId());
         markBean.setClassId(beanClass.getClassId());
         markBean.setTerm(termBean.getTerm());
         markBean.setExamList(examListBean.getExamList());
         markBean.setRollNo(rollNo);
         markBean.setStatus(1);
         markBean.setTotalMarks(totalMarks);
         markBean.setObtainMarks(totalObtain); 
         cellRow=DatabaseManager.addMarksheet(markBean); // create marksheet;
       
          int marksheetId=DatabaseManager.getRecentMarksheetId(); // geting Recent Marksheet id
          SubjectResultBean resultBean=null; 
           if(marksheetId>0){
            int addSubject=0;   
            for(int j=0; j<row; j++){    
              // geting data from jtable      
            String subjects =(String)subTable.getModel().getValueAt(j,0).toString();
            int minMarks =(int) subTable.getModel().getValueAt(j,1);
            int maxMarks =(int)subTable.getModel().getValueAt(j,2);
            int obtain=Integer.parseInt(subTable.getModel().getValueAt(j,3).toString());
            int courseNo=(int)subTable.getModel().getValueAt(j,4);
           
           // set Subject for add to subject_Result Table
           resultBean=new SubjectResultBean(); 
           resultBean.setMarksheetId(marksheetId);
           resultBean.setScheme_id(beanSchem.getSchemeId());
           resultBean.setClassId(beanClass.getClassId());
           resultBean.setTerm(termBean.getTerm());
           resultBean.setExamList(examListBean.getExamList());
           resultBean.setRollNo(rollNo);
           resultBean.setMinMarks(minMarks);
           resultBean.setMaxMarks(maxMarks);
           resultBean.setObtainedMarks(obtain);
           resultBean.setCourseNo(courseNo);
           resultBean.setStatus(1);
           addSubject=DatabaseManager.addSubjectResult(resultBean);// Adding Marksheet Subject's
   }// end of for loop
            if(addSubject>0)
            JOptionPane.showMessageDialog(this,"Marksheet Create Sucessfully...");
             getMarksheetInList(); 
           }      
     }catch(Exception e){
        e.printStackTrace();
     }
  }
  
  private void getMarksheetInList(){
        try {
             String emp[]={"Empty Record"};
                ClassesBean beanClass=(ClassesBean)classCombo.getSelectedItem(); 
               if(beanClass==null)return;
                SchemeBean beanSchem=(SchemeBean)schemeCombo.getSelectedItem();
                if(beanSchem==null){marksheetList.setListData(emp);return;}
                TermBean termBean=(TermBean)termCombo.getSelectedItem();
                if(termBean==null){marksheetList.setListData(emp);return;}
                ExamListBean examListBean=(ExamListBean)examListCombo.getSelectedItem();
                if(examListBean==null)return;
            
              Vector v=DatabaseManager.getMarksheets(beanClass.getClassId(),beanSchem.getSchemeId(),termBean.getTerm(),examListBean.getExamList());
                Vector temp=new Vector();
                for(int i=0; i<v.size(); i++){
                 MarksheetBean marksheetBean=(MarksheetBean)v.elementAt(i);
                 StudentRegistrationBean stdBeanReg=DatabaseManager.getStudentRegistrationByRollNo(marksheetBean.getRollNo());
              
          //set Markseet Id for Acess the at the printing Time
                stdBeanReg.setEntryTestMarks(marksheetBean.getMarsheetId());
                temp.addElement(stdBeanReg);
            }
            
                if(temp.isEmpty()){
                marksheetList.setListData(emp);
            }else
                 marksheetList.setListData(temp);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarksheetFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
      
  }
  
  private void deleteMarksheet(){
StudentRegistrationBean stdBean=(StudentRegistrationBean)marksheetList.getSelectedValue();
 if(stdBean==null){Toolkit.getDefaultToolkit().beep();JOptionPane.showMessageDialog(this, "Kindly Select Studetn for Delete Record ..");return;}
   try{
    int row=DatabaseManager.deleteSubjectResultByMarksheetId(stdBean.getEntryTestMarks());
    int rows=DatabaseManager.deleteMarksheetByMarsheetId(stdBean.getEntryTestMarks());
    if(row>0 && rows>=0)JOptionPane.showMessageDialog(this,"Marksheet Delete Sucessfully...");
     getMarksheetInList();
       }catch(Exception e){
          e.printStackTrace();
      }        
    }
          
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        classCombo = new javax.swing.JComboBox<beans.SchemeBean>();
        examListCombo = new javax.swing.JComboBox<beans.SchemeBean>();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stdTable = new javax.swing.JTable();
        jLabel50 = new javax.swing.JLabel();
        schemeCombo = new javax.swing.JComboBox<beans.SchemeBean>();
        jLabel47 = new javax.swing.JLabel();
        termCombo = new javax.swing.JComboBox<beans.SchemeBean>();
        jLabel2 = new javax.swing.JLabel();
        monthTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        yearField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        subTable = new javax.swing.JTable();
        nameField = new javax.swing.JTextField();
        fNameField = new javax.swing.JTextField();
        classField = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        rollNoField = new javax.swing.JTextField();
        addBtn4 = new javax.swing.JButton();
        deleteBtn3 = new javax.swing.JButton();
        clearBtn3 = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        marksheetList = new javax.swing.JList();
        selectCheckBox = new javax.swing.JCheckBox();
        printBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student's Information ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Class:");

        classCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        classCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classComboActionPerformed(evt);
            }
        });

        examListCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        examListCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examListComboActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel48.setText("Scheme:");

        stdTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Students", "Father's Name", "Roll No"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stdTable.getTableHeader().setReorderingAllowed(false);
        stdTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stdTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(stdTable);

        jLabel50.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel50.setText("Exam List:");

        schemeCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        schemeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schemeComboActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel47.setText("Term:");

        termCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        termCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                termComboActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel2.setText("Month:");

        monthTextField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        monthTextField.setEnabled(false);
        monthTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel3.setText("Year:");

        yearField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        yearField.setEnabled(false);
        yearField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(examListCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(monthTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(schemeCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(termCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(classCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(schemeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(termCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(examListCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Subject", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18))); // NOI18N

        jLabel40.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Name:");

        jLabel42.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("F/Name:");

        jLabel43.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("Roll No:");

        subTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subjects", "Min Marks", "Mix Marks", "Obtain Marks", "Course No"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        subTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(subTable);

        nameField.setEditable(false);
        nameField.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        nameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        fNameField.setEditable(false);
        fNameField.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        fNameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        classField.setEditable(false);
        classField.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        classField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel44.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("class");

        rollNoField.setEditable(false);
        rollNoField.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        rollNoField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel44))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(classField, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel43)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rollNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                            .addComponent(nameField))
                        .addGap(44, 44, 44))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(fNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(rollNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );

        addBtn4.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        addBtn4.setText("ADD");
        addBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtn4ActionPerformed(evt);
            }
        });

        deleteBtn3.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        deleteBtn3.setText("DELETE");
        deleteBtn3.setEnabled(true);
        deleteBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtn3ActionPerformed(evt);
            }
        });

        clearBtn3.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        clearBtn3.setText("CLEAR");
        clearBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtn3ActionPerformed(evt);
            }
        });

        exitBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Javanese Text", 1, 24)); // NOI18N
        jLabel1.setText("Marksheet's:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Students Marksheets", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18))); // NOI18N

        marksheetList.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        marksheetList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                marksheetListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(marksheetList);

        selectCheckBox.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        selectCheckBox.setText("Select All");
        selectCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectCheckBox)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(selectCheckBox))
        );

        printBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        printBtn.setText("Print Marksheet's");
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(addBtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clearBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(printBtn)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(440, 440, 440)
                        .addComponent(jLabel1)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clearBtn3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(addBtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(deleteBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void classComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classComboActionPerformed
        ClassesBean classBean=(ClassesBean)classCombo.getSelectedItem();
        if(classBean==null)return;
         try{
             schemeCombo.removeAllItems();
             Vector v=DatabaseManager.getSchemeByClassId(classBean.getClassId());
                loadComboBox(v,schemeCombo);
            
       }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_classComboActionPerformed

    private void termComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_termComboActionPerformed
        ClassesBean classBean=(ClassesBean)classCombo.getSelectedItem();
        SchemeBean schemeBean=(SchemeBean)schemeCombo.getSelectedItem();
        TermBean termBean=(TermBean)termCombo.getSelectedItem();
        if(classBean==null)return; if(schemeBean==null)return;
        if(termBean==null){model.setRowCount(0); return;}
         try{
            examListCombo.removeAllItems();
            Vector v=DatabaseManager.getExamListByClassId(classBean.getClassId(),schemeBean.getSchemeId(),termBean.getTerm());
            loadComboBox(v,examListCombo);
            getMarksheetInList();
            }catch(Exception e){ e.printStackTrace();}
    }//GEN-LAST:event_termComboActionPerformed

    private void examListComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examListComboActionPerformed
    ExamListBean examListBean=(ExamListBean)examListCombo.getSelectedItem();
       if(examListBean==null)return;
       
       yearField.setText(""+examListBean.getSlYear());
       String month_Name[]={"Jan","Feb", "Mar"," Apr" ,"May","Jun" ,"Jul" ,"Aug" ,"Sep", "Oct", "Nov","Dec"};
        for(int i=0; i<month_Name.length; i++){
          if(examListBean.getSlMonth()==i)
             monthTextField.setText(month_Name[i-1]);
        }
       getStudentInTable();
       getSubjectInTable();
       clearField();
       getMarksheetInList(); 
    }//GEN-LAST:event_examListComboActionPerformed

    private void schemeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schemeComboActionPerformed
       getTerm();
       getSubjectInTable();
       getMarksheetInList();
    }//GEN-LAST:event_schemeComboActionPerformed

    private void addBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtn4ActionPerformed
if(classField.getText().equals("")){
    Toolkit.getDefaultToolkit().beep();
    JOptionPane.showMessageDialog(this,"Kindly Select Student..");
    return;}
    addMarksheet();
    }//GEN-LAST:event_addBtn4ActionPerformed

    private void clearBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtn3ActionPerformed
    clearField();            
    }//GEN-LAST:event_clearBtn3ActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        dispose();
    }//GEN-LAST:event_exitBtnActionPerformed

    private void stdTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stdTableMouseClicked
     SchemeBean schemeBean=(SchemeBean)schemeCombo.getSelectedItem();
        int row = stdTable.rowAtPoint(evt.getPoint());
        String stdName=(String) stdTable.getValueAt(row, 0);
        String fName=(String) stdTable.getValueAt(row, 1);
        int roll_no=(int) stdTable.getValueAt(row, 2);
        nameField.setText(" "+stdName);
        fNameField.setText(" "+fName);
        rollNoField.setText(" "+roll_no);
        classField.setText(" "+schemeBean.getSchemeName());
    }//GEN-LAST:event_stdTableMouseClicked

    private void monthTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthTextFieldActionPerformed

    private void yearFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearFieldActionPerformed

    private void deleteBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtn3ActionPerformed
    Toolkit.getDefaultToolkit().beep();
    int opt=JOptionPane.showConfirmDialog(this,"Are You Sure Want to Delete..?","Delete Message",JOptionPane.YES_NO_OPTION);
     if(opt==0)
        deleteMarksheet();
    }//GEN-LAST:event_deleteBtn3ActionPerformed


    
    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed
  new resources.MarksheetPrintJob(marksheetList.getSelectedValuesList());
    }//GEN-LAST:event_printBtnActionPerformed

    
    private void marksheetListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_marksheetListValueChanged
  StudentRegistrationBean stdRegBean=(StudentRegistrationBean)marksheetList.getSelectedValue();
   SchemeBean schemeBean=(SchemeBean)schemeCombo.getSelectedItem();
    if(schemeBean==null)return;
    if(stdRegBean==null)return;
    nameField.setText(" "+stdRegBean.getStudentName());
    fNameField.setText(" "+stdRegBean.getFatherName());
    classField.setText(" "+schemeBean.getSchemeName());
    rollNoField.setText(" "+stdRegBean.getRollNo());
 
    }//GEN-LAST:event_marksheetListValueChanged

    private void selectCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCheckBoxActionPerformed
        Resources.getSelectAllList(selectCheckBox, marksheetList);
    }//GEN-LAST:event_selectCheckBoxActionPerformed

  
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
            java.util.logging.Logger.getLogger(MarksheetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MarksheetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MarksheetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MarksheetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MarksheetFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn4;
    private javax.swing.JComboBox<beans.SchemeBean> classCombo;
    private javax.swing.JTextField classField;
    private javax.swing.JButton clearBtn3;
    private javax.swing.JButton deleteBtn3;
    private javax.swing.JComboBox<beans.SchemeBean> examListCombo;
    private javax.swing.JButton exitBtn;
    private javax.swing.JTextField fNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList marksheetList;
    private javax.swing.JTextField monthTextField;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton printBtn;
    private javax.swing.JTextField rollNoField;
    private javax.swing.JComboBox<beans.SchemeBean> schemeCombo;
    private javax.swing.JCheckBox selectCheckBox;
    private javax.swing.JTable stdTable;
    private javax.swing.JTable subTable;
    private javax.swing.JComboBox<beans.SchemeBean> termCombo;
    private javax.swing.JTextField yearField;
    // End of variables declaration//GEN-END:variables
}
