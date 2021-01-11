package resources;

import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;

public class Validation{
  
  final public static String INSERTED_STRING="Records Successfully Inserted!!";
  final public static String UPDATED_STRING="Records Successfully Updated!!";
  final public static String DELETED_STRING="Records Successfully Deleted!!";
  
  final public static String NOT_INSERTED_STRING="SQL Error while inserting data!! error:";
  final public static String NOT_UPDATED_STRING="SQL Error while updating data!! error:";
  final public static String NOT_DELETED_STRING="SQL Error while deleting data!! error:";

  final public static String NOT_INSERTED_STRING_ERROR="Data not inserted!! Something went wrong";
  final public static String NOT_UPDATED_STRING_ERROR="Data not updated!! Something went wrong";
  final public static String NOT_DELETED_STRING_ERROR="Data not deleted!! Something went wrong";

  final public static String INCORRECT_PASSWORD_USERNAME= "Incorrect password or username";
  final public static String ENTER_USERNAME="**Please enter username**";
  final public static String ENTER_PASSWORD="**Please enter password**";
  final public static String UNATHORIZED_STRING="You are unathorized!!!, you're noted, we will reach you soon";
  final public static String SQL_DATA_LOAD_ERROR="SQL error while loading data!! error:";
  final public static String SOMETHING_WENT_WRONG="Something went wrong!!";
  final public static String SELECT_SINGLE_ROW="Select single row for updating!!";
  final public static String RECORD_EXISTS="Record already exists!! use clear button to enter new data";
  final public static String DELETE_RECORDS_CONFIRM="Are you you sure to delete ";
  final public static String ACCOUNT_NOT_EXISTS="Account does not exists";
  
  public static void messageSQLError(Exception error){
    messageBox(SQL_DATA_LOAD_ERROR,error);
  }//End of messageSQLError method
  
  public static void printSQLError(Exception error){
    printMessage(SQL_DATA_LOAD_ERROR,error);
  }//End of messageSQLError method
  
  public static void messageDeleteError(){
    messageBox(NOT_DELETED_STRING_ERROR);
  }//End of messageDeleteError method
  
  public static void printDeleteError(){
    printMessage(NOT_DELETED_STRING_ERROR);
  }//End of printDeleteError method
  
  public static void messageUpdateError(){
    messageBox(NOT_UPDATED_STRING_ERROR);
  }//End of messageUpdateError method

  public static void printUpdateError(){
    printMessage(NOT_UPDATED_STRING_ERROR);
  }//End of printNotUpdated method

  public static void messageInserteError(){
    messageBox(NOT_INSERTED_STRING_ERROR);
  }//End of messageInsertError method
  
  public static void printInsertError(){
    printMessage(NOT_INSERTED_STRING_ERROR);
  }//End of printInsertError method
  
  public static void messageNotDeleted(Exception error){
    messageBox(NOT_DELETED_STRING,error);
  }//End of messageNotDeleted method
  
  public static void printNotDeleted(Exception error){
    printMessage(NOT_DELETED_STRING,error);
  }//End of printNotDeleted method
  
  public static void messageNotUpdated(Exception error){
    messageBox(NOT_UPDATED_STRING,error);
  }//End of messageNotUpdated method

  public static void printNotUpdated(Exception error){
    printMessage(NOT_UPDATED_STRING,error);
  }//End of printNotUpdated method

  public static void messageNotInserted(Exception error){
    messageBox(NOT_INSERTED_STRING,error);
  }//End of messageNotInserted method
  
  public static void printNotInserted(Exception error){
    printMessage(NOT_INSERTED_STRING,error);
  }//End of printNotInserted method
  
  public static void messageDeleted(){
    messageDeleted("");
  }//End of messageDeleted method
  public static void messageDeleted(String msg){
    messageBox(msg, DELETED_STRING);
  }//End of messageDeleted method
  
  public static void printDeleted(){
    printDeleted("");
  }//End of printDeleted method
  
  public static void printDeleted(String msg){
    printMessage(msg,DELETED_STRING);
  }//End of printDeleted method
  
  public static void printUpdated(){
    printUpdated("");
  }//End of printUpdated method
  
  public static void messageUpdated(){
    messageUpdated("");
  }//End of messageUpdated method
  
  public static void messageUpdated(String msg){
    messageBox(msg,UPDATED_STRING);
  }//End of messageUpdated method
  
  public static void printUpdated(String msg){
    printMessage(msg,UPDATED_STRING);
  }//End of printUpdated method
  
  public static void printInserted(){
    printInserted("");
  }//End of printInserted method
  
  public static void messageInserted(){
    messageInserted("");
  }//End of messageInserted method
  
  public static void messageInserted(String msg){
    messageBox(msg,INSERTED_STRING);
  }//End of messageInserted method
  
  public static void printInserted(String msg){
    printMessage(msg,INSERTED_STRING);
  }//End of printInserted method
  
  public static void printMessage(String msg,Exception error){
    error.printStackTrace();
    printMessage(msg,error+"");
  }//End of printMessage method
  
  public static void messageBox(String msg,Exception error){
    error.printStackTrace();
    messageBox(msg,error+"");
  }//End of messageBox method
  
