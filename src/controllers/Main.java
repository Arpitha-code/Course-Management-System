package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
			LoginController.readfromfile();
			LoginController.readFromDatabse();
			LoginController.write();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("/views/application.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Login Screen");
			stage.setResizable(false);
			stage.setOnCloseRequest(event -> System.exit(0));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
