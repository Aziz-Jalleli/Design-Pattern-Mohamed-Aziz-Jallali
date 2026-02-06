public class CreerTacheCommande implements Commande {
    private GestionTaches gtache;
    private String description;
    private String etat;
    public CreerTacheCommande(GestionTaches gestionTaches, String description,String etat) {
        this.gtache = gestionTaches;
        this.description = description;
        this.etat = etat;
    }
    @Override
    public void annuler() {
        gtache.deleteTache(description);
        System.out.println("[Annulation de creation : ");
    }
    @Override
    public void executer() {
        gtache.addTache(description,etat);
    }
}
