/*
Luca Mazzotta
ICS4U0-B
Card Object
Cards that are used in the game and are put into the card deck to be used by players
May 5, 2023
*/
public class Card {
    String name, cardImg;
    int HP, ATK, VP;

    // empty constructor
    public Card() {
        name = "";
        HP = 0;
        ATK = 0;
        VP = 0;
    }

    // Main constructor to be called when instance of a card is created
    public Card(String name, String cardImg, int HP, int ATK, int VP) {
        this.name = name;// sets all stats to those placed in the arguments
        this.cardImg = cardImg;
        this.HP = HP;
        this.ATK = ATK;
        this.VP = VP;
    }

    // changes the name
    public void setName(String newValue) {
        name = newValue;
    }

    // returns the name
    public String getName() {
        return name;
    }

    // changes the HP
    public void setHP(int newValue) {
        HP = newValue;
    }

    // returns the HP
    public int getHP() {
        return HP;
    }// changes the Attack

    public void setATK(int newValue) {
        ATK = newValue;
    }

    // returns the Attack
    public int getATK() {
        return ATK;
    }

    // changes the Value points
    public void setVP(int newValue) {
        VP = newValue;
    }

    // returns the Value Points
    public int getVP() {
        return VP;
    }

    public void removeHP(int amount) {
        this.setHP(getHP() - amount);
    }

    // override to string for save file printing and debug printing
    @Override
    public String toString() {
        return name + "\n" + HP + "\n" + ATK + "\n" + VP;
    }
}



