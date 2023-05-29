/*
Luca Mazzotta
ICS4U0-C
Debug Code class
holds various debug codes to be used in the program
May 29, 2023
*/

public class debugCodes {
    public static boolean allJone, mrJone;
    public static void useCodes(String code) {

        if (code.equalsIgnoreCase("Mr. Jone")){
            CardDeck.deckReset();
            mrJone = true;
            System.out.println(mainMenu.debugCode);
        }
        if (code.equalsIgnoreCase("Oops all Jone!")) {
            CardDeck.deckReset();
            CardDeck.cardList.add(new Card("Mr. Jone", "imageAssets/cardSprites/MrJoneCard.png", 99, 99, 1));
            allJone = true;
            System.out.println(mainMenu.debugCode);
        }

        String codeNum = code;
        codeNum = codeNum.replaceAll("[^\\d.]", "");
        int codeNumInt;
        try {
            codeNumInt = Integer.parseInt(codeNum);
        } catch (NumberFormatException e) {
            codeNumInt = 0;
        }

        if (code.equalsIgnoreCase("Player HP " + codeNum)) {
            Player.setHP(codeNumInt);
            System.out.println(mainMenu.debugCode);
        }
        if (code.equalsIgnoreCase("AI HP "+ codeNum)) {
            PanelManager.ai.HP = codeNumInt;
            System.out.println(mainMenu.debugCode);
        }

        if (code.equalsIgnoreCase("Player VP " + codeNum)) {
            Player.setMaxVP(codeNumInt);
            Player.setVP(codeNumInt);
            System.out.println(mainMenu.debugCode);
        }
    }
}
