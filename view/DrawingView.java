package view;

import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.TicTacToeGame;

/**
 * 
 * @author ianoh
 *Implements a class where the user can click and draw their move for tic tac toe
 */
public class DrawingView extends BorderPane implements Observer {
	
	private TicTacToeGame theGame;
	private Canvas canvas;
	private GraphicsContext gc;
	private String label1Text="Make Move";
	private Label label1;
	
	public DrawingView(TicTacToeGame TicTacToeGame) {
		theGame = TicTacToeGame;
	    initializePane();
	}

	//Initialize the board
	private void initializePane() {
		label1 = new Label("Make Move");
		label1.setFont(new Font("serif", 40));
	    BorderPane.setAlignment(label1, Pos.TOP_CENTER);
	    BorderPane.setMargin(label1, new Insets(0, 0, 50, 0));
		setTop(label1);
		 canvas = new Canvas(250,250);
		 canvas.setStyle("-fx-background-color: cyan");
		 canvas.setOnMouseClicked(new EventHandler<MouseEvent>()
		 {
	            @Override
	            public void handle(MouseEvent t) {
	            	int squarePicked=getSquare(t.getSceneX(),t.getSceneY());
	            	if(squarePicked==-1) {
	            		return;
	            	}
	            	else {
	            		drawTurn(squarePicked);
	            	}
	                
	            }
	        });
		 //Create 3x3 board
		 BorderPane.setAlignment(canvas, Pos.TOP_CENTER);
		    BorderPane.setMargin(canvas, new Insets(20, 0, 40, 0));
		    gc= canvas.getGraphicsContext2D();
		    gc.setFill(Color.RED);
		    gc.setStroke(Color.BLACK);
		    gc.strokeRect(35, 0, 60, 60);
		    gc.strokeRect(95, 0, 60, 60);
		    gc.strokeRect(155, 0, 60, 60);
		    gc.strokeRect(35, 60, 60, 60);
		    gc.strokeRect(95, 60, 60, 60);
		    gc.strokeRect(155, 60, 60, 60);
		    gc.strokeRect(35, 120, 60, 60);
		    gc.strokeRect(95, 120, 60, 60);
		    gc.strokeRect(155, 120, 60, 60);
		 setCenter(canvas);
		
	}
	
