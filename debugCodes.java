public class debugCodes {
    public static void useCodes(String code) {
        System.out.println(mainMenu.debugCode);

        if (code.equalsIgnoreCase("Mr. Jone")){
            if (mainMenu.debugCode.equalsIgnoreCase("Mr. Jone")) {
                CardDeck.deckReset();
                CardDeck.cardList.add(new Card("Mr. Jone", "imageAssets/cardSprites/MrJoneCard.png", 99, 99, 1));
            }
        }
    }
}
