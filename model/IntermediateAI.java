package model;

import java.awt.Point;
import java.util.Random;

/**
 * This TTT strategy tries to prevent the opponent from winning by checking
 * for a space where the opponent is about to win. If none found, it randomly
 * picks a place to win, which can sometimes be a win even if not really trying.
 * 
 * @author Ian O'Heir
 */
public class IntermediateAI implements TicTacToeStrategy {
	private boolean firstTurn=false;
  @Override
  // Precondition: During testing the AI is associated with the 'O', the odd number move.
  public Point desiredMove(TicTacToeGame theGame) {
    // TODO: Return a Point that would win, if not possible to block, 
    // if not possible to block return an available Point of your own choosing
	
	  
	  int counter=0;
	  for (int i=0;i<3;i++) {
		  for (int c=0;c<3;c++) {
			  counter++;
		  }
	  }
	  if(counter==9) {
		  firstTurn=true;
	  }
	
	//Check to win
	  for (int i=0;i<3;i++) {
		  for (int c=0;c<3;c++) {
			  if(theGame.available(i, c)) {
				  if(firstTurn==true) {
					  theGame.humanMove(i, c,true);
					  if(theGame.didWin('X')==true) {
						  theGame.getTicTacToeBoard()[i][c]='_';
						  return new Point(i, c);
					  }
					  else {
						  theGame.getTicTacToeBoard()[i][c]='_';
					  }
				  }
				  else {
					  theGame.computerMove(i, c);
					  if(theGame.didWin('O')==true) {
						  theGame.getTicTacToeBoard()[i][c]='_';
						  return new Point(i, c);
					  }
					  else {
						  theGame.getTicTacToeBoard()[i][c]='_';
					  }
				  }
			  }
		  }
		  }
	  
		//Check to block
		 for (int i=0;i<3;i++) {
			  for (int c=0;c<3;c++) {
				  if(theGame.available(i, c)) {
					  if(firstTurn=true) {
						  theGame.computerMove(i, c);
						  if(theGame.didWin('O')==true) {
							  theGame.getTicTacToeBoard()[i][c]='_';
							  return new Point(i, c);
						  }
						  else {
							  theGame.getTicTacToeBoard()[i][c]='_';
						  }
					  }
					  else {
				  theGame.humanMove(i, c, theGame.available(i, c));
				  if(theGame.didWin('X')==true) {
					  theGame.getTicTacToeBoard()[i][c]='_';
					  return new Point(i, c);
				  }
				  else {
					  theGame.getTicTacToeBoard()[i][c]='_';
				  }
					  }
				  }
				  
			  }
		  }
		 
	  
	  counter=0;
	  for (int i=0;i<3;i++) {
		  for (int c=0;c<3;c++) {
			  if(!theGame.available(i, c)) {
				  counter++;
			  }
		  }
	  }
	  if(counter==9) {
		  throw new IGotNowhereToGoException(null);
	  }
	  
	  while(true) {
		  	 Random rnd = new Random();
			 int x=rnd.nextInt(3);
			 int y=rnd.nextInt(3);
			 if(theGame.available(x, y)) {

				 return new Point(x,y);
			 }
	  }
	 
  }
}