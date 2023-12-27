package Basededonne;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import professeur.Module;

public class database {

	Connection conn = null;
	Statement stt = null;
	public String nom;
	public String cin;

	public void connecter() {
		String url = "jdbc:mysql://127.0.0.1:3306/gestion_note";
		String user = "root";
		String password = "";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean enregistrer(String nom, String prenom, String email, String password, String cin, String type) {
		this.connecter();
		try {
			String req = "INSERT INTO user (nom,prenom,email,password,cin,type_utilisateur) values(?,?,?,?,?,?) ";
			PreparedStatement presmt = conn.prepareStatement(req);
			presmt.setString(1, nom);
			presmt.setString(2, prenom);
			presmt.setString(3, email);
			presmt.setString(4, password);
			presmt.setString(5, cin);
			presmt.setString(6, type);
			presmt.executeUpdate();
			conn.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean login_data(String email, String password) {
		this.connecter();
		try {
			String req = "SELECT * FROM user WHERE email = ? AND password = ?";
			PreparedStatement presmt = conn.prepareStatement(req);
			presmt.setString(1, email);
			presmt.setString(2, password);
			ResultSet rs = presmt.executeQuery();

			boolean userExists = rs.next();
			conn.close();
			return userExists;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String type_utilisateurSQL(String email) {
		// TODO Auto-generated method stub
		this.connecter();
		String type_utilisateur = "";

		try {
			String req = "SELECT * FROM user WHERE email = ?";
			PreparedStatement presmt = conn.prepareStatement(req);
			presmt.setString(1, email);
			ResultSet rs = presmt.executeQuery();

			if (rs.next()) {
				this.cin=rs.getString("cin");
				type_utilisateur = rs.getString("type_utilisateur");
				this.nom = rs.getString("nom") + " " + rs.getString("prenom");
			}

			conn.close();
			return type_utilisateur;
		} catch (SQLException e) {
			e.printStackTrace();
			return type_utilisateur;
		}
	}

	public boolean enregistrerModule(String nom_module, String semistre, String cin_prof,String code) {
		// TODO Auto-generated method stub

		this.connecter();
		try {
			String req = "INSERT INTO module (nom,semistre,cin_prof,id_module) values(?,?,?,?) ";
			PreparedStatement presmt = conn.prepareStatement(req);
			presmt.setString(1, nom_module);
			presmt.setString(2, semistre);
			presmt.setString(3, cin_prof);
			presmt.setString(4, code);


			presmt.executeUpdate();
			conn.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ObservableList<Module> getDatamodules(String cin) {
	    this.connecter();
	    ObservableList<Module> list = FXCollections.observableArrayList();
	    try {
	        String req = "SELECT * FROM module WHERE cin_prof=?";
	        PreparedStatement presmt = conn.prepareStatement(req);
	        presmt.setString(1, cin);
	        ResultSet rs = presmt.executeQuery();
	        while (rs.next()) {
	            list.add(new Module(rs.getString("id_module"), rs.getString("nom"), rs.getString("semistre")));
	        }

	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	
	public void deleteModuleFromDatabase(String moduleId) {
	    try {
	        this.connecter();
	        String req = "DELETE FROM module WHERE id_module = ?";
	        try (PreparedStatement pstmt = conn.prepareStatement(req)) {
	            pstmt.setString(1, moduleId);
	            pstmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Log l'exception pour le débogage
	    } finally {
	        try {
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	

	public boolean updateModule(String nom_module, String semestre, String code_moduleancienn, String code_module, String cinNumero) {
	    this.connecter();
	    try {
	        // Mettez à jour la ligne dans la base de données en fonction de l'ID du module
	        String req = "UPDATE module SET nom=?, semistre=?, id_module=? WHERE id_module=? AND cin_prof=?";
	        PreparedStatement preparedStatement = conn.prepareStatement(req);
	        preparedStatement.setString(1, nom_module);
	        preparedStatement.setString(2, semestre);
	        preparedStatement.setString(3, code_module);
	        preparedStatement.setString(4, code_moduleancienn);
	        preparedStatement.setString(5, cinNumero);

	        int rowsUpdated = preparedStatement.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Module mis à jour avec succès dans la base de données.");
	        } else {
	            System.out.println("Échec de la mise à jour du module dans la base de données.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return true;
	}




}
