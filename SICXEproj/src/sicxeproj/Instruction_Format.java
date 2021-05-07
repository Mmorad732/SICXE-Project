

package sicxeproj;


public class Instruction_Format {
      String pc;
      String Base ;
      String n,i,x,b,p,e,TA;
    public String[][] Extract(String [][] table , String [][] symbolTable){
        Base =baseValue(table,symbolTable);
        Converter convert = new Converter();
         
      
         for(int k = 1;k<table.length;k++){
            int format = formatcalc(table[k][2] ,  table);
           
            if(k<table.length-1){
            pc = table[k+1][0];
            }
            
            
            
            
            
            
            
            if(table[k][2].trim().equals("RESW")||table[k][2].trim().equals("RESB")){
                   table[k][4]= "No object code";
                   
               }else if(table[k][2].trim().equals("WORD")){
                   
                   String temp1 = Integer.toHexString(Integer.parseInt(table[k][3]));
   
                   temp1=convert.LengthCheck(temp1,6);    
                    table[k][4] = temp1 ;
                   
               }else if(table[k][2].trim().equals("BYTE")){
                   
                       String temp2 = table[k][3].substring(2, table[k][3].length()-1);
                
                       char[] char1 = table[k][3].toCharArray();

                       
                       if(table[k][3].trim().startsWith("C")){
                           char1=temp2.toCharArray();
                           temp2 = "";
                           int ascii ;
                           for(int l =0;l<char1.length;l++){
                               ascii = char1[l];
                               temp2+=ascii;
                           }
                           
                           
                           
                       }
                       
                     
                       table[k][4] =temp2;
                   
                   
                   
       
               }else{
                    
                   if(table[k][2].trim().equals("RSUB")){
                    
                    String address=convert.OPCODE(table[k][2].trim());
                     address += "0000";
                     table[k][4] = address;
                     continue;
                   }
                   

            
               }
            
            
            
            
            
            
            
            
            
            
            
            
           
           
            switch(format){
              
                case 1:
                    String opcode = convert.OPCODE(table[k][2]);
                    table[k][4]=opcode;
                    break;
                case 2:
                     opcode = convert.OPCODE(table[k][2]);
                     String reg1 , reg2;
                     if(table[k][3].length() == 1){
                      reg1 = convert.register_Translation(table[k][3]);
                      reg2="0";
                     }else{
                       char[] arr = table[k][3].toCharArray();
                       reg1 = convert.register_Translation(arr[0]+"");
                       reg2 = convert.register_Translation(arr[arr.length-1]+"");
                     }
                     opcode+=reg1+reg2;
                     
                     table[k][4]=opcode;
                     break;
                case 3:
                     opcode = convert.OPCODE(table[k][2]);
                     String binopcode="" ;
                     String disp ;
                    char[] opcodechar = opcode.toCharArray();
                    for(int r =0 ; r<opcodechar.length;r++){
                        String temp = Integer.toBinaryString(Integer.parseInt(opcodechar[r]+"", 16));
                        binopcode += convert.LengthCheck(temp,4);
                    }
                    binopcode = eleminatelast2bits(binopcode.toCharArray());
                    e="0";
                    if(table[k][3].trim().endsWith("X")){
                     n=i=x="1";
                     String temp = table[k][3].replace(",X", "");
                     TA = searchST(symbolTable,temp);
                     disp = convert.LengthCheck(displacement(TA),3);
                   
                    }else {
                        x= "0";
                        
                   
                     
                    
                    
                    
                    
           
                     if(table[k][3].startsWith("#")){
                    
                         i="1";
                         n="0";
                         String temp = table[k][3].replace("#","");
                         if(searchST(symbolTable,temp)==null){
                           TA = Integer.toHexString(Integer.parseInt(temp));
                           disp = convert.LengthCheck(TA,3);
                           
                         }else{
                             TA = searchST(symbolTable,temp);
                             disp = convert.LengthCheck(displacement(TA),3);
                         }
                         
                     }else if(table[k][3].toUpperCase().startsWith("@")){
                      
                          i="0";
                          n="1";
                          String temp = table[k][3].toUpperCase().replace("@","");
                         if(searchST(symbolTable,temp)==null){
                           TA = Integer.toHexString(Integer.parseInt(temp));
                           disp = convert.LengthCheck(displacement(TA),3);
                         }else{
                            TA = searchST(symbolTable,temp);
                            disp = convert.LengthCheck(displacement(TA),3);
                         }
                         
                     }else{
                         i=n="1";
                         if(searchST(symbolTable,table[k][3])==null){
                           TA = table[k][3];
                           disp = convert.LengthCheck(TA, 3);
                         }else{
                            TA = searchST(symbolTable,table[k][3]);
                            disp = convert.LengthCheck(displacement(TA),3);
                         }
                        
                     }
                     
                    }
                     
                     
                     binopcode += n+i+x+b+p+e;
                     binopcode = Integer.toHexString(Integer.parseInt(binopcode,2)).toUpperCase();
                     binopcode = convert.LengthCheck(binopcode, 3);
                     binopcode += disp;
                    
                   
                     table[k][4]= binopcode.toUpperCase();
                     
                     
                    break;
                    
                    
                    
                    
                    
                    
                    
                case 4:
                    String tempopcode= table[k][2].replace("+", "");
                    opcode = convert.OPCODE(tempopcode);
                     binopcode="" ;
                     opcodechar = opcode.toCharArray();
                    for(int r =0 ; r<opcodechar.length;r++){
                        String temp = Integer.toBinaryString(Integer.parseInt(opcodechar[r]+"", 16));
                        binopcode += convert.LengthCheck(temp,4);
                    }
                    binopcode = eleminatelast2bits(binopcode.toCharArray());
                   
                    b=p="0";
                    e="1";
                   
                    if(table[k][3].toUpperCase().endsWith("X")){
                      
                     n=i=x="1";
                     String temp = table[k][3].replace(",X", "");
                     TA = convert.LengthCheck(searchST(symbolTable,temp),5);
                     
                    }else{
                        x="0";
                     if(table[k][3].startsWith("#")){
                    
                         i="1";
                         n="0";
                         String temp = table[k][3].replace("#","");
                         if(searchST(symbolTable,temp)==null){
                          
                           TA = Integer.toHexString(Integer.parseInt(temp));
                           TA = convert.LengthCheck(TA,5);
                           
                         }else{
                           TA = convert.LengthCheck(searchST(symbolTable,temp),5);
                         }
                         
                     }else if(table[k][3].toUpperCase().startsWith("@")){
                      
                          i="0";
                          n="1";
                          String temp = table[k][3].toUpperCase().replace("@","");
                         if(searchST(symbolTable,temp)==null){
                           
                           TA = convert.LengthCheck(temp,5);
                         }else{
                           TA = convert.LengthCheck(searchST(symbolTable,temp),5);
                         }
                         
                     }else{
                         i=n="1";
                         if(searchST(symbolTable,table[k][3])==null){
                           TA = table[k][3];
                         }else{
                           TA = convert.LengthCheck(searchST(symbolTable,table[k][3]),5);
                         }
                        
                     }
                    }
                      binopcode+=n+i+x+b+p+e;
                     
                      binopcode= Integer.toHexString(Integer.parseInt(binopcode,2)).toUpperCase();
                    
                      binopcode+=TA;
                      
              
                       table[k][4]=binopcode;
                     break;         
            }
              
           
            
            
         }
         
         
         
 
         
        return table;
    }
    
    
    
