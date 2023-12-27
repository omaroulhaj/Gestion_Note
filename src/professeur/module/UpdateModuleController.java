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

public class UpdateModuleController implements Initializable {

	@FXML
	private Button enregistrer;
	@FXML
	private TextField tfcodeModule;
	@FXML
	private Text lab_varier;
	@FXML
	private ComboBox<String> comboBoxSemistre;
	@FXML
	private TextField tfnomModule;
	public String cinNumero;
	public String codeancienne;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.comboBoxSemistre.setItems(FXCollections.observableArrayList("Semistre 1","Semistre 2","Semistre 3","Semistre 4","Semistre 5"));
	}

	@FXML
	private void update_module()
	{
		database data=new database();
        // Logique d'inscription
        String nom_module = this.tfnomModule.getText();
        String code_module = this.tfcodeModule.getText();
        String semistre = comboBoxSemistre.getValue();
        String cin=this.cinNumero;
        
        if (( data.updateModule(nom_module,semistre,this.codeancienne,code_module,professeurController.cinNumero))) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Update");
			alert.setHeaderText("apdate du modèle avec succès" + professeurController.cinNumero);
			alert.showAndWait();
			professeurController c=new professeurController();
			c.toAjoutModule();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Erreur");
			alert.showAndWait();
		}

	}

	

	public void setValues(String id_module, String nom_module, String semistre) {
		// TODO Auto-generated method stub
		this.tfnomModule.setText(nom_module);
		this.tfcodeModule.setText(id_module);
		this.comboBoxSemistre.setValue(semistre);
		this.codeancienne=id_module;
	
	}

	
}
