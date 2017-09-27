package view;

import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.TicTacToeGame;

/**
 * 
 * @author ianoh
 * Implements a textAreaView for the game of tic tac toe
 */
public class TextAreaView extends BorderPane implements Observer {

  private TicTacToeGame theGame;
  private String board;
  private String buttonMessage="Make move";
  private Button button=new Button(buttonMessage);
  private TextField label1Text;
  private TextField label2Text;
  private Label label=new Label("_ _ _\n_ _ _\n_ _ _");

  public TextAreaView(TicTacToeGame TicTacToeGame) {
    theGame = TicTacToeGame;
    initializePane();
  }

  //Set up the pane for the tic tac toe game
  private void initializePane() {
	  
	  board=theGame.toString();
	  GridPane gridpane = new GridPane();
		 gridpane.setHgap(10);
		 gridpane.setVgap(10);
		 ButtonListener handler = new ButtonListener();
	  Label label1 = new Label("row");
	  Label label2 = new Label("column");
	  button.setOnAction(new ButtonListener());
	  label1Text = new TextField();
	 label2Text = new TextField();
	 gridpane.add(label1, 3, 2);
	 gridpane.add(label2, 3, 3);
	 gridpane.add(label1Text, 4, 2);
	 gridpane.add(label2Text, 4, 3);
	 gridpane.add(button, 4, 4);
    label.setFont(new Font("serif", 40));
    BorderPane.setAlignment(label, Pos.TOP_CENTER);
    BorderPane.setMargin(label, new Insets(0, 0, 50, 0));
    setBottom(label);
    BorderPane.setAlignment(gridpane, Pos.TOP_CENTER);
    BorderPane.setMargin(gridpane, new Insets(20, 0, 0, 0));
    setCenter(gridpane);
    
  }

  //Update the board on occasion
  @Override
  public void update(Observable o, Object arg) {
    // TODO Auto-generated method stub
	  theGame = (TicTacToeGame) o;
	  updateBoard();
    System.out.println("\nIn TextAreaView.update() \n" + o);
  }
  
  //Update board compared to the toString of the array
  public void updateBoard() {
	  label.setText(theGame.toString());
  }
  
  //A button listener to check when a move button is pressed
  private class ButtonListener implements EventHandler<ActionEvent> {

	    @Override
	    public void handle(ActionEvent arg0) {
	      Button buttonClicked = (Button) arg0.getSource();
	      if(buttonClicked==button) {
	    	  int row;
	    	  int col;
	    	  try {
	    	  row=Integer.parseInt(label1Text.getText());
	    	  col=Integer.parseInt(label2Text.getText());
	    	  }
	    	  catch(NumberFormatException e){
	    		  button.setText("Invalid Choice");
	    		  return;
	    	  }
	    	  if(row!=0 && row!=1 && row!=2 || col!=0 && col!=1 && col!=2 || !theGame.available(row, col) ) {
	    		  button.setText("Invalid Choice");
	    		  return;
	    	  }
	    	  button.setText("Make move");
	    	  theGame.humanMove(row, col, false);
	    	  label1Text.setText("");
	    	  label2Text.setText("");
	    	  if(theGame.didWin('O')) {
	    		  button.setText("O wins");
	    	  }
	    	  if(theGame.didWin('X')) {
	    		  button.setText("X wins");
	    	  }
	    	  if(theGame.tied()) {
	    		  button.setText("Tie");
	    	  }
	    	  updateBoard();
	      }
	      
	    }
  }
}