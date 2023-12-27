package professeur;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Basededonne.database;
import application.LoginController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import professeur.module.CreationModuleController;
import professeur.module.UpdateModuleController;
public class professeurController implements Initializable {



	
	@FXML
	private Hyperlink hyper_logout;
	@FXML
	private Text lab_varier;
	@FXML
	private Hyperlink nav_module;
	@FXML
	private Hyperlink nav_acceuil;
	@FXML
	private Hyperlink nav_Consultation;
	@FXML
	private Hyperlink ajouter;
	public static String cinNumero;
    @FXML
    private TableColumn<Module, String> ColonneNom;
    @FXML
    private TableColumn<Module, String> colonneId;
    @FXML
    private TableColumn<Module, String> colonneSemistre;
    @FXML
    private TableColumn<Module, Void> delete;
    @FXML
    private TableView<Module> tableModule;
    @FXML
    private TableColumn<Module, Void> update;
    ObservableList<Module> listM;
    ObservableList<Module> dataList;







    //Navigation entre les fenetres

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
				// Obtenir la fenêtre actuelle
				Stage currentStage = (Stage) hyper_logout.getScene().getWindow();
				// Remplacer la scène actuelle par la nouvelle scène
				currentStage.setScene(scene);
				currentStage.setTitle("Votre Nouvelle Fenêtre");
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void to_module() {
	    try
	    {
	        // Load the new FXML file
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/professeur/module/module.fxml"));
	        Parent root = loader.load();

	        // Create a new Scene
	        Scene scene = new Scene(root);
	        // Create a new Stage
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.setTitle("Votre Nouvelle Fenêtre");
	        // Close the current window (Stage)
	        Stage currentStage = (Stage) nav_module.getScene().getWindow();
	        currentStage.close();
	        // Show the new window (Stage)
	        stage.show();
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	}
	@FXML
	private void to_acceuil()
	{
		try
		{
			// Charger la nouvelle FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/professeur/dashboard_professeur.fxml"));
			Parent root = loader.load();
			// Créer une nouvelle scène
			Scene scene = new Scene(root);
			// Créer une nouvelle fenêtre (Stage)
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Votre Nouvelle Fenêtre");
			// Fermer la fenêtre actuelle
			Stage currentStage = (Stage) nav_acceuil.getScene().getWindow();
			currentStage.close();
			// Montrer la nouvelle fenêtre
			stage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	public void toAjoutModule()
	{
		try {
			// Charger la nouvelle FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/professeur/module/CreationModule.fxml"));
			Parent root = loader.load();
			// Créer une nouvelle scène
			Scene scene = new Scene(root);
			// Créer une nouvelle fenêtre (Stage)
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Votre Nouvelle Fenêtre");
			CreationModuleController controller = loader.getController();
	        controller.setCinNumero(professeurController.cinNumero);
			// Fermer la fenêtre actuelle
			Stage currentStage = (Stage) ajouter.getScene().getWindow();
			currentStage.close();
			// Montrer la nouvelle fenêtre
			stage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void to_update(String id_module,String nom_module,String semistre) {
		// TODO Auto-generated method stub
		try {
			// Charger la nouvelle FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/professeur/module/UpdateModule.fxml"));
			Parent root = loader.load();
			// Créer une nouvelle scène
			Scene scene = new Scene(root);
			// Créer une nouvelle fenêtre (Stage)
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Votre Nouvelle Fenêtre");
			UpdateModuleController controller = loader.getController();
	        controller.setValues(id_module,nom_module,semistre);
			// Fermer la fenêtre actuelle
			Stage currentStage = (Stage) ajouter.getScene().getWindow();
			currentStage.close();
			// Montrer la nouvelle fenêtre
			stage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
	}







	//Inialisation des lancement des fenetres comme par exemple Bienvenue Elbachir , donc la valeur de lab_varier est Elbachir

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
	    if (lab_varier != null)
	    {
	        lab_varier.setText(LoginController.username);
	    }
	    else
	    {
	        System.err.println("lab_varier is null. Check the FXML file .");

	    }
        professeurController.cinNumero = LoginController.cin;
        if(tableModule!=null)
        {
            this.UpdateTable();
        }
        else {
            System.err.println("tableModule is null. Check the FXML file.");
        }


    }

	
	// CRUD Module

	public void UpdateTable() {
		database a = new database();
		colonneId.setCellValueFactory(new PropertyValueFactory<>("id_module"));
		ColonneNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colonneSemistre.setCellValueFactory(new PropertyValueFactory<>("semistre"));
		delete.setCellFactory(column -> new TableCell<Module, Void>() {
			private final ImageView deleteImageView = new ImageView(new Image("/image/delete1.png")); 
			{
				deleteImageView.setFitWidth(30);
				deleteImageView.setFitHeight(30);

				// Ajoutez un gestionnaire d'événements pour le clic sur l'image de suppression
				setOnMouseClicked(event -> {
					Module module = getTableView().getItems().get(getIndex());
					if (module != null) {
						// Afficher une boîte de dialogue de confirmation
						Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
						alert.setTitle("Confirmation de suppression");
						alert.setHeaderText("Voulez-vous vraiment supprimer cette ligne ?");
						alert.setContentText("ID : " + module.getId_module());

						Optional<ButtonType> result = alert.showAndWait();

						if (result.isPresent() && result.get() == ButtonType.OK) {
							// Appel à la méthode pour supprimer la ligne de la base de données
							a.deleteModuleFromDatabase(module.getId_module());

							// Supprimez la ligne du tableau
							getTableView().getItems().remove(module);

							System.out.println("Ligne supprimée avec succès.");
						}
					}
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(deleteImageView);
				}
			}
		});

		update.setCellFactory(column -> new TableCell<>() {
			private final ImageView updateImageView = new ImageView(new Image("/image/update1.png")); // Ajoutez le
																										// chemin vers
																										// votre icône
																										// de
																										// suppression
			{
				updateImageView.setFitWidth(30);
				updateImageView.setFitHeight(30);
				// Ajoutez un gestionnaire d'événements pour le clic sur l'image de suppression
				setOnMouseClicked(event -> {
					Module module = getTableView().getItems().get(getIndex());
					// Ajoutez le code de suppression ici en utilisant le module.getId_module()
					to_update(module.getId_module(), module.getNom(), module.getSemistre());
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(updateImageView);
				}
			}
		});
		listM = a.getDatamodules(professeurController.cinNumero);
		tableModule.setItems(listM);
	}



}





