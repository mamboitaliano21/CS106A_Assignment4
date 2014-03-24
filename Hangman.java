/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	/*constants */
	/*number of life you have*/
	private static final int LIFE = 8;
	
	public void init() { 
		 canvas = new HangmanCanvas(); 
		 add(canvas); 
	} 
	
    public void run() {
		setUpGame();
		while(checkWinCondition()){
			updateUI();
			guessWord();
			canvas.displayWord(guess);
		}
		endGame();
	}
    
    private void setUpGame(){
    	println("Welcome to Hangman!");
    	canvas.reset();
    	int totalSecrets = hangmanlexicon.getWordCount();
    	secret = hangmanlexicon.getWord(rgen.nextInt(0,totalSecrets-1));
    	guess = "";
    	life = LIFE;
    	for (int i = 0; i < secret.length(); i++){
    		guess += "_";
    	}

    }
    
    /*show the condition of the game to the user */
    private void updateUI(){
    	
    	println("The word now looks like this: " + guess);
    	println("You have " + life + " guesses left.");
    }
    
    /*let user guess the letter and update the game's model */
    private void guessWord(){
    	String input = readLine("Your guess: ");
    	while(input.isEmpty() || !isValidInput(input)){
    		println("Your input is invalid, please try again.");
    		input = readLine("Your guess: ");
    	}
    	char letter = input.charAt(0);
    	letter = Character.toUpperCase(letter);
    	if(!(subtituteLetter(letter))){
    		println("There are no " + letter + "\'s in the word.");
    		life -=1 ;
    		canvas.noteIncorrectGuess(letter);
    	}

    }
    
    /* check if input from user is valid , returns bool value */
    private boolean isValidInput(String input){
    	if(input.length() > 1) return false;
    	else if(input.length() < 0) return false;
    	/*take one single char */
    	char letter = input.charAt(0);
    	if(!Character.isLetter(letter)) return false;
    	/*previous implementation */
    	//if(letter < 65 || (letter > 90 && letter < 61) || letter > 122) return false;
    	return true;
    }
    
    private boolean isMatch(char input, char secret){
    	if(input == secret) return true;
    	return false;
    }
    
    private boolean subtituteLetter(char input){
    	/*provide a base for copying string*/
    	boolean flag = false;
    	String temp = "";
    	
    	for(int i = 0; i < secret.length(); i++){
    		println(temp);
    		if(Character.isLetter(guess.charAt(i))){ 
				temp += guess.charAt(i);
				continue;
    		}
			else if(isMatch(input,secret.charAt(i))){
    			temp += input;
    			flag = true;
    		}
			else {temp += "_";}

    	}
    	if(flag) guess = temp;
    	return flag;
    }
    
    private boolean checkWinCondition(){
    	if (guess.equals(secret)) return false;
    	if (life == 0) return false;
    	return true;
    }
    
    private void endGame(){
    	println("The word is: " + secret);
    	if(life > 0){
    		println("You Won!");
    	}
    	else {
    		println("You Lose.");
    	}

    }

    /*instance variable */
    private HangmanLexicon hangmanlexicon = new HangmanLexicon();
    private RandomGenerator rgen = RandomGenerator.getInstance();
    /* the secret word that needs to be guessed */
    private String secret;
    /* player's guess */
    private String guess;
    private int life;
    /* class declaration */
    private HangmanCanvas canvas; 






}



