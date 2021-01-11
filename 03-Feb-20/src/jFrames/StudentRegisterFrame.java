/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jFrames;

import beans.StudentRegistrationBean;
import com.digitalpersona.onetouch.DPFPTemplate;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import resources.Resources;
import DBManager.DatabaseManager;
import java.awt.FileDialog;
import java.io.DataInputStream;
import java.util.StringTokenizer;


/**
 *
 * @author IB
 */
public class StudentRegisterFrame extends javax.swing.JFrame {
    public static String TEMPLATE_PROPERTY = "template";
	private DPFPTemplate template;
        byte[] profileImg=null;
        String path=null;
        String filename;
        private ByteArrayOutputStream profileImage;

    /**
     * Creates new form StudentRegisterFrame
     */
    public StudentRegisterFrame() {
        initComponents();
        loadFirst();
    }
    
    private void loadFirst(){
        Resources.setComboToField(admissionDateField,new JComboBox[]{dateCombo,monthCombo,yearCombo});
        Resources.setComboToField(birthDateField,new JComboBox[]{birthDateCombo,birthMonthCombo,birthYearCombo});
        Resources.setComboToField(sexField,new JComboBox[]{sexCombo});
        Resources.setCurrentDate(new JComboBox[]{dateCombo,monthCombo,yearCombo});
        Resources.setCurrentDate(new JComboBox[]{birthDateCombo,birthMonthCombo,birthYearCombo});
        //profilePic.setIcon('BiometricSchoolManagementSystem\\03-Feb-20\\src\\img\\UserIcon.png');
        String tempPath="src\\img\\UserIcon.png"; 
        profilePic.setIcon(ResizeImage(tempPath));
          try{
        studentsList.setListData(DatabaseManager.getStudentRegistrations());
        }catch(Exception e){
            e.printStackTrace();
        }
    }//End of loadFirst method

    private boolean saveProfileImage(int studentId){
        if(filename!=null)
            try{
                File file=new File("assets/profile_images/"+studentId);
             
                if(!file.exists())
                    file.mkdirs();
                else
                     if(new File("assets/profile_images/"+studentId).listFiles()[0].exists())
                         new File("assets/profile_images/"+studentId).listFiles()[0].delete();
                
                System.out.println(file.getAbsolutePath()+"/"+filename);

                FileOutputStream out=new FileOutputStream(file.getAbsolutePath()+"\\"+studentId+"."+filename.split("\\.")[1]);
                out.write(profileImage.toByteArray());
                out.close();
            }catch(Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        
        return false;
    }//End of saveProfileImage
    
    private beans.StudentRegistrationBean getFieldsData() throws ParseException{
       beans.StudentRegistrationBean bean=new beans.StudentRegistrationBean();
       //JOptionPane.showMessageDialog(this,gardianNameField.getText());
       bean.setStudentName(nameField.getText());
       bean.setFatherName(fatherNameField.getText());
       bean.setSurname(surnameField.getText());
       bean.setGender(sexField.getText());
       bean.setDateOfBirth(birthDateField.getText());
       bean.setMotherName(motherNameField.getText());
       bean.setGuardianName(gardianNameField.getText());
       bean.setPlaceOfBirth(placeOfBirth.getText());
       bean.setGuardianOccupation(gardianOccupationField.getText());
       bean.setAddress(addressField.getText());
       bean.setPhoneNo(phoneField.getText());
       bean.setMobileNo(mobileField.getText());
       bean.setRemarks(remarksField.getText());
       bean.setLastAttendentSchool(lastAttendentSchoolField.getText());
       Calendar cal = Calendar.getInstance();
       String dateText=admissionDateField.getText();
       bean.setAdmissionDate(admissionDateField.getText());
       bean.setProfileImage(""+profileImg);
       bean.setStatus(1);
       
     
         
//        if(studentsList.getSelectedIndex()>-1)
//            bean.setStudentId(((beans.StudentBean)studentsList.getSelectedValue()).getStudentId());
//        
        return bean;
    }//End of getFieldsData method
    
    private void setProfileImage(String directory){
        profileImage=new ByteArrayOutputStream();
        try{
            FileInputStream stdImage=new FileInputStream(directory);
        
            DataInputStream in=new DataInputStream(stdImage);
            byte[] data=new byte[in.available()];
            in.readFully(data);
            profileImage.write(data);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        profilePic.setIcon(new ImageIcon(new ImageIcon(profileImage.toByteArray()).getImage().getScaledInstance(profilePic.getWidth(),profilePic.getHeight(), Image.SCALE_SMOOTH)));
    }//End of setProfileImage method
    
    private void imageUpload(){
        FileDialog dialog=new FileDialog(this,"Browse Image",FileDialog.LOAD);
        dialog.setVisible(true);
        
        filename=dialog.getFile();
        String directory=dialog.getDirectory();
        
        if(filename!=null){
           try{
               System.out.println(directory+filename);
               
               setProfileImage(directory+filename);
           }catch(Exception e){
               e.printStackTrace();
               JOptionPane.showMessageDialog(null, "Invalid Image!");
           }
        }
        

    }// end of imageUpload
    
    // Methode to resize imageIcon with the same size of a Jlabel
    private ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(profilePic.getWidth(), profilePic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
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
        placeOfBirth = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        profilePic = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        testScoreField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        birthMonthCombo = new javax.swing.JComboBox<String>();
        dateCombo = new javax.swing.JComboBox<String>();
        jLabel35 = new javax.swing.JLabel();
        fatherNameLabel = new javax.swing.JLabel();
        dateOfBirthLabel = new javax.swing.JLabel();
        testScoreLabel = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        admissionLabel = new javax.swing.JLabel();
        birthYearCombo = new javax.swing.JComboBox<String>();
        jLabel43 = new javax.swing.JLabel();
        yearCombo = new javax.swing.JComboBox<String>();
        fatherNameField = new javax.swing.JTextField();
        mobileLabel = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        gardianOccupationField = new javax.swing.JTextField();
        remarksField = new javax.swing.JTextField();
        motherNameField = new javax.swing.JTextField();
        lastAttendentSchoolField = new javax.swing.JTextField();
        surnameField = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        birthDateCombo = new javax.swing.JComboBox<String>();
        sexCombo = new javax.swing.JComboBox<String>();
        jLabel48 = new javax.swing.JLabel();
        sexLabel = new javax.swing.JLabel();
        surnameLabel = new javax.swing.JLabel();
        mobileField = new javax.swing.JTextField();
        motherNameLabel = new javax.swing.JLabel();
        gardianNameField = new javax.swing.JTextField();
        addressField = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        IdField = new javax.swing.JTextField();
        nameLabel1 = new javax.swing.JLabel();
        sexField = new javax.swing.JTextField();
        birthDateField = new javax.swing.JTextField();
        monthCombo = new javax.swing.JComboBox<String>();
        admissionDateField = new javax.swing.JTextField();
        browseBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        studentsList = new javax.swing.JList<Object>();
        selectCheckBox = new javax.swing.JCheckBox();
        jButton11 = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Student Register");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 14))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(685, 700));
        jPanel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                jPanel2ComponentMoved(evt);
            }
        });