  public static void printMessage(String status){
    printMessage("",status);
  }//End of printMessage method
  
  public static void printMessage(String msg,String status){
    System.out.println((msg.equals("")?"":msg+" ")+status);
  }//End of printMessageMethod
  
  public static void messageBox(String status){
    messageBox("",status);
  }//End of messageBox method
  
  public static void messageBox(String msg,String status){
    javax.swing.JOptionPane.showMessageDialog(null, (msg.equals("")?"":msg+" ")+status);
  }//End of messageBox method
  
  public static void clearFormData(javax.swing.text.JTextComponent[]  field){
    clearFields(field);
  }//End of clearFormData method
  
  public static void clearFormData(javax.swing.text.JTextComponent[] field, javax.swing.JComboBox[] combo){
    clearFormData(field, combo, null);
  }//End of clearFormData method
  
  public static void clearFormData(javax.swing.text.JTextComponent[] field,javax.swing.JComboBox[] combo,String[] comboDefault){
    clearFormData(field, combo, comboDefault, null, null);
  }//End of clearFormData method
  
  public static void clearFormData(javax.swing.text.JTextComponent[] field,javax.swing.JComboBox[] combo,String comboDefault[], javax.swing.JList list,javax.swing.JTable table){
    if(field!=null)
      clearFields(field);
    
    if(combo!=null){
      clearCombo(combo, comboDefault);
    }
    
    if(list!=null)
      list.clearSelection();
    
    if(table!=null)
      table.clearSelection();
    
  }//End of clearFormData method
  
  public static void clearCombo(javax.swing.JComboBox[] combo,String[] values){
    for(int i=0;i<combo.length;i++){
        if(values.length==combo.length)
          combo[i].setSelectedItem(values[i]);
        else
          combo[i].setSelectedIndex(0);
      }
  }//End of clearCombo method
  
  public static void clearFields(javax.swing.text.JTextComponent[] field,javax.swing.ListSelectionModel list){
    list.clearSelection();
    clearFields(field);
  }//End of clearFields method
  
  public static void clearFields(javax.swing.text.JTextComponent[] field){
    for(int i=0;i<field.length;i++)
        field[i].setText("");
  }//End of clearFields method
  
  public static boolean checkFields(javax.swing.text.JTextComponent[] field){
    boolean check=false;
    int fields=0;
    for(int k=0;k<field.length;k++){
      if(field[k].getText().isEmpty()){
        setImage("icons/alert.png", field[k]);
        check=true;
        fields++;
        field[k].setText("\r");
        field[k].setText("");
      }
    }
    if(check)
      messageBox(fields+" Field(s) empty!!");
    
    return check;
  }//End of validateFormDataMethod 
  
  //ValidateFormData(new JTextComponent[]{rollNoField,aa,aa..},new JButton[]{addBtn,updateBtn});
  public static void validateFormData(javax.swing.text.JTextComponent[] textField,javax.swing.JButton[] btn){
    if(textField!=null && btn!=null){
      for(int i=0;i<btn.length;i++)
        btn[i].addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            boolean check=false;
            for(int k=0;k<textField.length;k++){
              if(textField[k].getText().equals("")){
                setImage("icons/alert.png", textField[k]);
                check=true;
                textField[k].setText("\r");
              }
            }
            if(check)
              new javax.swing.JFrame().getToolkit().beep();
          }
        });
    }
  }//End of validateFormData method

  
  public static boolean validateFormData(javax.swing.text.JTextComponent[] textField,javax.swing.JComboBox<String>[] date,javax.swing.JButton[] btn){
    boolean check=false;
    
    if(textField!=null && date!=null && btn!=null){
      
      for(int i=0;i<btn.length;i++)
        btn[i].addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            boolean check=false;
            for(int k=0;k<textField.length;k++){
              if(textField[k].getText().equals("")){
                setImage("icons/alert.png", textField[k]);
                check=true;
                textField[k].setText("\r");
              }
            }
            if(check)
              new javax.swing.JFrame().getToolkit().beep();
          }
        });
    }
    return check;
  }//End of validateFormData method
  
  public static void loadDate(javax.swing.JComboBox<String> dayCombo,javax.swing.JComboBox<String> monthCombo, javax.swing.JComboBox<String> yearCombo){
    String month[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};
    
    dayCombo.removeAllItems();
    monthCombo.removeAllItems();
    yearCombo.removeAllItems();
    
    for(int i=1970;i<=2030;i++)
      yearCombo.addItem(i+"");
    for(int i=0;i<month.length;i++)
      monthCombo.addItem(month[i]);
    for(int i=1;i<=31;i++){
      dayCombo.addItem(i+"");
    }
    yearCombo.setSelectedItem("1996");
  }//End of loadDate method
  
  public static void validateNumberFields(javax.swing.text.JTextComponent[] field){
    for(int i=0;i<field.length;i++)
      validateNumberField(field[i]);
  }//End of validateNumberFields methods
  
  public static void validateNumberField(javax.swing.text.JTextComponent field){
    validateField(field, "number");
  }//End of validateNumberField
  
  public static void validateField(javax.swing.text.JTextComponent[] field){
    for(int i=0;i<field.length;i++)
      validateField(field[i]);
  }//End of validateFields method
  
  public static void validateField(javax.swing.text.JTextComponent field){
    validateField(field,"");
  }//End of validateField method
  
