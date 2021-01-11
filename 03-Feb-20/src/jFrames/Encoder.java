package jFrames;

public class Encoder{
  public static String encodeSubjectType(String text){
    String temp="";
    String parts[]=text.split(" ");
    
    for(int i=0;i<parts.length;i++){
      String str=parts[i].toLowerCase();
      if(parts[i].length()<3)
        temp+=str.charAt(0);
      else
        temp+=str.substring(0,2);
    }
    
    return temp;
  }//End of encodeSubjectType method
}//End Encoder class
