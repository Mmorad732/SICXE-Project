

package sicxeproj;


public class LocationCounter {
       String[][] ExtractLC(String[][] table){
          
           String [] locationCount = new String[table.length];
           
          Converter conv = new Converter();
             
             
           locationCount[0] =  conv.LengthCheck(table[0][3],4);
           locationCount[1] =  conv.LengthCheck(table[0][3],4);
          
           int j=1;
           
           for(int i = 1 ; i < table.length-1;i++){
             
               
               
               
            if(table[i][2].toUpperCase().equals("RESW")){
            int temp=Integer.parseInt(table[i][3])*3;
            locationCount[j+1] = Integer.toHexString(Integer.parseInt(locationCount[j],16)+temp).toUpperCase();
            }else if(table[i][2].toUpperCase().equals("RESB")){
              int temp=Integer.parseInt(table[i][3]);
            locationCount[j+1] = Integer.toHexString(Integer.parseInt(locationCount[j],16)+temp).toUpperCase();
            }else if(table[i][2].toUpperCase().equals("BYTE")){
                char[] temp= table[i][3].toCharArray();
               if(table[i][3].toUpperCase().startsWith("X") ){                 
                  int count= (temp.length -3)/2;
                  locationCount[j+1] = Integer.toHexString(Integer.parseInt(locationCount[j],16)+count).toUpperCase() ;
               }else{
                  int count = temp.length-3 ;
                  locationCount[j+1] = Integer.toHexString(Integer.parseInt(locationCount[j],16)+count).toUpperCase() ;
               }
            }else if(table[i][2].toUpperCase().equals("WORD")){
                        locationCount[j+1] = Integer.toHexString(Integer.parseInt(locationCount[j],16)+3).toUpperCase();
            }else{
               if( conv.Format(table[i][2]) == null ){
                    errorHandle h = new errorHandle();
                   if(table[i][2].startsWith("+") == true){
                      locationCount[j+1] = Integer.toHexString(Integer.parseInt(locationCount[j],16)+4).toUpperCase();
                      String temp = table[i][2].replace("+", "");
                      h.handle(temp.trim());
                   }else{
                      if(table[i][2].equals("BASE")){
                    locationCount[j+1] = locationCount[j];  
                      }else{
                        h.handle(table[i][2].trim());
                      }
                   }
                  }else{
                   errorHandle h = new errorHandle();
                   h.handle(table[i][2].trim());   
                   locationCount[j+1] = Integer.toHexString(Integer.parseInt(locationCount[j],16)+Integer.parseInt(conv.Format(table[i][2]))).toUpperCase();        
                         
                 }
              }
            
            locationCount[j] =  conv.LengthCheck(locationCount[j],4);
            j++;
            
           }
           for(int k=0;k<table.length;k++){
               table[k][0] = locationCount[k];
           }
           
         
         
           
           
                      
                         
           return table ;
           
           
          
         
       }
        String [][] ExtractST (String [][] table  ){
            String[][] symboltable = new String[stRowCal(table)][2];
            int j=0;
            for(int i =1 ;i<table.length;i++){
                if(table[i][1].trim().equals(";") == false){
                    symboltable[j][0] = table[i][1];
                    symboltable[j][1] = table[i][0];
                 
                    j++;
                }
            }
           
               return symboltable;
        }
        
       int stRowCal(String[][] table){
          int count =0 ;
          for(int i = 1 ;i <table.length ; i++){
              if(table[i][1].trim().equals(";") == false ){ 
                   count++;
              }
          }
         
          
          return count;
      } 
}
