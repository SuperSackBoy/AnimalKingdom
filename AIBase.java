/*
Name: Mateo Adebowale
Class: ICS4U0
Assignment: Animal Kingdom: Card Arena
Sub-Assignment: AI base object
Date: 2023-05-16
Description: The main hub where all the AI values will be stored and strategy types will be decided
*/
import java.util.LinkedList;
public class AIBase
{
    //--------------------------------------------------
    protected static LinkedList<Card> AiHandList = new LinkedList<Card>();
    protected static int HP, PlayerHP = 100, playedVP = 0;
    //--------------------------------------------------
    public AIBase()
    {
        for (int i = 0; i < 5; i++)
        {
            AiHandList.add(CardDeck.drawCard());
        }
        HP = 150;
    }
    //--------------------------------------------------
    public static void playAI()
    {
        playedVP = 0;
        if (HP <= (PlayerHP - 20))
        {
            AiHandList = DefensiveAI.Play(AiHandList);
            move();
        }
        else
        {
            AiHandList = AggressiveAI.Play(AiHandList);
            move();
        }
    }
    //--------------------------------------------------
    private static void move ()
    {
        for (int i = 0; i < 5; i++)
        {
            if ((playedVP + AiHandList.get(i).getVP()) <= 5)
            {
                for (int ii = 0; ii < 5; ii++)
                {
                    if (AICardManager.AIPlayed[ii] != null)
                    {
                        AICardManager.playCard(i, ii);
                        playedVP = playedVP + AiHandList.get(i).getVP();
                    }
                }
            }
            else
            {
                break;
            }
        }
    }
}