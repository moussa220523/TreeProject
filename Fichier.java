public class Fichier extends Composant {
	public Fichier(String nom) {
		super(nom);
	}

	public void afficher(int nbreIndent) {
		System.out.print("│" + ("├───" + getNom()).indent(nbreIndent));
	}
}

// System.out.print(("│").indent(nbreIndent));
// System.out.print(("│").indent(nbreIndent - 4));
// System.out.print(("│").indent(nbreIndent));
// System.out.print(("└───").indent(nbreIndent));