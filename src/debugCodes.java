package src;/*
Luca Mazzotta
ICS4U0-C
Debug Code class
holds various debug codes to be used in the program
May 29, 2023
*/

public class debugCodes {
    public static boolean allJone, mrJone, peek;//booleans to test if codes are active

    /**
     * Activate set codes when method called
     * @param code set by debug box
     */
    public static void useCodes(String code) {

        if (code.equalsIgnoreCase("Mr. Jone")){
            mrJone = true;//sets the mr jone card to be enabled in the deck
            CardDeck.deckReset();//reshuffles deck
            mainMenu.debugBox.setText("Code Applied");
        }
        if (code.equalsIgnoreCase("Oops all Jone!")) {
            allJone = true;//sets all jone cheat active
            CardDeck.deckReset();//reshuffled deck
            CardDeck.cardList.add(new Card("Mr. Jone", "imageAssets/cardSprites/MrJoneCard.png", 99, 99, 1));//adds mr jone card
            mainMenu.debugBox.setText("Code Applied");
        }
        if (code.equalsIgnoreCase("show hand"))
        {
            peek = true;
            mainMenu.debugBox.setText("Code Applied");
        }

        /**
         * Code takes entered string and isolates the integers for number based cheat codes
         */
        String codeNum = code;
        codeNum = codeNum.replaceAll("[^\\d.]", "");//replaces all letters with null
        int codeNumInt;
        try {//tries to convert string of numbers to integer
            codeNumInt = Integer.parseInt(codeNum);//sets codeNumINt to the isolated numbers from the entered code
        } catch (NumberFormatException e) {
            codeNumInt = 0;//if it couldnt be converted it sets it to 0
        }

        /**
         * Player HP, VP and AI HP modifer cheat codes
         */
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
            PanelManager.player.setMaxVP(codeNumInt);
            PanelManager.player.setVP(codeNumInt);
            mainMenu.debugBox.setText("Code Applied");
        }

        //tests if the code was not applied and sets the text to code dne
        if (!mainMenu.debugBox.getText().equals("Code Applied")) mainMenu.debugBox.setText("Code DNE");
    }

    /**
     * Resets debug code booleans and variables after game restart
     */
    public static void NoCodes()
    {
        mrJone = false;
        allJone = false;
        peek = false;
        PanelManager.player.setHP(250);
        PanelManager.player.setMaxHP(250);
        PanelManager.ai.setMaxHP(250);
        PanelManager.ai.setHP(250);
        PanelManager.player.setMaxVP(5);
        PanelManager.player.setVP(5);
    }
}
