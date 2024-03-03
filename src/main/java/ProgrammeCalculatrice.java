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

public class ProgrammeCalculatrice {


    public static void main(String[] args) throws Exception {


        // déclarations et initialisation des variables
        boolean erreur = false ;                    // indique si une erreur a été détectée ou non

        int nbSymbolesExpression = args.length ;    // nombre de symboles en paramètres
        int operandeCourante ;                      // operande courante analysée
        int opACourante ;                           // première opérande utilisée dans le calcul courant
        int opBCourante ;                           // deuxième opérande utilisée dans le calcul courant
        int resultatOperationCourante ;             // resultat du calcul courant

        String[] operateurs = {"+", "-", "*", "/"} ;
        String[] operandes = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"} ;


        // création de la pile contenant les symboles
        Pile expression = new Pile(nbSymbolesExpression) ;
        // Il y a maximum nbSymbolesExpressions entier à stocker


        // analyse des symboles (args)
        int i = 0 ;
        while (i<nbSymbolesExpression && erreur==false) {
            if (estUnOperateur(operateurs, args[i])) {
                if (contientPlusieursElements(expression)) {

                    // Première opérande dépilée
                    opACourante = expression.elements[expression.indiceSommet] ;
                    depiler(expression) ;

                    // Deuxième opérande dépilée
                    opBCourante = expression.elements[expression.indiceSommet] ;
                    depiler(expression) ;

                    // Résultat de l'opération empilé
                    resultatOperationCourante = operation(opACourante, opBCourante, args[i]) ;
                    empiler(expression, resultatOperationCourante) ;

                } else {
                    // une erreur a été détectée :
                    // il n'y a pas assez d'éléments (au moins 2)
                    // pour effectuer une opération
                    erreur = true ;
                }
            } else {
                if (estUneOperande(operandes, args[i])) {
                    operandeCourante = Integer.parseInt(args[i]) ;
                    empiler(expression, operandeCourante) ;
                } else {
                    // une erreur a été détectée :
                    // le symbole courant n'est pas reconnu
                    erreur = true ;
                }
            }
            i++ ;
        }


        // affichages
        afficherTitre("CALCULATRICE") ;
        sautDeLignes(1) ;
        System.out.println("Nombre de paramètres : " + args.length) ;
        sautDeLignes(1) ;
        if (erreur || contientPlusieursElements(expression)) {
            // une erreur a été détectée
            System.out.println("Erreur détectée") ;
        } else {
            if (nbSymbolesExpression == 0) {
                // aucun calcul à traiter
                System.out.println("Aucun calcul à effectuer") ;
            } else {
                // aucune erreur n'a été détectée
                System.out.println("Aucune erreur détectée") ;
                sautDeLignes(1) ;
                System.out.println("Résultat : " + expression.elements[expression.indiceSommet]) ;
            }
        }
        // saut de ligne pour cause de maniaquerie
        sautDeLignes(1) ;


    }




    /** 
     * -------------------------------
     *    FONCTION : estUnOperateur
     * -------------------------------
     * 
     * Indique si pfSymbole est un opérateur ou non
     *
     * @param pfOperateurs  IN  :       liste des opérateurs reconnus
     * @param pfSymbole     IN  :       symbole à analyser
     * 
     * @return  VRAI si pfSymbole est un opérateur,
     *          FAUX sinon
     * 
     */
    public static boolean estUnOperateur(String[] pfOperateurs, String pfSymbole) {
        for (int i = 0 ; i < pfOperateurs.length ; i++) {
            if (pfSymbole.compareTo(pfOperateurs[i]) == 0) {
                return true ;
            }
        }
        return false ;
    }


    /** 
     * -------------------------------
     *    FONCTION : estUneOperande
     * -------------------------------
     * 
     * Indique si pfSymbole est une opérande ou non
     *
     * @param pfOperandes   IN  :       liste des opérandes reconnues
     * @param pfSymbole     IN  :       symbole à analyser
     * 
     * @return  VRAI si pfSymbole est une opérande,
     *          FAUX sinon
     * 
     */
    public static boolean estUneOperande(String[] pfOperandes, String pfSymbole) {
        for (int i = 0 ; i < pfOperandes.length ; i++) {
            if (pfSymbole.compareTo(pfOperandes[i]) == 0) {
                return true ;
            }
        }
        return false ;
    }


