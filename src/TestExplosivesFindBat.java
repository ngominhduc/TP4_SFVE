import static org.junit.Assert.fail;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestExplosivesFindBat {
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
			e.add_incomp("Prod_Nitro","Prod_Glycerine");
			e.add_assign("Bat_1","Prod_Nitro");
			
			//invalide pre-condition
			//e.findBat("toto");
			
			//invalide post-condition
			//e.findBat("Prod_Nitro");
			
			//e.add_assign("Bat_2","Prod_2");
			e.add_assign("Bat_2","Prod_5");
			e.add_assign("Bat_3","Prod_3");
			e.add_assign("Bat_4","Prod_4");
			e.findBat("Prod_Nitro");
			
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}
}
