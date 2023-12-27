package professeur;

public class Module {

	String id_module, nom, semistre;

	public void setId_module(String id) {
		this.id_module = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setSemistre(String semistre) {
		this.semistre = semistre;
	}

	public String getSemistre() {
		return this.semistre;
	}

	public String getNom() {
		return nom;
	}

	public String getId_module() {
		return this.id_module;
	}

	public Module(String id, String nom, String Semistre) {
		this.id_module = id;
		this.nom = nom;
		this.semistre = Semistre;

	}
}