        placeOfBirth.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N

        nameLabel.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameLabel.setText("Std/Name:");

        jLabel29.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Std Photo");

        phoneField.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N

        testScoreField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N

        nameField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N

        birthMonthCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        birthMonthCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        birthMonthCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                birthMonthComboActionPerformed(evt);
            }
        });

        dateCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        dateCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        dateCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("PlaceOfBirth:");

        fatherNameLabel.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        fatherNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fatherNameLabel.setText("F/Name:");

        dateOfBirthLabel.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        dateOfBirthLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateOfBirthLabel.setText("DOB:");

        testScoreLabel.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        testScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        testScoreLabel.setText("Test Score:");

        jLabel41.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Phone#:");

        admissionLabel.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        admissionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        admissionLabel.setText("Admission Date:");

        birthYearCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        birthYearCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035" }));
        birthYearCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                birthYearComboActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("Guardian:");

        yearCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        yearCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035" }));
        yearCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearComboActionPerformed(evt);
            }
        });

        fatherNameField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N

        mobileLabel.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        mobileLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mobileLabel.setText("Mob#:");

        jLabel46.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel46.setText("Last Attendent School:");

        gardianOccupationField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N

        remarksField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N

        motherNameField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N

        lastAttendentSchoolField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N

        surnameField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N

        jLabel47.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel47.setText("Guardn Occupation:");

        birthDateCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        birthDateCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        birthDateCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                birthDateComboActionPerformed(evt);
            }
        });

        sexCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        sexCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        sexCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexComboActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel48.setText("Remarks:");

        sexLabel.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        sexLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sexLabel.setText("Sex:");

        surnameLabel.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        surnameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        surnameLabel.setText("Surname:");

        mobileField.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N

        motherNameLabel.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        motherNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        motherNameLabel.setText("MotherName:");

        gardianNameField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N

        addressField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N

        jLabel55.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel55.setText("Address:");

        IdField.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        IdField.setEnabled(false);

        nameLabel1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        nameLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameLabel1.setText("Std/Id:");

        sexField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        sexField.setEnabled(false);

        birthDateField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        birthDateField.setEnabled(false);

        monthCombo.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        monthCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        monthCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthComboActionPerformed(evt);
            }
        });

        admissionDateField.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        admissionDateField.setEnabled(false);

        browseBtn.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        browseBtn.setText("Browse");
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(fatherNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(fatherNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(motherNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(motherNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel46)
                                        .addGap(70, 70, 70)
                                        .addComponent(admissionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGap(183, 183, 183)
                                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(gardianNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(5, 5, 5)
                                                .addComponent(placeOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel47)
                                                .addGap(6, 6, 6)
                                                .addComponent(gardianOccupationField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(7, 7, 7)
                                                .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel55)
                                                .addGap(332, 332, 332)
                                                .addComponent(mobileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)
                                                .addComponent(mobileField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(testScoreLabel)
                                                .addGap(4, 4, 4)
                                                .addComponent(testScoreField, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(remarksField, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lastAttendentSchoolField, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(dateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(monthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(yearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(admissionDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(profilePic, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(IdField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(sexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(sexCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(sexField, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(surnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(dateOfBirthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(birthDateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(birthMonthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(birthYearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(birthDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sexCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sexField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateOfBirthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(birthDateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(birthMonthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(birthYearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(birthDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(surnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fatherNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fatherNameField)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(motherNameField)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(motherNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(gardianNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(placeOfBirth, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel47))
                            .addComponent(gardianOccupationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mobileField)
                            .addComponent(mobileLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(testScoreField)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(remarksField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(testScoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(admissionLabel))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lastAttendentSchoolField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(monthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(yearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(admissionDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(profilePic, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(browseBtn)))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Students", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 12))); // NOI18N

        studentsList.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        studentsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                studentsListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(studentsList);

        selectCheckBox.setFont(new java.awt.Font("Courier New", 1, 11)); // NOI18N
        selectCheckBox.setText("Select All");
        selectCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCheckBoxActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jButton11.setText("PRINT ID(s)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(selectCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addComponent(jButton11)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectCheckBox)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        deleteBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        clearBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        exitBtn.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteBtn)
                            .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel2ComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2ComponentMoved

    private void birthDateComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_birthDateComboActionPerformed
        Resources.setComboToField(birthDateField,new JComboBox[]{birthDateCombo,birthMonthCombo,birthYearCombo});
    }//GEN-LAST:event_birthDateComboActionPerformed

    private void birthMonthComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_birthMonthComboActionPerformed
        Resources.setComboToField(birthDateField,new JComboBox[]{birthDateCombo,birthMonthCombo,birthYearCombo});
    }//GEN-LAST:event_birthMonthComboActionPerformed

    private void birthYearComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_birthYearComboActionPerformed
        Resources.setComboToField(birthDateField,new JComboBox[]{birthDateCombo,birthMonthCombo,birthYearCombo});
    }//GEN-LAST:event_birthYearComboActionPerformed

    private void sexComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexComboActionPerformed
        Resources.setComboToField(sexField,new JComboBox[]{sexCombo});
    }//GEN-LAST:event_sexComboActionPerformed

    private void dateComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboActionPerformed
        Resources.setComboToField(admissionDateField,new JComboBox[]{dateCombo,monthCombo,yearCombo});
    }//GEN-LAST:event_dateComboActionPerformed

    private void monthComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthComboActionPerformed
        Resources.setComboToField(admissionDateField,new JComboBox[]{dateCombo,monthCombo,yearCombo});
    }//GEN-LAST:event_monthComboActionPerformed

    private void yearComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearComboActionPerformed
        Resources.setComboToField(admissionDateField,new JComboBox[]{dateCombo,monthCombo,yearCombo});
    }//GEN-LAST:event_yearComboActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        if(studentsList.getSelectedIndex()>-1)
            return;
        try{
            int row=DatabaseManager.addStudentRegistration(getFieldsData());
    
            if(row>0){
                saveProfileImage(row);
                JOptionPane.showMessageDialog(null,"Data Inserted!!!");
            }
            studentsList.setListData(DatabaseManager.getStudentRegistrations());
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while inserting error:"+e);
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnActionPerformed
    imageUpload();
    }//GEN-LAST:event_browseBtnActionPerformed

    private void studentsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_studentsListValueChanged
      setFieldData((beans.StudentRegistrationBean)studentsList.getSelectedValue());
      
    }//GEN-LAST:event_studentsListValueChanged

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_exitBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        //if(studentsList.getSelectedIndex()>0)
            try{
                int rollNo=((StudentRegistrationBean)studentsList.getSelectedValue()).getRollNo();
                StudentRegistrationBean studentBean=getFieldsData();
                studentBean.setRollNo(rollNo);

                
                int row=DatabaseManager.updateStudentRegistrationByRollNo(studentBean,rollNo);
//                JOptionPane.showMessageDialog(this,
//                        "Std_name="+studentBean.getStudentName()+""
//                        );

                if(row>0){
                    saveProfileImage(rollNo);
                    JOptionPane.showMessageDialog(null,"Data Update successfully..!!!");
                }
                studentsList.setListData(DatabaseManager.getStudentRegistrations());

            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while inserting error:"+e);
            }        
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
  
        int confirm= JOptionPane.showConfirmDialog(this, "Are Your Sure To Detlete Recod..?","",JOptionPane.YES_NO_OPTION );
     if(confirm==0)
       deleteStudentRegistration();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
       nameField.setText("");
       fatherNameField.setText("");
       surnameField.setText("");
       sexField.setText("");
       birthDateField.setText("");
       motherNameField.setText("");
       gardianNameField.setText("");
       placeOfBirth.setText("");
       phoneField.setText("");
       mobileField.setText("");
       gardianOccupationField.setText("");
       addressField.setText("");
       remarksField.setText("");
       lastAttendentSchoolField.setText("");
       admissionDateField.setText("");
       setProfileImage(new File("assets/UserIcon.png").getAbsolutePath());
        
    }//GEN-LAST:event_clearBtnActionPerformed

    private void selectCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCheckBoxActionPerformed
     Resources.getSelectAllList(selectCheckBox,studentsList);
    }//GEN-LAST:event_selectCheckBoxActionPerformed

    private void setFieldData(beans.StudentRegistrationBean bean){

       if(bean==null)return;
       IdField.setText(""+bean.getRollNo());
       nameField.setText(bean.getStudentName());
       fatherNameField.setText(bean.getFatherName());
       surnameField.setText(bean.getSurname());
       sexField.setText(bean.getGender());
       birthDateField.setText(bean.getDateOfBirth());
       motherNameField.setText(bean.getMotherName());
       gardianNameField.setText(bean.getGuardianName());
       placeOfBirth.setText(bean.getPlaceOfBirth());
       phoneField.setText(bean.getPhoneNo());
       mobileField.setText(bean.getMobileNo());
       gardianOccupationField.setText(bean.getGuardianOccupation());
       addressField.setText(bean.getAddress());
       remarksField.setText(bean.getRemarks());
       lastAttendentSchoolField.setText(bean.getLastAttendentSchool());
       admissionDateField.setText(bean.getAdmissionDate());
       if(new File("assets/profile_images/"+bean.getRollNo()).exists()){
            System.out.println(new File("assets/profile_images/"+bean.getRollNo()).listFiles()[0].getAbsolutePath());
            setProfileImage(new File("assets/profile_images/"+bean.getRollNo()).listFiles()[0].getAbsolutePath());
       }else{
           setProfileImage(new File("assets/UserIcon.png").getAbsolutePath());
       }
     }

   private void deleteStudentRegistration(){
      int rollNo=((StudentRegistrationBean)studentsList.getSelectedValue()).getRollNo();
           try{
          
          int rows=DatabaseManager.deleteStudentRegistrationByRollNo(rollNo);
          if(rows>0)
              JOptionPane.showMessageDialog(this,"Data Delete Sucessfully..");
           studentsList.setListData(DatabaseManager.getStudentRegistrations());
      }catch(Exception e){
          e.printStackTrace();
      }
       
   }
    
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
            java.util.logging.Logger.getLogger(StudentRegisterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentRegisterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentRegisterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentRegisterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentRegisterFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IdField;
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField addressField;
    private javax.swing.JTextField admissionDateField;
    private javax.swing.JLabel admissionLabel;
    private javax.swing.JComboBox<String> birthDateCombo;
    private javax.swing.JTextField birthDateField;
    private javax.swing.JComboBox<String> birthMonthCombo;
    private javax.swing.JComboBox<String> birthYearCombo;
    private javax.swing.JButton browseBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> dateCombo;
    private javax.swing.JLabel dateOfBirthLabel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JTextField fatherNameField;
    private javax.swing.JLabel fatherNameLabel;
    private javax.swing.JTextField gardianNameField;
    private javax.swing.JTextField gardianOccupationField;
    private javax.swing.JButton jButton11;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lastAttendentSchoolField;
    private javax.swing.JTextField mobileField;
    private javax.swing.JLabel mobileLabel;
    private javax.swing.JComboBox<String> monthCombo;
    private javax.swing.JTextField motherNameField;
    private javax.swing.JLabel motherNameLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JTextField phoneField;
    private javax.swing.JTextField placeOfBirth;
    private javax.swing.JLabel profilePic;
    private javax.swing.JTextField remarksField;
    private javax.swing.JCheckBox selectCheckBox;
    private javax.swing.JComboBox<String> sexCombo;
    private javax.swing.JTextField sexField;
    private javax.swing.JLabel sexLabel;
    private javax.swing.JList<Object> studentsList;
    private javax.swing.JTextField surnameField;
    private javax.swing.JLabel surnameLabel;
    private javax.swing.JTextField testScoreField;
    private javax.swing.JLabel testScoreLabel;
    private javax.swing.JButton updateBtn;
    private javax.swing.JComboBox<String> yearCombo;
    // End of variables declaration//GEN-END:variables
}

