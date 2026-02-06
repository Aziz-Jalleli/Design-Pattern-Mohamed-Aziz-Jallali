public class SupprimerTacheCommande implements Commande {
    private GestionTaches gestionTaches;
    private String Description;
    public SupprimerTacheCommande(String Description, GestionTaches gestionTaches) {
        this.Description = Description;
        this.gestionTaches = gestionTaches;
    }
    @Override
    public void executer(){
        gestionTaches.deleteTache(Description);
    }
    @Override
    public void annuler(){
        gestionTaches.addTache(Description,"incomplete");
        System.out.println("[Annulation de suppersion : ");
        gestionTaches.afficher();
    }
}

