public class ApplicationGestionTaches {
    private GestionTaches gestionTaches;
    private GestionnaireCommandes gestionnaireCommandes;

    public ApplicationGestionTaches() {
        this.gestionTaches = new GestionTaches();
        this.gestionnaireCommandes = new GestionnaireCommandes();
    }

    public void creerTache(String description,String etat) {
        gestionnaireCommandes.executerCommande(new CreerTacheCommande(gestionTaches, description,etat));
    }

    public void modifierTache(String Description, String newDescription) {
        gestionnaireCommandes.executerCommande(new ModifierTacheCommande(gestionTaches, Description, newDescription));
    }

    public void supprimerTache(String description) {
        gestionnaireCommandes.executerCommande(new SupprimerTacheCommande(description,gestionTaches ));
    }

    public void annulerDerniereCommande() {
        gestionnaireCommandes.annulerDerniereCommande();
    }


    public void afficher() {
        gestionTaches.afficher();
    }
}
