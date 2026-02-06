public class Tache {
    private String description;
    private String etat;
    public Tache(String description, String etat) {
        this.description = description;
        this.etat = etat;
    }

    public Tache() {
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
}
