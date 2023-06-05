package src;/*
Name: Mateo Adebowale
Class: ICS4U0
Assignment: Animal Kingdom: Card Arena
Sub-Assignment: Reckless AI child object
Date: 2023-05-23
Description: This object randomly sorts the AI's hand and then outputs the newly organized hand so that the play is truly random.
*/
import java.util.LinkedList;
public class RecklessAI extends AIBase
{
    protected static LinkedList<Card> Combo = new LinkedList<Card>();
    protected static int ran;
    //--------------------------------------------------
    public RecklessAI ()
    {
        //empty
    }
    //--------------------------------------------------
    public static LinkedList<Card> Play(LinkedList<Card> Hand)
    {
        for (int i = 0; i < 5; i++)
        {
            Combo.add(null);
        }
        for (int i = 0; i < 5; i++)
        {
            ran = (int)(Math.random()*5);
            if (Combo.get(ran) == null)
            {
                Combo.set(ran, Hand.get(i));
            }
            else
            {
                i--;
            }
        }
        Hand = Combo;
        return Hand;
    }
}