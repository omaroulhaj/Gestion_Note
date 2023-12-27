package admin;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class mainController implements Initializable {
 
    @FXML
    private Hyperlink nav_GESTION;

    @FXML
    private Hyperlink nav_acceuil;

    @FXML
    private Hyperlink nav_Consultation;

    @FXML
    private Hyperlink hyper_logout;

    @FXML
    private Text lab_varier;


    // Add any additional methods or event handlers here

    @FXML
    private void toGestion() {
        // Handle the toGestion action
    }

    @FXML
    private void to_acceuil() {
        // Handle the to_acceuil action
    }

    @FXML
    private void to_logout() {
        // Handle the to_logout action
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("Êtes-vous sûr de vouloir vous déconnecter?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked OK, proceed with logout
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
                Stage currentStage = (Stage) hyper_logout.getScene().getWindow();
                currentStage.close();

                // Montrer la nouvelle fenêtre
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // If the user clicks Cancel, do nothing
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	 if (lab_varier != null) {
 	        lab_varier.setText(LoginController.username);
 	    } else {
 	        System.err.println("lab_varier is null. Check the FXML file.");
 	    }
    }

    @FXML
    private void toConsultation() {
        // Handle the toConsultation action
    }

    @FXML
    private void toReleve() {
        // Handle the toReleve action
    }



}
