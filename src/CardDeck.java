package src;
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
    public static LinkedList<Card> cards = new LinkedList<>();
    /**
     * Method used to draw a random Card to a players hand from the CardDeck object
     * @return randomly selected Card
     */
    public static Card drawCard() {
        if (cardList.size() <= 0) {
            deckReset();
        }
        if (debugCodes.allJone) {//if the all jones cheat is active, only return jone cards
            return cardList.getLast();
        } else {
            Card CardDrew = new Card();//creates an instance of the Card object to be returned
            int J_check = (int) (Math.random() * 1000);
            if (J_check == 1)
            {
                CardDrew = new Card("Mr. Jone", "src/imageAssets/cardSprites/MrJoneCard.png", 99, 99, 1);
            }
            else
            {
                int rNum = (int) (Math.random() * cardList.size());//gets random number from the Card list index
                CardDrew = cardList.get(rNum);//sets the drawn Card to the Card in the list of the rNum index
                cardList.remove(rNum);//removes that Card from the deck
            }
            return CardDrew;//sends Card out of method
        }
    }
    /**
     * Resets the deck to have all the Cards in it
     */
    public static void deckReset() {
        if(cards.isEmpty()) {
            init();
        }
        cardList.addAll(cards);
    }

    /**
     * adds all cards to the deck
     */
    public static void init() {
        cards.add(new Card("Arctic Hare", "src/imageAssets/cardSprites/ArcticHareCard.png", 10, 5, 1));
        cards.add(new Card("Blue Jay", "src/imageAssets/cardSprites/BlueJayCard.png", 10, 5, 1));
        cards.add(new Card("Chipmunk", "src/imageAssets/cardSprites/ChipmunkCard.png", 10, 5, 1));
        cards.add(new Card("Fisher", "src/imageAssets/cardSprites/FisherCard.png", 10, 5, 1));
        cards.add(new Card("Groundhog", "src/imageAssets/cardSprites/GroundhogCard.png", 10, 5, 1));
        cards.add(new Card("Stoat", "src/imageAssets/cardSprites/StoatCard.png", 10, 5, 1));
        cards.add(new Card("Arctic Fox", "src/imageAssets/cardSprites/ArcticFoxCard.png", 15, 15, 2));
        cards.add(new Card("Beaver", "src/imageAssets/cardSprites/BeaverCard.png", 20, 10, 2));
        cards.add(new Card("Penguin", "src/imageAssets/cardSprites/PenguinCard.png", 20, 10, 2));
        cards.add(new Card("Racoon", "src/imageAssets/cardSprites/RaccoonCard.png", 10, 20, 2));
        cards.add(new Card("Wolf", "src/imageAssets/cardSprites/WolfCard.png", 20, 10, 2));
        cards.add(new Card("Canada Goose", "src/imageAssets/cardSprites/CanadaGooseCard.png", 10, 30, 3));
        cards.add(new Card("Canada Lynx", "src/imageAssets/cardSprites/CanadaLynxCard.png", 20, 20, 3));
        cards.add(new Card("Coyote", "src/imageAssets/cardSprites/CoyoteCard.png", 20, 20, 3));
        cards.add(new Card("Snowy Owl", "src/imageAssets/cardSprites/SnowyOwlCard.png", 15, 25, 3));
        cards.add(new Card("Bison", "src/imageAssets/cardSprites/BisonCard.png", 30, 20, 4));
        cards.add(new Card("Black Bear", "src/imageAssets/cardSprites/BlackBearCard.png", 30, 20, 4));
        cards.add(new Card("Brown Bear", "src/imageAssets/cardSprites/BrownBearCard.png", 35, 15, 4));
        cards.add(new Card("Moose", "src/imageAssets/cardSprites/MooseCard.png", 35, 25, 5));
        cards.add(new Card("Polar Bear", "src/imageAssets/cardSprites/PolarBearCard.png", 40, 20, 5));
        if (debugCodes.mrJone) CardDeck.cards.add(new Card("Mr. Jone", "src/imageAssets/cardSprites/MrJoneCard.png", 99, 99, 1));//if the jone cheat is active, adds jone card
    }

    public static Card findCard(String name) {
        for(Card c : cards) {
            if(c.name.equals(name)) {
                return c;
            }
        }
        return null;
    }

    public static boolean removeCard(String name) {
        for(int x = 0; x < cardList.size(); x++) {
            if(cardList.get(x).name.equals(name)) {
                cardList.remove(x);
                return true;
            }
        }
        return false;
    }
}


