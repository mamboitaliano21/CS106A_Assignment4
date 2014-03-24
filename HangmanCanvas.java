/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		initScaffold();
		man = null;
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
		double x = getWidth()/4;
		double y = getHeight()/2+getHeight()/4;
		word = subtituteUnderscore(word);
		wordDisplay = new GLabel(word);
		wordDisplay.setFont("Halvetica-24");
        if (getElementAt(x,y) != null){
            remove(getElementAt(x,y));
        }
		add(wordDisplay,x,y);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
		displayError(letter);
		displayHangman();
		
		
	}
	/* Initialize the scaffolding */
	private void initScaffold(){
		double xMid = getWidth()/2;
		double yMid = getHeight()/2;
		GLine pole1 = new GLine(xMid-BEAM_LENGTH,yMid-SCAFFOLD_HEIGHT/2,xMid-BEAM_LENGTH,yMid+SCAFFOLD_HEIGHT/2);
		GLine beam = new GLine(xMid-BEAM_LENGTH,yMid-SCAFFOLD_HEIGHT/2,xMid+BEAM_LENGTH,yMid-SCAFFOLD_HEIGHT/2);
		GLine rope  = new GLine(xMid+BEAM_LENGTH,yMid-SCAFFOLD_HEIGHT/2,xMid+BEAM_LENGTH,yMid-SCAFFOLD_HEIGHT/2+ROPE_LENGTH);
		add(pole1);
		add(beam);
		add(rope);
	}
	/* Helper method to change the underscore to Hypen */
	private String subtituteUnderscore(String word){
		String result = "";
		for(int i = 0; i < word.length(); i++){
			if(word.charAt(i) == '_'){
				result += '-';
			} else {
				result += word.charAt(i);
			}
		}
		return result;
	}
	
	private void displayError(char letter){
		double x = getWidth()/4;
		double y = getHeight()/2+getHeight()/4+getHeight()/8;
		error += letter;
		GLabel errorLabel = new GLabel(error,x,y);
        if (getElementAt(x,y) != null){
            remove(getElementAt(x,y));
        }
        add(errorLabel);
		errorLabel.setFont("Halvetica-24");
		
	}
	
	private void displayHangman(){
		int life = error.length();
		double xMid = getWidth()/2;
		double yMid = getHeight()/2;
		double x = xMid + BEAM_LENGTH;
		double y = yMid - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH;
        //if (getElementAt(x,y) != null){
        //    remove(getElementAt(x,y));
       // }
		man = new GCompound();
		add(man,x-HEAD_RADIUS/2,y);

        

		if (life == 1){ 
		GOval head = new GOval(HEAD_RADIUS,HEAD_RADIUS);	
		man.add(head);
		}
		
		else if (life == 2){ 
		GLine neck = new GLine(HEAD_RADIUS/2,HEAD_RADIUS,HEAD_RADIUS/2,HEAD_RADIUS+BODY_LENGTH);
		man.add(neck);
		}
		
		else if (life == 3){ 
		GLine leftLeg = new GLine(HEAD_RADIUS/2,HEAD_RADIUS+BODY_LENGTH,0,HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
		man.add(leftLeg);
		}
		
		else if (life == 4){
			GLine rightLeg = new GLine(HEAD_RADIUS/2,HEAD_RADIUS+BODY_LENGTH,HEAD_RADIUS*2,HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
			man.add(rightLeg); 
			//GLine dick = new GLine(HEAD_RADIUS/2,HEAD_RADIUS+BODY_LENGTH,HEAD_RADIUS*2,HEAD_RADIUS*2+LEG_LENGTH);
			//man.add(dick);
		}
		
		
		else if (life == 5){ 
			leftHand = new GLine(HEAD_RADIUS/2,HEAD_RADIUS+ARM_OFFSET_FROM_HEAD,0,HEAD_RADIUS+BODY_LENGTH );
			man.add(leftHand);
		}
		
		else if (life == 6){
			rightHand = new GLine(HEAD_RADIUS/2,HEAD_RADIUS+ARM_OFFSET_FROM_HEAD,HEAD_RADIUS*2,HEAD_RADIUS+BODY_LENGTH );
			man.add(rightHand);
		}
		
		else if (life == 7){
			rightHand.setVisible(false);
			GLine raiseLeftHand = new GLine(HEAD_RADIUS/2,HEAD_RADIUS+ARM_OFFSET_FROM_HEAD,0,HEAD_RADIUS );
			man.add(raiseLeftHand);
		}
		
		else if (life == 8){
			leftHand.setVisible(false);
			GLine raiseRightHand = new GLine(HEAD_RADIUS/2,HEAD_RADIUS+ARM_OFFSET_FROM_HEAD,HEAD_RADIUS*2,HEAD_RADIUS );
			man.add(raiseRightHand);
		}

		
	}
	

	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

/* instance variable */
	private GCompound man;
	private GLabel wordDisplay;
	private String error = "";
	private GLine rightHand;
	private GLine leftHand;
}
