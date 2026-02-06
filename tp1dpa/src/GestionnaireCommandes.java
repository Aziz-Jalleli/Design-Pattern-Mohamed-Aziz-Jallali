import java.util.ArrayList;
import java.util.List;

public class GestionnaireCommandes {
    private List<Commande> historique = new ArrayList<>();
    private int index = -1;

    public void executerCommande(Commande c) {
        while (historique.size() > index + 1) {
            historique.remove(historique.size() - 1);
        }

        c.executer();
        historique.add(c);
        index++;
    }

    public void annulerDerniereCommande() {
        if (index >= 0) {
            Commande c = historique.get(index);
            c.annuler();
            index--;
        } else {
            System.out.println("[UNDO] Rien à annuler !");
        }
    }
}