	//Update the board to be cleared if a new game happened
	public void updateBoard() {
		System.out.println("Updating board");
		int counter=0;
		char[][] board=theGame.getTicTacToeBoard();
		for (int i=0;i<3;i++) {
			  for (int c=0;c<3;c++) {
				  if(theGame.available(i, c)) {
					  counter++;
				  }
			  }
		}
		if(counter==9) {
			gc.clearRect(35, 0, 250, 250);
		}
		label1.setText("Make Move");
		gc.setStroke(Color.BLACK);
	    gc.strokeRect(35, 0, 60, 60);
	    gc.strokeRect(95, 0, 60, 60);
	    gc.strokeRect(155, 0, 60, 60);
	    gc.strokeRect(35, 60, 60, 60);
	    gc.strokeRect(95, 60, 60, 60);
	    gc.strokeRect(155, 60, 60, 60);
	    gc.strokeRect(35, 120, 60, 60);
	    gc.strokeRect(95, 120, 60, 60);
	    gc.strokeRect(155, 120, 60, 60);
	}
		
	
	//Check to see what square was clicked, then do that turn
	public void drawTurn(int square) {
		Image X= new Image("file:///C:/Users/ianoh/Desktop/Classes/CSC%20335/gitrepos/ttt-patterns-Ianohurr/images/x.png");
		Image O= new Image("file:///C:/Users/ianoh/Desktop/Classes/CSC%20335/gitrepos/ttt-patterns-Ianohurr/images/o.png");
		if(square==1) {
			theGame.humanMove(0, 0, false);
		}
		if(square==2) {
			theGame.humanMove(0, 1, false);
		}
		if(square==3) {
			theGame.humanMove(0, 2, false);
		}
		if(square==4) {
			theGame.humanMove(1, 0, false);
		}
		if(square==5) {
			theGame.humanMove(1, 1, false);
		}
		if(square==6) {
			theGame.humanMove(1, 2, false);
		}
		if(square==7) {
			theGame.humanMove(2, 0, false);
		}
		if(square==8) {
			theGame.humanMove(2, 1, false);
		}
		if(square==9) {
			theGame.humanMove(2, 2, false);
		}
		if(theGame.didWin('O')){
			label1.setText("O wins");
		}
		if(theGame.didWin('X')){
			label1.setText("X wins");
		}
		if(theGame.tied()){
			label1.setText("Tied");
		}
		//Go through the array and see what needs to be drawn on the canvas
		char[][] board=theGame.getTicTacToeBoard();
		for (int i=0;i<3;i++) {
			  for (int c=0;c<3;c++) {
				  if(!theGame.available(i, c)) {
					  //First Square
					  if(i==0 && c==0 ) {
						  if(board[0][0]=='X') {
							gc.drawImage(X, 50, 15);
						  }
						  else {
							  gc.drawImage(O, 50, 15);
						  }
						}
					  //Second Square
					  if(i==0 && c==1 ) {
						  if(board[0][1]=='X') {
							gc.drawImage(X, 110, 15);
						  }
						  else {
							  gc.drawImage(O, 110, 15);
						  }
						}
					//Third Square
					  if(i==0 && c==2 ) {
						  if(board[0][2]=='X') {
							gc.drawImage(X, 170, 15);
						  }
						  else {
							  gc.drawImage(O, 170, 15);
						  }
						}
					//Fourth Square
					  if(i==1 && c==0 ) {
						  if(board[1][0]=='X') {
							gc.drawImage(X, 50, 75);
						  }
						  else {
							  gc.drawImage(O, 50, 75);
						  }
						}
					//Fifth Square
					  if(i==1 && c==1 ) {
						  if(board[1][1]=='X') {
							gc.drawImage(X, 110, 75);
						  }
						  else {
							  gc.drawImage(O, 110, 75);
						  }
						}
					//Sixth Square
					  if(i==1 && c==2 ) {
						  if(board[1][2]=='X') {
							gc.drawImage(X, 170, 75);
						  }
						  else {
							  gc.drawImage(O, 170, 75);
						  }
						}
					//Seventh Square
					  if(i==2 && c==0 ) {
						  if(board[2][0]=='X') {
							gc.drawImage(X, 50, 135);
						  }
						  else {
							  gc.drawImage(O, 50, 135);
						  }
						}
					//Eigth Square
					  if(i==2 && c==1 ) {
						  if(board[2][1]=='X') {
							gc.drawImage(X, 110, 135);
						  }
						  else {
							  gc.drawImage(O, 110, 135);
						  }
						}
					//Ninth Square
					  if(i==2 && c==2 ) {
						  if(board[2][2]=='X') {
							gc.drawImage(X, 170, 135);
						  }
						  else {
							  gc.drawImage(O, 170, 135);
						  }
						}
				  }
			  }
		}
		
	
	}
	
	//Check what square was clicked based on coordinates of the mouse click
	public int getSquare(Double x,Double y) {
		//square 0,0
		if(x>=35 && x<=95 && y>=155 && y<=215) {
			return 1;
		}
		//square 0,1
				if(x>=95 && x<=155 && y>=155 && y<=215) {
					return 2;
				}
		//square 0,2
		if(x>=155 && x<=215 && y>=155 && y<=215) {
			return 3;
		}
		//square 1,0
		if(x>=35 && x<=95 && y>=215 && y<=275) {
			return 4;
		}
		//square 1,1
				if(x>=95 && x<=155 && y>=215 && y<=275) {
					return 5;
				}
		//square 1,2
		if(x>=155 && x<=215 && y>=215 && y<=275) {
			return 6;
		}
		//square 2,0
		if(x>=35 && x<=95 && y>=275 && y<=335) {
			return 7;
		}
		//square 2,1
				if(x>=95 && x<=155 && y>=275 && y<=335) {
					return 8;
				}
		//square 2,2
		if(x>=155 && x<=215 && y>=275 && y<=335) {
			return 9;
		}
		return -1;
	}
	@Override
	public void update(Observable o, Object arg) {
	    updateBoard();
		theGame = (TicTacToeGame) o;


	}
	

}
