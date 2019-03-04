import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestExplosivesJUnit4Public {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;

    Explosives e;

    public static void main(String args[]) {
    	String testClass = "TestExplosivesJUnit4Public";
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


    /*Chaque incompatibilité est renseignée pour les deux produits. Càd, chacun des deux produits est renseignés comme étant incompatible avec l'autre.*/
    /* public invariant // Prop 6
       (\forall int i; 0 <= i && i < nb_inc;
              (\exists int j; 0 <= j && j < nb_inc;
                 (incomp[i][0]).equals(incomp[j][1])
                    && (incomp[j][0]).equals(incomp[i][1])));
      */
     @Test
     public void  testProp6() {
        try{
            e=new Explosives();
            e.incomp[0][0] = "Prod_1";
        	e.incomp[0][1] = "Prod_2";
            e.nb_inc++;
            e.skip();
        } 	catch(JmlAssertionError e){
                handleJMLAssertionError(e);
        }
     }
}
