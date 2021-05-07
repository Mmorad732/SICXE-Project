
package sicxeproj;


public class HTMERecord {
     public String[] Extract(String[][] table ){
     String progName= table[0][1];
     String End="";
     int intstart = Integer.parseInt(table[1][0],16);
      String[] length = null;
        String counter = "";
        String rec="" ;
        int count = 0 , lengthCount=0;
        int f4counter=0;
        int loc1=0 ,loc2=0;
        for(int i = 1;i<table.length;i++){
            if(table[i][2].startsWith("+")){
                f4counter++;
                
            }
           if(count==0 && table[i][4].toUpperCase().equals("NO OBJECT CODE")==false){
               counter+=table[i][0]+"\n";
           }
            if(table[i][4].toUpperCase().equals("NO OBJECT CODE")==false &&  count <10){
                    if(table[i][4].trim().equals(";")==false){
                        rec += table[i][4];
                        count++;
                        
                    }
                    
                    
                   
            }
            if(table[i][4].toUpperCase().equals("NO OBJECT CODE") ){
                 
                 if(count!=0){
                 
                 rec+="\n";
                 count=0;
               }
                continue;
            }
            if(count == 10 || i==table.length-1){
                if(i==table.length-1){
                   End =table[i][0]; 
                }
               rec+="\n";
                count=0;
               
            }
            
      
           
           
        }
        
      
        String[] tempTrec = rec.split("\n");
        String[] tempTreclen = counter.split("\n");
        String[] Treclen = new String[tempTreclen.length];
        Converter conv = new Converter();
        for(int i=1;i<tempTreclen.length;i++){
            int temp1,temp2,temp3;
             temp1 = Integer.parseInt(tempTreclen[i-1],16);
             temp2 = Integer.parseInt(tempTreclen[i],16);
             temp3 = (temp2- temp1) ;
           
            Treclen[i-1] = Integer.toHexString(temp3);
            Treclen[i-1]= Treclen[i-1];
            tempTreclen[i-1] = conv.LengthCheck(tempTreclen[i-1], 6);
            
        }
        int intEnd = Integer.parseInt(End,16);
        int temp1 = Integer.parseInt(tempTreclen[tempTreclen.length-1],16);
        int temp2 = intEnd - temp1;
        
        Treclen[Treclen.length-1]=conv.LengthCheck(Integer.toHexString(temp2),6);
        for(int i=0;i<Treclen.length;i++){
        }
        String[] Trec = new String[Treclen.length];
        for(int i=0;i<Trec.length;i++){
            Trec[i]="T^"+tempTreclen[i]+"^"+Treclen[i].toUpperCase()+"^"+tempTrec[i];
        }
        int j = 0;
        String[] Mrec = new String[f4counter];
        for(int i=1;i<table.length;i++){
            if(table[i][2].startsWith("+") && table[i][3].startsWith("#")==false){
                int intaddress = Integer.parseInt(table[i][0],16)+1;
                Mrec[j]="M^"+conv.LengthCheck(Integer.toHexString(intaddress).toUpperCase(),4)+"^05^"+progName;
                j++;
            }
        }
        String Hrec = "H ^"+ progName +"^"+table[1][0]+"^"+Integer.toHexString(intEnd-intstart).toUpperCase();
        String  Erec = "E ^" +table[1][0];
        String[] HTMErec =new String[Trec.length+Mrec.length+2];
        for(int i=0;i<Trec.length;i++){
                HTMErec[i+1] = Trec[i];
        }
        for(int i=0;i<Mrec.length;i++){
                HTMErec[i+Trec.length+1] = Mrec[i];
        }
        HTMErec[0] = Hrec;
        HTMErec[HTMErec.length-1]=Erec;
        
        return HTMErec;
     }
}
