

package sicxeproj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Extractor {
    String [][] ArrayExtractor(String filePath ) throws FileNotFoundException
    {
        File codeFile = new File(filePath);
        
         
       
         
       
             Scanner sc = new Scanner(codeFile);
         
             String line = "";
           
             Scanner sc2 = new Scanner(codeFile);
             String templine2="",templine3="";
             StringTokenizer tokenizer ,tokenizer1;
             Scanner sc3 = new Scanner(codeFile);
             sc3.nextLine();
            while( sc2.hasNextLine() ) 
            {  
               templine2=sc2.nextLine(); 
               tokenizer = new StringTokenizer(templine2); 
                if(sc2.hasNext() == false && tokenizer.countTokens()==2){
                    line+= " ; "+templine2.toUpperCase()+" ; ";
                    break;
                }
                if(tokenizer.countTokens()==2 ){
                    line+=" ; " +templine2.toUpperCase()+"\n";
                }else if(tokenizer.countTokens()==1){
                      line += " ; " +templine2.toUpperCase()+" ;"+"\n";
                }else{
                line+= templine2.toUpperCase()+"\n";
                }
               
                
                
            }
             String [] str = line.split("\n");
             int rowsCount=str.length;
             StringTokenizer columnfinder = new StringTokenizer(str[0]);
             int columnsCount =columnfinder.countTokens()+2;
             StringTokenizer sh1 = new StringTokenizer(line);
             StringTokenizer sh2 = new StringTokenizer(line); 
             String [][] modified_Array = new String[rowsCount][columnsCount];
           
               for(int i=0;i<rowsCount;i++){
                  for(int j=0;j<columnsCount;j++){
                        if(sh2.hasMoreTokens()){
                          if(j==0 || j==4){
                              modified_Array[i][j]=";";
                          }else{
                            modified_Array[i][j]=sh2.nextToken().trim().toUpperCase();
                          }
                        }
                    }
                }
             
             
            return modified_Array;
            

        }
    
    void display(String[][] array){
          System.out.println("");
          for(int i=0;i<array.length;i++){
              for(int j=0;j<array[0].length;j++){
                  if(array[i][j]==null){break;}
                  System.out.printf(array[i][j] +"\t");
                  
              }
              System.out.println("");
          }
         
      }
     void display(String[] array){
         
          for(int i=0;i<array.length;i++){
              
              System.out.println(array[i]);
          }
         
      }
    
}
