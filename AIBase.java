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
    protected int HP, PlayerHP = 150;
    //--------------------------------------------------
    public AIBase()
    {
        for (int i = 0; i < 5; i++)
        {
            AiHandList.add(CardDeck.drawCard());
        }
        HP = 100;
    }
    //--------------------------------------------------
    public LinkedList<Card> playAI()
    {
        if (HP <= (PlayerHP - 20))
        {
            return DefensiveAI.DefensivePlay(AiHandList);
        }
        else
        {
            return AggressiveAI.AggressivePlay(AiHandList);
        }
    }
}