    /** 
     * --------------------------
     *    FONCTION : operation
     * --------------------------
     * 
     * Retourne le résultat d'une opération entre les opérandes
     * pfOpA et pfOpB en fonction de l'opérateur pfOperateur
     *
     * @param pfOpA         IN  :       opérande A
     * @param pfOpB         IN  :       opérande B
     * @param pfOperateur   IN  :       opétateur à appliquer
     * 
     * @return  le résultat de l'opération
     * 
     */
    public static int operation(int pfOpA, int pfOpB, String pfOperateur) {
        int resultat = 0 ;
        if (pfOperateur.compareTo("+") == 0) {
            resultat = pfOpA + pfOpB ;
        }
        if (pfOperateur.compareTo("-") == 0) {
            // pfOpB est située avant pfOpA
            // on soustrait donc pfOpA à pfOpB
            resultat = pfOpB - pfOpA ;
            // on accepte les valeurs négatives dans les résultats
        }
        if (pfOperateur.compareTo("*") == 0) {
            resultat = pfOpA * pfOpB ;
        }
        if (pfOperateur.compareTo("/") == 0) {
            resultat = pfOpA / pfOpB ;
        }
        return resultat ;
    }




    /** 
     * --------------------------
     *    FONCTION : creerPile
     * --------------------------
     * 
     * Retourne une pile vide
     *
     * @return  une pile vide
     * 
     */
    public static Pile creerPile( ) {
        Pile p = new Pile( ) ;
        return p ;
    }


    /** 
     * ------------------------
     *    FONCTION : estVide
     * ------------------------
     * 
     * Indique si pfPile est vide ou non
     *
     * @param pfPile    IN  :       pile à traiter
     * 
     * @return  Vrai si pfPile est vide, Faux sinon
     * 
     */
    public static boolean estVide(Pile pfPile) {
        boolean vide = false ;
        if (pfPile.indiceSommet == -1) {
            vide = true ;
        }
        return vide ;
    }


    /** 
     * ------------------------------------------
     *    FONCTION : contientPlusieursElements
     * ------------------------------------------
     * 
     * Indique si pfPile contient plusieurs éléments
     *
     * @param pfPile    IN  :       pile à traiter
     * 
     * @return  Vrai si pfPile contient au moins 2 éléments, Faux sinon
     * 
     */
    public static boolean contientPlusieursElements(Pile pfPile) {
        boolean plusieursElts = false ;
        if (pfPile.indiceSommet > 0) {
            plusieursElts = true ;
        }
        return plusieursElts ;
    }


    /** 
     * --------------------------
     *    FONCTION : estPleine
     * --------------------------
     * 
     * Indique si pfPile est pleine ou non
     *
     * @param pfPile    IN  :       pile à traiter
     * 
     * @return  Vrai si pfPile est pleine, Faux sinon
     * 
     */
    public static boolean estPleine(Pile pfPile) {
        boolean pleine = false ;
        if (pfPile.indiceSommet == pfPile.elements.length) {
            pleine = true ;
        }
        return pleine ;
    }


    /** 
     * -------------------------
     *    PROCÉDURE : empiler
     * -------------------------
     * 
     * Empile pfElement dans pfPile
     *
     * @param pfPile        IN/OUT  :       pile à traiter
     * @param pfElement     IN      :       element à empiler
     * 
     * @throws  Exception ssi estPleine(pfPile)
     * 
     */
    public static void empiler(Pile pfPile, int pfElement)
    throws Exception {
        if (estPleine(pfPile) == true) {
            throw new Exception("ERREUR : Impossible d'empiler sur une pile pleine.") ;
        } else {
            pfPile.elements[pfPile.indiceSommet + 1] = pfElement ;
            pfPile.indiceSommet++ ;
        }
    }


