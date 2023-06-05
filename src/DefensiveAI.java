package src;/*
Name: Mateo Adebowale
Class: ICS4U0
Assignment: Animal Kingdom: Card Arena
Sub-Assignment: Defensive AI child object
Date: 2023-05-16
Description: This object sorts the AI's hand, analyses the values to find the highest health cards, then outputs
the newly organized hand to play the strongest defensive order.
*/
import java.util.LinkedList;
public class DefensiveAI extends AIBase
{
    protected static LinkedList<Card> Combo = new LinkedList<Card>();
    protected static int Combo_VP, order;
    //--------------------------------------------------
    public DefensiveAI ()
    {
        //empty
    }
    //--------------------------------------------------
    public static LinkedList<Card> Play(LinkedList<Card> Hand)
    {
        //--------------------------------------------------
        //Sort the array in ascending order using two for loops
        for (int i = 0; i < 5; i++) //loops for the amount of cards that can be held
        {
            for (int ii = i + 1; ii < 5; ii++) //loops for the remaining amount cards held
            {
                if (Hand.get(i).getHP() < Hand.get(ii).getHP()) //swap elements if not in order of health
                {
                    Card temp = Hand.get(i);
                    Hand.set(i, Hand.get(ii));
                    Hand.set(ii, temp);
                }
                else if (Hand.get(i).getHP() == Hand.get(ii).getHP()) //when the health are the same
                {
                    if (Hand.get(i).getVP() > Hand.get(ii).getVP()) //swap elements if not in order of value
                    {
                        Card temp = Hand.get(i);
                        Hand.set(i, Hand.get(ii));
                        Hand.set(ii, temp);
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++)
        {
            if (Combo_VP < 5)
            {
                if (Hand.get(i).getVP() + Combo_VP <= 5)
                {
                    Combo.add(Hand.get(i));
                    Combo_VP = Combo_VP + Hand.get(i).getVP();
                }
            }
            else
            {
                break;
            }
        }
        //--------------------------------------------------
        for (int i = 0; i < Combo.size(); i++)
        {
            for (int ii = i + 1; ii < Combo.size(); ii++)
            {
                if (Combo.get(i).getHP() < Combo.get(ii).getHP()) //swap elements if not in order
                {
                    Card temp = Combo.get(i);
                    Combo.set(i, Combo.get(ii));
                    Combo.set(ii, temp);
                }
                else if (Combo.get(i).getHP() == Combo.get(ii).getHP())
                {
                    if (Combo.get(i).getVP() < Combo.get(ii).getVP()) //swap elements if not in order
                    {
                        Card temp = Combo.get(i);
                        Combo.set(i, Combo.get(ii));
                        Combo.set(ii, temp);
                    }
                }
            }
        }
        //--------------------------------------------------
        for (int i = 0; i < Combo.size(); i++)
        {
            order = Hand.indexOf(Combo.get(i));
            Card temp = Hand.get(i);
            Hand.set(i, Combo.get(i));
            Hand.set(order, temp);
        }
        return Hand;
    }
}