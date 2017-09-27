package model;

import java.awt.Point;
import java.util.Random;

/**
 * This strategy selects the first available move at random.  It is easy to beat
 * 
 * @throws IGotNowhereToGoException whenever asked for a move that is impossible to deliver
 * 
 * @author Ian O'Heir
 */
public class RandomAI implements TicTacToeStrategy {

  // Find an open spot while ignoring possible wins and stops (block a guaranteed win)
  @Override
  public Point desiredMove(TicTacToeGame theGame) {
	  int counter=0;
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

  