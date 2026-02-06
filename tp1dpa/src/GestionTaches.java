import java.util.ArrayList;
import java.util.List;

public class GestionTaches {
    private List<Tache> taches;
    public GestionTaches() {
        taches = new ArrayList<Tache>();
    }
    public void addTache(String description,String etat) {
        Tache t = new Tache(description,etat);
        taches.add(t);
    }
    public Tache getTache(String description) {
        for(Tache t : taches) {
            if(t.getDescription() == description ) {
                return t;
            }
        }
        return null;
    }
    public List<Tache> getTaches() {
        return taches;
    }

    public void deleteTache(String description) {
        for(Tache t : taches) {
            if(t.getDescription() == description ) {
                taches.remove(t);
            }
        }
    }
    public void modifierTache(String description,String newDescription) {
        for(Tache t : taches) {
            if(t.getDescription() == description) {
                t.setDescription(newDescription);
            }
        }
    }
    public void afficher(){
        for(Tache t : taches) {
            System.out.println(t.getDescription());
        }
    }
}
