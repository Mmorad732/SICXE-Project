
package sicxeproj;


public class errorHandle {
    public void handle(String inst){
       Converter con = new Converter(); 
       if(con.OPCODE(inst)== null){
           System.out.println("File invalid");
           System.exit(1);
       }
       
        
    
    }
}
