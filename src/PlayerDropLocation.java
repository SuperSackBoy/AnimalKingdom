package src;/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
A place where a PlayerCardPanel can be placed
*/

public class PlayerDropLocation extends DropLocation{
    public int index;
    public boolean active;
    public PlayerDropLocation(int x, int y, int index) {
        super(x,y);
        this.index = index;
    }
}
