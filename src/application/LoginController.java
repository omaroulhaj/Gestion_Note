package application;

import java.io.IOException;

import Basededonne.database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField tfemail;
	@FXML
	private PasswordField tfpassword;
	@FXML
	private Button login;
	@FXML
	private VBox rootVBox;
	@FXML
	private HBox loginBox;
	@FXML
	private Hyperlink create_compte;
	public static String cin;
	public static String username;

	@FXML
	private void initialize()
	{
	}

	@FXML
	private void Login() {

		// Logique de gestion de la connexion
		String email = tfemail.getText();
		String password = tfpassword.getText();
		// Ajoutez votre logique de connexion ici
		database data = new database();
		if ((data.login_data(email, password)) && (email != "" && password != "")) {
			data.type_utilisateurSQL(email);
			LoginController.username = data.nom;
			LoginController.cin=data.cin;
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Login");
			alert.setHeaderText("Login avec succès");
			alert.showAndWait();
			this.to_application(data.type_utilisateurSQL(email));
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Utilisateur n'a pas pu inscrit");
			alert.showAndWait();
		}

	}

	@FXML
	private void Creat_compte() {
		try {
			// Charger la nouvelle FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/inscrire/signup.fxml"));
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

	private void to_application(String type_utilisateur) {
		try {
			Stage stage = new Stage();
			if ("Administrateur".equals(type_utilisateur)) {
				// Charger la nouvelle FXML
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/mainapp.fxml"));
				Parent root = loader.load();
				// Créer une nouvelle scène
				Scene scene = new Scene(root);
				// Créer une nouvelle fenêtre (Stage)
				stage.setScene(scene);
				stage.setTitle("Votre Nouvelle Fenêtre");
				stage.setResizable(true);

			} else if ("Professeur".equals(type_utilisateur)) {
				// Charger la nouvelle FXML
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/professeur/dashboard_professeur.fxml"));
				Parent root = loader.load();
				// Créer une nouvelle scène
				Scene scene = new Scene(root);
				// Créer une nouvelle fenêtre (Stage)
				stage.setScene(scene);
				stage.setTitle("Votre Nouvelle Fenêtre");
				stage.setResizable(true);

			}
			// Fermer la fenêtre actuelle
			Stage currentStage = (Stage) login.getScene().getWindow();
			currentStage.close();
			// Montrer la nouvelle fenêtre
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
