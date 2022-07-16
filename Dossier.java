import java.util.ArrayList;

public class Dossier extends Composant {

    private ArrayList<Composant> composants = new ArrayList<Composant>();

    public Dossier(String nom) {
        super(nom);
    }

    public void ajouter(Composant composant) {
        this.composants.add(composant);
    }

    public void afficher(int nbreIndent) {

        System.out.print("│" + ("└───" + getNom()).indent(nbreIndent));

        for (Composant c : composants) {

            if (c instanceof Dossier) {

                c.afficher(nbreIndent + 4);

            } else {

                c.afficher(nbreIndent + 4);
            }
        }
    }
}

// System.out.print(("└───").indent(nbreIndent));
// System.out.print(("│").indent(nbreIndent + 4));
