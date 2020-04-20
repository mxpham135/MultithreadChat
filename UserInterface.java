//Angela Gadon
//CS2450
//Assignment 2
//3/1/20

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserInterface extends Application{
	
	private HBox chatDisplay = new HBox();
	
	public static void main (String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		//Create Actors
		VBox otherChat = new VBox();
		VBox myChat = new VBox();
		
		chatDisplay.getChildren().addAll(otherChat, myChat);
		
		TextField chatbox = new TextField("Chat here.");
		Button sendBtn = new Button("Send");
		HBox interfaceDisplay = new HBox();
		interfaceDisplay.getChildren().addAll(chatbox, sendBtn);
		
		VBox vbOverall = new VBox();
		vbOverall.getChildren().addAll(chatDisplay, interfaceDisplay);
		
		// set handlers
		sendBtn.setOnAction(e -> {
		     sendMessage(chatbox);
		});

		chatbox.setOnKeyReleased(event -> {
			  if (event.getCode() == KeyCode.ENTER){
				     sendMessage(chatbox);
				  }
		});
		
		displayChat(false, "Chat", "Enter a username to begin.");
				
		//Visual Adjustments
		chatDisplay.setAlignment(Pos.TOP_CENTER);
		vbOverall.setAlignment(Pos.BOTTOM_CENTER);
		vbOverall.setSpacing(100);
		
		Scene scene = new Scene(vbOverall, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Chatroom");
		scene.getStylesheets().add("stylesheet.css");
		primaryStage.show();
	}
	
	/*
	 * Sends whatever is currently in user's chat box out to Client.
	 */
	private void sendMessage(TextField textbox) {
	    String chat = textbox.getText();

	    // TODO: send String chat to Client
	    
	    // clear text
	    textbox.setText("");
	}
	
	/*
	 * Displays existing chat from current user and other users.
	 * boolean fromUser - true if chat to display is from current user
	 */
	public void displayChat(boolean fromUser, String username, String text) {
		Label nameLabel = new Label(username + ":");	//TODO: add different css to username
		Label chatLabel = new Label(text);
		VBox vb;
		VBox vbBlank;
		int targetVbNum = fromUser ? 1 : 0;
		int nonTargetVbNum = !fromUser ? 1 : 0;
		vb = (VBox) chatDisplay.getChildren().get(targetVbNum);	//add to myChat VBox
		vbBlank = (VBox) chatDisplay.getChildren().get(nonTargetVbNum);	//add to myChat VBox
	    vb.getChildren().addAll(nameLabel, chatLabel);
	    vbBlank.getChildren().addAll(new Label(""), new Label(""));
	}
}
