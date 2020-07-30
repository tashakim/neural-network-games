package IceHockey;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * PaneOrganizer class controls the visual display of the program, it's consists
 * of many helper methods that creates the _root pane and _bottomPane VBox.
 */
public class PaneOrganizer {
	// Declaring private instance variables
	private BorderPane _root;
	private VBox _quitBox;
	private IceHockey _iceHockey;
	private Color _paleBlue;

	/**
	 * PaneOrganizer constructor sets the background and creates the Tetris instance
	 * and make it appear visually on the user interface. It also aligns the
	 * _bottomBox VBox to the bottom of the user interface.
	 */
	public PaneOrganizer() {
		_root = new BorderPane();
		// Setting background color
		_paleBlue = Color.web("#E5EFF4");
		BackgroundFill fill = new BackgroundFill(_paleBlue, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(fill);
		_root.setBackground(background);
		Pane iceHockeyPane = new Pane();
		// Associating PaneOrganizer with DoodleJump class
		_iceHockey = new IceHockey(iceHockeyPane);
		_root.setCenter(iceHockeyPane);
		// Setting up Quit Button Pane
		this.createQuitPane();
		_root.setBottom(_quitBox);
		_root.setFocusTraversable(false);
	}

	/**
	 * Methods that creates the VBox in the bottom, that is consists of the
	 * instructions and quit button.
	 */
	private void createQuitPane() {
		// Instantiating instance variable
		_quitBox = new VBox();
		// Importing exit icon
		Button quitButton = new Button("Exit Game");
		quitButton.setStyle("-fx-background-color: #011434; -fx-text-fill: white;");
		// Adding shadows to the button
		DropShadow shadow = new DropShadow();
		// Adding the shadow when the mouse cursor is on
		quitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				quitButton.setEffect(shadow);
			}
		});
		// Removing the shadow when the mouse cursor is off
		quitButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				quitButton.setEffect(null);
			}
		});
		// Handling the Quit Button
		quitButton.setOnAction(new QuitHandler());
		quitButton.setFocusTraversable(false);
		// Setting up VBox style
		_quitBox.getChildren().add(quitButton);
		_quitBox.setStyle("-fx-background-color: #FFFFFF;");
		_quitBox.setAlignment(Pos.CENTER);
		_quitBox.setPrefHeight(Constants.QUITPANE_HEIGHT);
	}

	/**
	 * Returning _root Pane
	 */
	public BorderPane getRoot() {
		return _root;
	}

	/**
	 * QuitHandler implements the EventHandler interface and defines the function of
	 * the Quit button
	 */
	private class QuitHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			Platform.exit();
		}
	}
}
