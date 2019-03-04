import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestExplosivesJUnit4 {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;

    Explosives e;

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
    public void  testProp1() {
    	try{
    		e=new Explosives();
            for (int i = 0; i < 25; i++) {
                e.add_incomp("Prod_Nitro","Prod_Glycerine");
            }
    		e.add_incomp("Prod_Dyna","Prod_Mite");
    	} 	catch(JmlAssertionError e){
    			handleJMLAssertionError(e);
    	}
    }

       /*L'indice d'insertion dans le tableau des assignations est compris entre 0 et la taille maximale du tableau (30).*/
       /* public invariant // Prop 2
          (0 <= nb_assign && nb_assign < 30);
         */

     @Test
     public void  testProp2() {
     	try{
     		e=new Explosives();
            for (int i = 0; i < 30; i++) {
                e.add_assign("Bat_prop2","Prod_Mite"+i);
            }
    		e.add_assign("Bat_prop2","Prod_Mite");
     	} 	catch(JmlAssertionError e){
     			handleJMLAssertionError(e);
     	}
     }

       /*Tous les noms des produits renseignés dans le tableau des incompatibilités sont préfixés par "Prod".*/
       /* public invariant // Prop 3
          (\forall int i; 0 <= i && i < nb_inc;
                  incomp[i][0].startsWith("Prod") && incomp[i][1].startsWith("Prod"));
         */
         @Test
         public void  testProp3() {
            try{
                e=new Explosives();
                e.add_incomp("Prod_Dyna","Mite");
            } 	catch(JmlAssertionError e){
                    handleJMLAssertionError(e);
            }
         }
       /*Tous les noms des bâtiments et des produits renseignés dans le tableau des assignations sont préfixés respectivement "Bat" et par "Prod".*/
       /* public invariant // Prop 4
          (\forall int i; 0 <= i && i < nb_assign;
                  assign[i][0].startsWith("Bat") && assign[i][1].startsWith("Prod"));
         */
         @Test
         public void  testProp4() {
            try{
                e=new Explosives();
                e.add_assign("prop4","Mite");
            } 	catch(JmlAssertionError e){
                    handleJMLAssertionError(e);
            }
         }
       /*Un produit ne peut pas être renseigné comme étant incompatible avec lui même dans le tableau des incompatibilités.*/
       /* public invariant // Prop 5
          (\forall int i; 0 <= i && i < nb_inc; !(incomp[i][0]).equals(incomp[i][1]));
         */
         @Test
         public void  testProp5() {
            try{
                e=new Explosives();
                e.add_incomp("Prod_Dyna","Prod_Dyna");
            } 	catch(JmlAssertionError e){
                    handleJMLAssertionError(e);
            }
         }

         /*Un bâtiment ne contient jamais deux produits déclarés incompatibles dans le tableau des incompatibilités.*/
         /* public invariant // Prop 7
            (\forall int i; 0 <= i &&  i < nb_assign;
                (\forall int j; 0 <= j && j < nb_assign;
                   (i != j && (assign[i][0]).equals(assign [j][0])) ==>
                   (\forall int k; 0 <= k && k < nb_inc;
                      (!(assign[i][1]).equals(incomp[k][0]))
                         || (!(assign[j][1]).equals(incomp[k][1])))));
           */
         @Test
         public void  testProp7() {
          try{
              e=new Explosives();
              e.add_incomp("Prod_Nitro","Prod_Glycerine");
              e.add_assign("Bat_1", "Prod_Nitro");
              e.add_assign("Bat_1", "Prod_Glycerine");
          } 	catch(JmlAssertionError e){
                  handleJMLAssertionError(e);
          }
         }
}
