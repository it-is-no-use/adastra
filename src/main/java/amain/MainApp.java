package amain;

import controller.Entrance;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			String fxmlFile = "/entrance.fxml";
			FXMLLoader loader = new FXMLLoader();
			Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
			Scene scene = new Scene(root,850,650);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adastra[¬ход]");
			primaryStage.getIcons().add(new Image("/star-32.png"));
			primaryStage.show();
			Entrance.STAGE = primaryStage;
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		launch(args);
	}
}