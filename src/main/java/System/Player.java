package System;
public class Player {
   private char character;
   private String name;
   private boolean isTurn;
   private boolean isPC;

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public Player() {
        character = 'X';
        name = "Player";
        isTurn = false;
        isPC = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsTurn() {
        return isTurn;
    }

    public void setIsTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public boolean getIsPC() {
        return isPC;
    }

    public void setIsPC(boolean isPC) {
        this.isPC = isPC;
    }
    
    
}
