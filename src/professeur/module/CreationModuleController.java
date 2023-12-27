package professeur.module;

import java.net.URL;
import java.util.ResourceBundle;

import Basededonne.database;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import professeur.professeurController;

public class CreationModuleController implements Initializable {

	@FXML
	private Button update;
	@FXML
	private TextField tfcodeModule;
	@FXML
	private Text lab_varier;
	@FXML
	private ComboBox<String> comboBoxSemistre;
	@FXML
	private TextField tfnomModule;
	public String cinNumero;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.comboBoxSemistre.setItems(FXCollections.observableArrayList("Semistre 1","Semistre 2","Semistre 3","Semistre 4","Semistre 5"));
	}

	@FXML
	private void enregistrer_module()
	{
		database data=new database();
        // Logique d'inscription
        String nom_module = this.tfnomModule.getText();
        String code_module = this.tfcodeModule.getText();
        String semistre = comboBoxSemistre.getValue();
        String cin=this.cinNumero;
        if (( data.enregistrerModule(nom_module,semistre,cin,code_module))) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Ajoute");
			alert.setHeaderText("Ajout du modèle avec succès" + cin);
			alert.showAndWait();
			
			
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Erreur");
			alert.showAndWait();
		}

	}

	public void setCinNumero(String cinNumero)
	{
		this.cinNumero=cinNumero;
	}
}