    /** 
     * -------------------------
     *    PROCÉDURE : depiler
     * -------------------------
     * 
     * Dépile pfPile
     *
     * @param pfPile        IN/OUT  :       pile à traiter
     * 
     * @throws  Exception ssi estVide(pfPile)
     * 
     */
    public static void depiler(Pile pfPile) throws Exception {
        if (estVide(pfPile) == true) {
            throw new Exception("ERREUR : Impossible de dépiler une pile vide.") ;
        } else {
            pfPile.indiceSommet-- ;
        }
    }


    /** 
     * -----------------------
     *    FONCTION : sommet
     * -----------------------
     * 
     * Retourne le sommet de pfPile
     *
     * @param pfPile        IN/OUT  :       pile à traiter
     * 
     * @return  le sommet de la pile
     * 
     * @throws  Exception ssi estVide(pfPile)
     * 
     */
    public static int sommet(Pile pfPile) throws Exception {
        if (estVide(pfPile) == true) {
            throw new Exception("ERREUR : Impossible de dépiler une pile vide.") ;
        } else {
            return pfPile.elements[pfPile.indiceSommet] ;
        }
    }


    /** 
     * -------------------------
     *    FONCTION : toString
     * -------------------------
     * 
     * Retourne l'état courant de pfPile
     *
     * @param pfPile        IN  :       pile à traiter
     * 
     * @return  la chaine de caractère décrivant l'état courant
     *          de pfPile
     * 
     */
    public static String toString(Pile pfPile) {
        String etatPile = "" ;
        for (int i = 0 ; i<=pfPile.indiceSommet ; i++) {
            etatPile = etatPile + "|" + pfPile.elements[i];
        }
        etatPile = etatPile + "|vide" ;
        return etatPile ;
    }


    /** 
     * ---------------------------
     *    FONCTION : nbElements
     * ---------------------------
     * 
     * Retourne le nombre d'éléments courant de pfPile
     *
     * @param pfPile        IN  :       pile à traiter
     * 
     * @return  le nombre d'éléments courant de pfPile (entier)
     * 
     */
    public static int nbElements(Pile pfPile) {
        int nbElt = pfPile.indiceSommet + 1 ;
        return nbElt ;
    }




    /* 
     * --------------------------------
     *    AFFICHAGE ET MISE EN FORME
     * --------------------------------
     * 
     */

    /**
     * -------------------------------
     *    Procédure : afficherTitre
     * -------------------------------
     * 
     * @author Victor Jockin
     *
     * Affiche la mise en forme du titre donné en paramètre
     * 
     * @param pfTitre       IN  :   titre à mettre en forme et à afficher
     * @param pfTabulation  IN  :   tabulation (nombre d'espaces) avant le titre
     * 
    **/
    public static void afficherTitre(String pfTitre) {
        String ligneH = ligneHorizontale(pfTitre.length() + 2 * 3, "-") ;
        String tabulation = ligneHorizontale(3, " ") ;
        System.out.println(ligneH + "\n" + tabulation + pfTitre + "\n" + ligneH) ;
    }

    /**
     * ------------------------------
     *    Procédure : sautDeLignes
     * ------------------------------
     * 
     * @author Victor Jockin
     *
     * Affiche un nombre donné de sauts de lignes
     * 
     * @param pfNbSautsDeLignes IN  :   nombre de sauts de lignes à afficher
     * 
    **/
    public static void sautDeLignes(int pfNbSautsDeLignes) {
        for (int i = 0 ; i<pfNbSautsDeLignes ; i++) {
            System.out.println("") ;
        }
    }

    /**
     * ---------------------------------
     *    Fonction : ligneHorizontale
     * ---------------------------------
     * 
     * @author Victor Jockin
     *
     * Retourne une ligne horizontale :
     * - Avec une longueur donnée
     * - Construite à partir d'un caractère donné
     * 
     * @param pfLongueurLigne   IN  :   longueur de la ligne à construire
     * @param pfCaractere       IN  :   caractère utilisé pour construire la ligne
     * 
     * @return la ligne horizontale
     * 
    **/
    public static String ligneHorizontale(int pfLongueurLigne, String pfCaractere) {
        String ligneHorizontale = "" ;
        for (int i = 0 ; i<pfLongueurLigne ; i++) {
            ligneHorizontale = ligneHorizontale + pfCaractere ;
        }
        return ligneHorizontale ;
    }


}