    public String baseValue(String [][] table , String [][] symbolTable){
       String base="";
         for(int k = 1;k<table.length;k++){
             if(table[k][2].equals("BASE")){
                 for(int j = 0; j < symbolTable.length ; j++){
                     if(table[k][3].equals(symbolTable[j][0])){
                         base = symbolTable[j][1];
                         break;
                     }
                 }
             }
         }
        return base;
    }
    public String location(String str , String [][] symbolTable){ 
        
            String loc ="";
            for(int j = 0; j < symbolTable.length ; j++){
                if(str.equals(symbolTable[j][0])){
                       loc = symbolTable[j][1];
                }
            }
              return loc;
    }
    
    public int formatcalc(String str , String [][] table){
        
        Converter con = new Converter();
        for(int i = 1 ; i<table.length-1;i++){
            if(str == table[i][2] && str.equals("RESW")==false && str.equals("BYTE")==false &&  str.equals("WORD")==false &  str.equals("RESB")==false && str.equals("BASE")==false){
                int op1 =  Integer.parseInt(table[i+1][0],16), op2=Integer.parseInt(table[i][0],16);
                return op1-op2;
            }
        }
      return 0;
    }
   
   
    
    public String eleminatelast2bits(char[] binarr){
        String str="";
        for(int i =0; i< binarr.length-2;i++){
            str +=binarr[i];
        }
        return str;
    }
    public String searchST(String [][] st, String ref ){
         
         for(int i=0;i<st.length;i++){
           if(ref.contains(st[i][0])){
               return st[i][1];
           }
         }
        return null;
    
    }
  
    public String retTAval(String[][] table , String [][] st , String TA){
        for(int i =0;i<table.length;i++){
            if(TA.equals(table[i][0])){
                System.out.println(table[i][0] + "--->"+ table[i][3]);
            }
        }
    
    
    
    
       return"";
    }
    
   public String displacement(String TA  ){
        
        int intTA = Integer.parseInt(TA,16);
        int intpc = Integer.parseInt(pc,16);
        int disp = intTA-intpc ;
        if(-2048 <=disp && disp <= 2047){
            b= "0" ;
            p= "1";
           
            if(disp<0){
                String negdisp = Integer.toHexString(disp);
                negdisp=negdisp.substring(negdisp.length()-3 , negdisp.length());
                return negdisp;
            }
            return Integer.toHexString(disp);
        }
        int intbase = Integer.parseInt(Base,16);
        disp = intTA - intbase ;
        if(0<=disp && disp<=4095){
            b="1";
            p="0";
          
            if(disp<0){
                String negdisp = Integer.toHexString(disp);
                negdisp=negdisp.substring(negdisp.length()-3 , negdisp.length());
                return negdisp;
            }
           return Integer.toHexString(disp);
        }
        
       
       return "";
   }

}