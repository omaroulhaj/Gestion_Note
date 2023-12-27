package inscrire;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Basededonne.database;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController implements Initializable {
	@FXML
	private TextField tfnom;
	@FXML
	private TextField tfprenom;
	@FXML
	private TextField tfcin;
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private PasswordField tfpassword;
	@FXML
	private TextField tfemail;
	@FXML
	private Button signup;
	@FXML
	private Hyperlink login;
	@FXML
	private void signup() {
		database data = new database();
		// Logique d'inscription
		String nom = tfnom.getText();
		String prenom = tfprenom.getText();
		String password = tfpassword.getText();
		String email = tfemail.getText();
		String cin = tfcin.getText();
		String type = comboBox.getValue();
		if ((data.enregistrer(nom, prenom, email, password, cin, type))
				&& (nom != "" && prenom != "" && email != "" && password != "" && cin != "" && type != "")) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Inscription");
			alert.setHeaderText("Utilisateur a inscrit avec succès");
			alert.showAndWait();
			this.Login();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Utilisateur n'a pas pu inscrit");
			alert.showAndWait();
		}

	}

	@FXML
	private void Login() {
		// Logique de redirection vers l'écran de connexion
		System.out.println("Redirection vers l'écran de connexion");
		try {
			// Charger la nouvelle FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MainScene.fxml"));
			Parent root = loader.load();

			// Créer une nouvelle scène
			Scene scene = new Scene(root);

			// Créer une nouvelle fenêtre (Stage)
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Votre Nouvelle Fenêtre");
			// Fermer la fenêtre actuelle
			Stage currentStage = (Stage) login.getScene().getWindow();
			currentStage.close();

			// Montrer la nouvelle fenêtre
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.comboBox.setItems(FXCollections.observableArrayList("Professeur", "Administrateur"));
	}

}
