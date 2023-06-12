package src;/*
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
            mainMenu.debugBox.setText("Code Applied");
        }
        if (code.equalsIgnoreCase("Oops all Jone!")) {
            CardDeck.deckReset();
            CardDeck.cardList.add(new Card("Mr. Jone", "imageAssets/cardSprites/MrJoneCard.png", 99, 99, 1));
            allJone = true;
            mainMenu.debugBox.setText("Code Applied");
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
            PanelManager.player.setHP(codeNumInt);
            PanelManager.player.setMaxHP(codeNumInt);
            mainMenu.debugBox.setText("Code Applied");
        }
        if (code.equalsIgnoreCase("AI HP "+ codeNum)) {
            PanelManager.ai.setMaxHP(codeNumInt);
            PanelManager.ai.setHP(codeNumInt);
            mainMenu.debugBox.setText("Code Applied");
        }

        if (code.equalsIgnoreCase("Player VP " + codeNum)) {
            Player.setMaxVP(codeNumInt);
            PanelManager.player.setVP(codeNumInt);
            mainMenu.debugBox.setText("Code Applied");
        }

        if (!mainMenu.debugBox.getText().equals("Code Applied")) mainMenu.debugBox.setText("Code DNE");
    }
}
