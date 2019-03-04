import static org.junit.Assert.*;
import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestExplosivesJUnit4 {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;

    Explosives e = new Explosives();

    public static void main(String args[]) {
    	String testClass = "TestExplosivesJUnit4";
     	org.junit.runner.JUnitCore.main(testClass);
     }


    private void handleJMLAssertionError(JmlAssertionError e) {
    	if (e.getClass().equals(JmlAssertionError.PreconditionEntry.class)) {
    	    System.out.println("\n INCONCLUSIVE "+(new Exception().getStackTrace()[1].getMethodName())+ "\n\t "+ e.getMessage());
            nb_inconclusive++;}
    else{
	    // test failure	
	    nb_fail++;
	    fail("\n\t" + e.getMessage());	
		}  
    }
	
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nb_inconclusive = 0;
		nb_fail = 0;
		org.jmlspecs.utils.Utils.useExceptions = true;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	     System.out.println("\n inconclusive tests: "+nb_inconclusive+" -- failures : "+nb_fail );
	}
	
	@Test
	public void  testSequence_0() {
		try{
			
			//e.add_incomp("Prod_Nitro","Prod_Glycerine");
			//e.add_incomp("Prod_Dyna","Prod_Mite");
			//e.add_assign("Bat_1","Prod_Dyna");
			//e.add_assign("Bat_1","Prod_Nitro");
			//e.add_assign("Bat_2","Prod_Mite");
			//e.add_assign("Bat_1","Prod_Glycerine");
			
			//invalide propriete 1
			// e.add_imcomp > 25 fois
			
			//invalide propriete 2
			// e.add_assign > 30 fois
			
			//invalide propriete 3
			//e.add_incomp("Bat_1","Prod_Glycerine");
			
			//invaldie propriete 4
			//e.add_assign("Prod_Dyna","Prod_Dyna");
			
			//invalide propriete 5
			//e.add_incomp("Prod_Glycerine","Prod_Glycerine");
			
			//invalide propriete 6
			
			//invalide propriete 7
			//e.add_incomp("Prod_Nitro","Prod_Glycerine");
			//e.add_assign("Bat_1","Prod_Nitro");
			//e.add_assign("Bat_1","Prod_Glycerine");
			
			//invalide propriete 8
			//e.add_assign("Bat_1","Prod_Nitro");
			//e.add_assign("Bat_1","Prod_Nitro");
			
			//invalide propriete 9
			e.add_assign("Bat_1","Prod_Nitro");
			e.add_assign("Bat_2","Prod_Nitro");
			e.add_assign("Bat_3","Prod_Nitro");
			e.add_assign("Bat_4","Prod_Nitro");		
			
			//test question 4
			//e.add_incomp("Prod_Nitro","Prod_Glycerine");
			//e.add_assign("Bat_1","Prod_Nitro");
			//e.add_assign("Bat_2","Prod_2");
			//e.findBat("");
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}

	
	
}
