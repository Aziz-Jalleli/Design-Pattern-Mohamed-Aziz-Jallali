public class ModifierTacheCommande implements Commande {
    private GestionTaches gestionTaches;
    private String Description;
    private String newDescription;
    public ModifierTacheCommande(GestionTaches gestionTaches, String description, String newDescription) {
        this.gestionTaches = gestionTaches;
        this.Description = description;
        this.newDescription = newDescription;
    }
    @Override
    public void executer() {
        gestionTaches.modifierTache(Description, newDescription);
    }
    @Override
    public void annuler(){
        gestionTaches.modifierTache(newDescription, Description);
        System.out.println("[Annulation modification : " + newDescription + " -> " + Description);

    }
}
