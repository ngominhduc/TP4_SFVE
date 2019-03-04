// Based on a B specification from Marie-Laure Potet.

public class Explosives{
    public int nb_inc = 0;
    public String [][] incomp = new String[50][2];
    public int nb_assign = 0;
    public String [][] assign = new String[30][2];

 /*  L'indice d'insertion dans le tableau des incompatibilités est compris entre 0 et la taille maximale du tableau (50).*/
    /*@ public invariant // Prop 1
      @ (0 <= nb_inc && nb_inc < 50);
      @*/

    /*L'indice d'insertion dans le tableau des assignations est compris entre 0 et la taille maximale du tableau (30).*/
    /*@ public invariant // Prop 2
      @ (0 <= nb_assign && nb_assign < 30);
      @*/

    /*Tous les noms des produits renseignés dans le tableau des incompatibilités sont préfixés par "Prod".*/
    /*@ public invariant // Prop 3
      @ (\forall int i; 0 <= i && i < nb_inc;
      @         incomp[i][0].startsWith("Prod") && incomp[i][1].startsWith("Prod"));
      @*/

    /*Tous les noms des bâtiments et des produits renseignés dans le tableau des assignations sont préfixés respectivement "Bat" et par "Prod".*/
    /*@ public invariant // Prop 4
      @ (\forall int i; 0 <= i && i < nb_assign;
      @         assign[i][0].startsWith("Bat") && assign[i][1].startsWith("Prod"));
      @*/

    /*Un produit ne peut pas être renseigné comme étant incompatible avec lui même dans le tableau des incompatibilités.*/
    /*@ public invariant // Prop 5
      @ (\forall int i; 0 <= i && i < nb_inc; !(incomp[i][0]).equals(incomp[i][1]));
      @*/

    /*Chaque incompatibilité est renseignée pour les deux produits. Càd, chacun des deux produits est renseignés comme étant incompatible avec l'autre.*/
    /*@ public invariant // Prop 6
      @ (\forall int i; 0 <= i && i < nb_inc;
      @        (\exists int j; 0 <= j && j < nb_inc;
      @           (incomp[i][0]).equals(incomp[j][1])
      @              && (incomp[j][0]).equals(incomp[i][1])));
      @*/

    /*Un bâtiment ne contient jamais deux produits déclarés incompatibles dans le tableau des incompatibilités.*/
    /*@ public invariant // Prop 7
      @ (\forall int i; 0 <= i &&  i < nb_assign;
      @     (\forall int j; 0 <= j && j < nb_assign;
      @        (i != j && (assign[i][0]).equals(assign [j][0])) ==>
      @        (\forall int k; 0 <= k && k < nb_inc;
      @           (!(assign[i][1]).equals(incomp[k][0]))
      @              || (!(assign[j][1]).equals(incomp[k][1])))));
      @*/

      /* Un produit ne peut être stocké deux fois dans le même bâtiment */
     /*@ public invariant // Prop 8
       @ (\forall int i; 0 <= i &&  i < nb_assign;
       @	(\forall int j; 0 <= j &&  i < nb_assign;
       @		(i != j && (assign[i][0]).equals(assign [j][0]) ) ==> !(assign[i][1]).equals(assign [j][1]) ));
       @*/

     /* Un produit ne peut être stocké dans plus de trois bâtiments différents */
     /*@ public invariant // Prop 9
       @ (\forall int i; 0 <= i &&  i < nb_assign;
       @    (\num_of int j; 0 <= j &&  j < nb_assign; assign[i][1] == assign[j][1] ) <= 3);
       @*/

    /* Le nombre d'incompatibilités ne peut pas diminuer */
    /*@ public constraint // Prop 10
      @ \old(nb_inc) <= nb_inc;
      @*/


    //@ requires nb_inc < 48;
    //@ requires prod1.startsWith("Prod") && prod2.startsWith("Prod");
    //@ requires ! prod1.equals(prod2);
    public void add_incomp(String prod1, String prod2){
    	incomp[nb_inc][0] = prod1;
    	incomp[nb_inc][1] = prod2;
    	incomp[nb_inc+1][1] = prod1;
    	incomp[nb_inc+1][0] = prod2;
    	nb_inc = nb_inc+2;
    }

    //@ requires nb_assign < 29;
    //@ requires bat.startsWith("Bat") && prod.startsWith("Prod");
    //@ requires (\forall int i; 0 <= i && i < nb_assign; assign[i][0].equals(bat) ==> (\forall int j; 0 <= j && j < nb_inc; assign[i][1].equals(incomp[j][0]) ==> ! incomp[j][1].equals(prod) ) );
    public void add_assign(String bat, String prod){
    	assign[nb_assign][0] = bat;
    	assign[nb_assign][1] = prod;
    	nb_assign = nb_assign+1;
    }

    public void skip(){
    }

    //@ requires prod.startsWith("Prod");
    //@ ensures \result.startsWith("Bat");
    public String findBat(String prod) {
        
        for (int i = O; i < nb_assign; i++) {

        }
    }

    public boolean compatible(String prod1, String prod2) {
        for (int i = O; i < nb_inc; i++) {
            if (incomp[i][0].equals(prod1) && incomp[i][1].equals(prod2)) return false;
        }
        return true;
    }

}
