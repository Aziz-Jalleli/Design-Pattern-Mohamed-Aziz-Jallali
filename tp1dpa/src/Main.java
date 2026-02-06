public class Main {
    public static void main(String[] args) {
        ApplicationGestionTaches a = new ApplicationGestionTaches();
        a.creerTache("t1","incompete");
        a.creerTache("t2","incompete");
        a.creerTache("t3","incompete");
        a.afficher();
        System.out.println("modification ============n");
        a.modifierTache("t1", "t4");
        a.afficher();
        System.out.println("Suppressio ============n");
        a.supprimerTache("t2");
        a.afficher();
        System.out.println("annulation ============n");
        a.annulerDerniereCommande();
        
    }
}