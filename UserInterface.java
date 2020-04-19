//Angela Gadon
//CS2450
//Assignment 2
//3/1/20

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserInterface extends Application{
	public static void main (String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		//Create Actors
		TextField chatbox = new TextField("Type something here.");
		VBox vb = new VBox();
		vb.getChildren().addAll(chatbox);
		
		//Visual Adjustments
		vb.setAlignment(Pos.BOTTOM_CENTER);
		vb.setSpacing(100);
		
		Scene scene = new Scene(vb, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Chatroom");
		scene.getStylesheets().add("stylesheet.css");
		primaryStage.show();
	}
}
