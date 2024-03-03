/**
 * -----------------------------------
 *    Dev2 TP5 : Calculatrice & EPF
 * -----------------------------------
 * 
 * @date Mercredi 13 décembre
 * 
 * @author Victor Jockin
 * 
 * @groupeTD 3
 * @groupeTP A
 * 
 */

public class Pile {

    // déclaration des champs
    int[] elements ;            // elements de la pile
    int indiceSommet ;          // indice du sommet de la pile

    // constructeur sans paramètres
    Pile ( ) {
        this.elements = new int[100] ;
        this.indiceSommet = -1 ;
    }

    // constructeur à un paramètre
    Pile (int pfTaille) {
        this.elements = new int[pfTaille] ;
        this.indiceSommet = -1 ;
    }

}