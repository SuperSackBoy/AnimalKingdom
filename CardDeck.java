/*
Luca Mazzotta
ICS4U0-B
Card Deck Object
This object is a deck made up of one of every Card that will be used to add Cards to players hands
May 11, 2023 *changed fields to static
*/

import java.util.LinkedList;

public class CardDeck {
    public static LinkedList<Card> cardList = new LinkedList<>();//list of all Cards
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
            cardList.add(new Card("Arctic Hare", "imageAssets/cardSprites/ArcticHareCard.png", 10, 5, 1));
            cardList.add(new Card("Blue Jay", "imageAssets/cardSprites/BlueJayCard.png", 10, 5, 1));
            cardList.add(new Card("Chipmunk", "imageAssets/cardSprites/ChipmunkCard.png", 10, 5, 1));
            cardList.add(new Card("Fisher", "imageAssets/cardSprites/FisherCard.png", 10, 5, 1));
            cardList.add(new Card("Groundhog", "imageAssets/cardSprites/GroundHogCard.png", 10, 5, 1));
            cardList.add(new Card("Stoat", "imageAssets/cardSprites/StoatCard.png", 10, 5, 1));
            cardList.add(new Card("Arctic Fox", "imageAssets/cardSprites/ArcticFoxCard.png", 15, 15, 1));
            cardList.add(new Card("Beaver", "imageAssets/cardSprites/BeaverCard.png", 20, 10, 2));
            cardList.add(new Card("Penguin", "imageAssets/cardSprites/PenguinCard.png", 20, 10, 2));
            cardList.add(new Card("Racoon", "imageAssets/cardSprites/RaccoonCard.png", 10, 20, 2));
            cardList.add(new Card("Wolf", "imageAssets/cardSprites/WolfCard.png", 20, 20, 2));
            cardList.add(new Card("Canada Goose", "imageAssets/cardSprites/CanadaGooseCard.png", 10, 30, 3));
            cardList.add(new Card("Canada Lynx", "imageAssets/cardSprites/CanadaLynxCard.png", 20, 20, 3));
            cardList.add(new Card("Coyote", "imageAssets/cardSprites/CoyoteCard.png", 20, 20, 3));
            cardList.add(new Card("Snowy Owl", "imageAssets/cardSprites/SnowyOwlCard.png", 15, 25, 3));
            cardList.add(new Card("Bison", "imageAssets/cardSprites/BisonCard.png", 30, 20, 4));
            cardList.add(new Card("Black Bear", "imageAssets/cardSprites/BlackBearCard.png", 30, 20, 4));
            cardList.add(new Card("Brown Bear", "imageAssets/cardSprites/BrownBearCard.png", 35, 15, 4));
            cardList.add(new Card("Moose", "imageAssets/cardSprites/MooseCard.png", 35, 25, 5));
            cardList.add(new Card("Polar Bear", "imageAssets/cardSprites/PolarBearCard.png", 40, 20, 5));
    }
}


