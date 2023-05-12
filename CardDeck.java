/*
Luca Mazzotta
ICS4U0-B
Card Deck Object
This object is a deck made up of one of every Card that will be used to add Cards to players hands
May 11, 2023 *changed fields to static
*/

import java.util.LinkedList;

public class CardDeck {
    public static LinkedList<Card> cardList = new LinkedList<Card>();//list of all Cards
    CardDeck() {//constructor
        deckReset();//resets the deck when Card deck instance is created
    }
    /**
     * Method used to draw a random Card to a players hand from the CardDeck object
     * @return randomly selected Card
     */
    public static Card drawCard() {
        if (cardList.size() <= 0) {
            deckReset();
        }
        Card CardDrew = new Card();//creates an instance of the Card object to be returned
        int rNum = (int) (Math.random()* cardList.size());//gets random number from the Card list index
        CardDrew = cardList.get(rNum);//sets the drawn Card to the Card in the list of the rNum index
        cardList.remove(rNum);//removes that Card from the deck
        return CardDrew;//sends Card out of method
    }
    /**
     * Resets the deck to have all of the Cards in it
     */
    public static void deckReset() {
        cardList.add(new Card("Arctic Hare", 10, 5, 1));
        cardList.add(new Card("Blue Jay", 10, 5, 1));
        cardList.add(new Card("Chipmunk", 10, 5, 1));
        cardList.add(new Card("Fisher", 10, 5, 1));
        cardList.add(new Card("Groundhog", 10, 5, 1));
        cardList.add(new Card("Stoat", 10, 5, 1));
        cardList.add(new Card("Arctic Fox", 15, 15, 1));
        cardList.add(new Card("Beaver", 20, 10, 2));
        cardList.add(new Card("Penguin", 20, 10, 2));
        cardList.add(new Card("Racoon", 10, 20, 2));
        cardList.add(new Card("Wolf", 20, 20, 2));
        cardList.add(new Card("Canada Goose", 10, 30, 3));
        cardList.add(new Card("Canada Lynx", 20, 20, 3));
        cardList.add(new Card("Coyote", 20, 20, 3));
        cardList.add(new Card("Snowy Owl", 15, 25, 3));
        cardList.add(new Card("Bison", 30, 20, 4));
        cardList.add(new Card("Black Bear", 30, 20, 4));
        cardList.add(new Card("Brown Bear", 35, 15, 4));
        cardList.add(new Card("Moose", 35, 25, 5));
        cardList.add(new Card("Polar Bear", 40, 20, 5));
        /*
         * if ("""debug mr jone cheat is active""") {
         * 	CardList.add(new Card("Mr. Jone", 99, 99, 1));
         * }
         */
    }
}


