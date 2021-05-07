package sicxeproj;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.io.FileNotFoundException;


public class SICXEproj {

    
    public static void main(String[] args) throws FileNotFoundException {
        
        Extractor arr = new Extractor();
        String [][] array = arr.ArrayExtractor("text/inSICXE.txt");
        LocationCounter loc = new LocationCounter();
        array = loc.ExtractLC(array);
        String [][] st = loc.ExtractST(array); 
        arr.display(st);
        Instruction_Format ins = new Instruction_Format();
        array = ins.Extract(array, st);
        arr.display(array);
        HTMERecord htme = new HTMERecord();
        String[] HTMErec = htme.Extract(array);
        arr.display(HTMErec);
        
        
    }
    
}