//  public static void validateCombo(javax.swing.JComboBox combo){
//    combo.addActionListener(new java.awt.event.ActionListener(){
//      public void actionPerformed(ActionEvent e) {
//       if(combo.getSelectedIndex()==0){
//         setImage("icons/alert.png", combo);
//         combo.setBackground(new java.awt.Color(247, 141, 141));
//         new javax.swing.JFrame().getToolkit().beep();
//       }else
//         combo.setBackground(java.awt.Color.white);
//      }
//    });
//  }//End of validateCombo method
  
  public static void validateField(javax.swing.text.JTextComponent field,String type){
    
    //FocusListener Event
    field.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(java.awt.event.FocusEvent fe){
        String text=field.getText();
        if(text.trim().equals("")){
          field.removeAll();
          setImage("icons/alert.png", field);
          new javax.swing.JFrame().getToolkit().beep();
          field.setText("\r");
        }
      }
      
      public void focusGained(java.awt.event.FocusEvent fe){
        if(field.getText().trim().equals("")){
          field.removeAll();
          field.setBackground(java.awt.Color.white);
        }
      }
    });
    
    
    //KeyListener Event
    field.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent ke){
        if(type.equals("number")){
          char ch=ke.getKeyChar();
          if(!Character.isDigit(ch) && ch!=java.awt.event.KeyEvent.VK_LEFT && ch!=java.awt.event.KeyEvent.VK_RIGHT 
            && ch!=java.awt.event.KeyEvent.VK_BACK_SPACE && ch!=java.awt.event.KeyEvent.VK_DELETE 
            && (ch!=java.awt.event.KeyEvent.VK_CONTROL && ch!=java.awt.event.KeyEvent.VK_A || ch!=java.awt.event.KeyEvent.VK_C 
                  || ch!=java.awt.event.KeyEvent.VK_V || ch!=java.awt.event.KeyEvent.VK_X) && (ch!='.'))
            ke.consume();
          else
            if(field.getText().indexOf(".")!=-1 && ch=='.'){
              ke.consume();
              new javax.swing.JFrame().getToolkit().beep();
            }
         }
      }
      
      public void keyReleased(java.awt.event.KeyEvent ke){
        if(!field.getText().trim().equals("")){
          field.setBackground(java.awt.Color.white);
          field.removeAll();
        }
        else{
//          field.setBackground(new java.awt.Color(247, 141, 141));
          field.removeAll();
          setImage("icons/alert.png", field);
          new javax.swing.JFrame().getToolkit().beep();
          field.setText("\r");
        }
      }
    });
  }//End of validateNumberField method
  
  public static void setImage(String path,javax.swing.JComponent field){
    java.awt.Image img=new javax.swing.ImageIcon(path).getImage().getScaledInstance(field.getHeight()-10,field.getHeight()-10, java.awt.Image.SCALE_SMOOTH);
    javax.swing.JLabel alertLabel = new javax.swing.JLabel(new javax.swing.ImageIcon(img));
    field.setLayout(new java.awt.BorderLayout());
    field.setBackground(new java.awt.Color(247, 141, 141));
    field.add(alertLabel, java.awt.BorderLayout.EAST);
  
  }//End of setImage method
  
//  public static void reflectSetObject(javax.swing.text.JTextComponent[] fields,javax.swing.JComboBox<String> combo,String colNames[],Object obj){
//    java.lang.reflect.Method[] getMethods=getMethods(obj, colNames, "set");
//    
//    
//  }//End of reflect Object method
//
//  public static void reflectGetObject(javax.swing.text.JTextComponent[] fields,javax.swing.JComboBox<String> combo, String colNames[],Object obj){
//    java.lang.reflect.Method[] getMethods=getMethods(obj, colNames, "get");
//    
//    for(int i=0;i<colNames.length;i++){
//      String methodName=getMethods[i].getName().substring(3,i).toLowerCase();
//      String colParts[]=colNames[i].split(":");
////      switch(colParts){
////        
////        default:
////          get[1].invoke(sampleObject, "data");
////      }
//    }
//  }//End of reflectGetObject method
//  
//  public static java.lang.reflect.Method[] getMethods(Object obj,String colNames[],String methodType){
//    java.lang.reflect.Method[] setMethods=new java.lang.reflect.Method[colNames.length];
//    java.lang.reflect.Method[] methods=obj.getClass().getDeclaredMethods();
//    
//    for(int i=0,index=0;i<methods.length;i++){
//      java.lang.reflect.Method m=methods[i];
//      String methodName=(colNames[i].charAt(0)+"").toUpperCase()+colNames[i].substring(1,colNames[i].length());
//      
//      if(m.getName().startsWith(methodType+methodName)){
//				setMethods[index]=m;
//        System.out.println(m.getName());
//      }
//    }//End of loop
//    return setMethods;
//  }//End of getSetMethods
  
  
}//End of class
