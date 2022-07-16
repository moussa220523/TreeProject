import javax.print.DocFlavor.STRING;

abstract public class Composant {

    public abstract void afficher(int nbreIndent);

    public void ajouter(Composant c) {
    }

    public Composant(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    private String nom;

}
