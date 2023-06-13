package src;/*
Luca Mazzotta
ICS4U0-B
Player Object
Object that stores the user player's cards, selected cards, HP, Attack and value points
May 11, 2023 * Fixed card deck object from instance
 */

public class Player {
    private int HP, VP;
    private int maxHP = 250;
    private int maxVP = 5;
    //public boolean selections[] = new boolean[5];
    /**
     * List that holds 5 of the players cards in their hand as separate objects
     */
    public PlayerCardPanel[] PlayerHand = new PlayerCardPanel[5];
    /**
     * List that holds the players cards as objects for the game field
     */
    public static PlayerCardPanel[] PlayerPlayedCards = new PlayerCardPanel[5];


    // constructor method called when object created
    public Player() {
        HP = maxHP;
        VP = maxVP;

    }

    public void setMaxVP(int newValue) {
        maxVP = newValue;
    }

    /**
     * Play the cards that the user selected. Selected cards are tested by the boolean array selections[]
     */

    // changes the HP
    public void setHP(int newValue) {
        HP = newValue;
    }

    // returns the HP
    public int getHP() {
        return HP;
    }

    //resets the HP
    public void resetHP() { HP = maxHP; }

    public void removeHP(int amount) {
        this.setHP(this.getHP() - amount);
    }

    public void setMaxHP(int newValue) {
        maxHP = newValue;
    }
    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxVP() {
        return maxVP;
    }

    // changes the Value points
    public void setVP(int newValue) {
        VP = newValue;
    }

    public void resetVP() {
        VP = maxVP;
    }

    // returns the value points
    public int getVP() {
        return VP;
    }

    public void removeVP(int newValue) {
        VP -= newValue;
    }
}



