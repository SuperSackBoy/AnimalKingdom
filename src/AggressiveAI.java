package src;/*
Name: Mateo Adebowale
Class: ICS4U0
Assignment: Animal Kingdom: Card Arena
Sub-Assignment: Aggressive AI child object
Date: 2023-05-09
Description: This object sorts the AI's hand, analyses the values to come up with the best possible attacking combination, then outputs
the newly organized hand to play the best attacking order.
*/
import java.util.LinkedList;
public class AggressiveAI extends AIBase
{
    protected static LinkedList<Card> Combo = new LinkedList<Card>();
    protected static int Best_ATK, Compare_ATK, Best_VP, Compare_VP, order;
    //--------------------------------------------------
    public AggressiveAI()
    {
        //empty
    }
    //--------------------------------------------------
    public static LinkedList<Card> Play(LinkedList<Card> Hand)
    {
        //--------------------------------------------------
        //Sort the array in ascending order using two for loops
        for (int i = 0; i < 5; i++)
        {
            for (int ii = i + 1; ii < 5; ii++)
            {
                if (Hand.get(i).getVP() > Hand.get(ii).getVP()) //swap elements if not in order
                {
                    Card temp = Hand.get(i);
                    Hand.set(i, Hand.get(ii));
                    Hand.set(ii, temp);
                }
                else if (Hand.get(i).getVP() == Hand.get(ii).getVP())
                {
                    if (Hand.get(i).getATK() < Hand.get(ii).getATK()) //swap elements if not in order
                    {
                        Card temp = Hand.get(i);
                        Hand.set(i, Hand.get(ii));
                        Hand.set(ii, temp);
                    }
                }
            }
        }
        //--------------------------------------------------
        Best_ATK = 0;
        Best_VP = 0;
        for (int i = 0; i < 5; i++)
        {
            if (Hand.get(i).getVP() == 1)
            {
                if (comparing(1, Hand.get(i).getATK(), 0, 0, 0, 0, Hand.get(i).getVP(), 0, 0, 0, 0))
                {
                    Combo.clear();
                    Combo.add(Hand.get(i));
                }
                for (int ii = i + 1; ii < 5; ii++)
                {
                    if (Hand.get(ii).getVP() == 1)
                    {
                        if (comparing(2, Hand.get(i).getATK(), Hand.get(ii).getATK(), 0, 0, 0, Hand.get(i).getVP(), Hand.get(ii).getVP(), 0, 0, 0))
                        {
                            Combo.clear();
                            Combo.add(Hand.get(i));
                            Combo.add(Hand.get(ii));
                        }
                        for (int iii = ii + 1; iii < 5; iii++)
                        {
                            if (Hand.get(iii).getVP() == 1)
                            {
                                if (comparing(3, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), 0, 0, Hand.get(i).getVP(), Hand.get(ii).getVP(), Hand.get(iii).getVP(), 0, 0))
                                {
                                    Combo.clear();
                                    Combo.add(Hand.get(i));
                                    Combo.add(Hand.get(ii));
                                    Combo.add(Hand.get(iii));
                                }
                                for (int iv = iii + 1; iv < 5; iv++)
                                {
                                    if (Hand.get(iv).getVP() == 1)
                                    {
                                        if (comparing(4, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), Hand.get(iv).getATK(), 0, Hand.get(i).getVP(), Hand.get(ii).getVP(), Hand.get(iii).getVP(), Hand.get(iv).getVP(), 0))
                                        {
                                            Combo.clear();
                                            Combo.add(Hand.get(i));
                                            Combo.add(Hand.get(ii));
                                            Combo.add(Hand.get(iii));
                                            Combo.add(Hand.get(iv));
                                        }
                                        for (int v = iv + 1; v < 5; v++)
                                        {
                                            if (Hand.get(v).getVP() == 1)
                                            {
                                                if (comparing(5, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), Hand.get(iv).getATK(), Hand.get(v).getATK(), Hand.get(i).getVP(), Hand.get(ii).getVP(), Hand.get(iii).getVP(), Hand.get(iv).getVP(), Hand.get(v).getATK()))
                                                {
                                                    Combo.clear();
                                                    Combo.add(Hand.get(i));
                                                    Combo.add(Hand.get(ii));
                                                    Combo.add(Hand.get(iii));
                                                    Combo.add(Hand.get(iv));
                                                    Combo.add(Hand.get(v));
                                                }
                                            }
                                        }
                                    }
                                    else if (Hand.get(iv).getVP() == 2)
                                    {
                                        if (comparing(4, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), Hand.get(iv).getATK(), 0, Hand.get(i).getVP(), Hand.get(ii).getVP(), Hand.get(iii).getVP(), Hand.get(iv).getVP(), 0))
                                        {
                                            Combo.clear();
                                            Combo.add(Hand.get(i));
                                            Combo.add(Hand.get(ii));
                                            Combo.add(Hand.get(iii));
                                            Combo.add(Hand.get(iv));
                                        }
                                    }
                                }
                            }
                            else if (Hand.get(iii).getVP() == 2)
                            {
                                if (comparing(3, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), 0, 0, Hand.get(i).getVP(), Hand.get(ii).getVP(), Hand.get(iii).getVP(), 0, 0))
                                {
                                    Combo.clear();
                                    Combo.add(Hand.get(i));
                                    Combo.add(Hand.get(ii));
                                    Combo.add(Hand.get(iii));
                                }
                            }
                            else if (Hand.get(iii).getVP() == 3)
                            {
                                if (comparing(3, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), 0, 0, Hand.get(i).getVP(), Hand.get(ii).getVP(), Hand.get(iii).getVP(), 0, 0))
                                {
                                    Combo.clear();
                                    Combo.add(Hand.get(i));
                                    Combo.add(Hand.get(ii));
                                    Combo.add(Hand.get(iii));
                                }
                            }
                        }
                    }
                    else if (Hand.get(ii).getVP() == 2)
                    {
                        if (comparing(2, Hand.get(i).getATK(), Hand.get(ii).getATK(), 0, 0, 0, Hand.get(i).getVP(), Hand.get(ii).getVP(), 0, 0, 0))
                        {
                            Combo.clear();
                            Combo.add(Hand.get(i));
                            Combo.add(Hand.get(ii));
                        }
                        for (int iii = ii + 1; iii < 5; iii++)
                        {
                            if (Hand.get(iii).getVP() == 2)
                            {
                                if (comparing(3, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), 0, 0, Hand.get(i).getVP(), Hand.get(ii).getVP(), Hand.get(iii).getVP(), 0, 0))
                                {
                                    Combo.clear();
                                    Combo.add(Hand.get(i));
                                    Combo.add(Hand.get(ii));
                                    Combo.add(Hand.get(iii));
                                }
                            }
                        }
                    }
                    else if (Hand.get(ii).getVP() == 3)
                    {
                        if (comparing(2, Hand.get(i).getATK(), Hand.get(ii).getATK(), 0, 0, 0, Hand.get(i).getVP(), Hand.get(ii).getVP(), 0, 0, 0))
                        {
                            Combo.clear();
                            Combo.add(Hand.get(i));
                            Combo.add(Hand.get(ii));
                        }
                    }
                    else if (Hand.get(ii).getVP() == 4)
                    {
                        if (comparing(2, Hand.get(i).getATK(), Hand.get(ii).getATK(), 0, 0, 0, Hand.get(i).getVP(), Hand.get(ii).getVP(), 0, 0, 0))
                        {
                            Combo.clear();
                            Combo.add(Hand.get(i));
                            Combo.add(Hand.get(ii));
                        }
                    }
                }
            }
            else if (Hand.get(i).getVP() == 2)
            {
                if (comparing(1, Hand.get(i).getATK(), 0, 0, 0, 0, Hand.get(i).getVP(), 0, 0, 0, 0))
                {
                    Combo.clear();
                    Combo.add(Hand.get(i));
                }
                for (int ii = i + 1; ii < 5; ii++)
                {
                    if (Hand.get(ii).getVP() == 2)
                    {
                        if (comparing(2, Hand.get(i).getATK(), Hand.get(ii).getATK(), 0, 0, 0, Hand.get(i).getVP(), Hand.get(ii).getVP(), 0, 0, 0))
                        {
                            Combo.clear();
                            Combo.add(Hand.get(i));
                            Combo.add(Hand.get(ii));
                        }
                    }
                    else if (Hand.get(ii).getVP() == 3)
                    {
                        if (comparing(2, Hand.get(i).getATK(), Hand.get(ii).getATK(), 0, 0, 0, Hand.get(i).getVP(), Hand.get(ii).getVP(), 0, 0, 0))
                        {
                            Combo.clear();
                            Combo.add(Hand.get(i));
                            Combo.add(Hand.get(ii));
                        }
                    }
                }
            }
            else if (Hand.get(i).getVP() == 3)
            {
                if (comparing(1, Hand.get(i).getATK(), 0, 0, 0, 0, Hand.get(i).getVP(), 0, 0, 0, 0))
                {
                    Combo.clear();
                    Combo.add(Hand.get(i));
                }
            }
            else if (Hand.get(i).getVP() == 4)
            {
                if (comparing(1, Hand.get(i).getATK(), 0, 0, 0, 0, Hand.get(i).getVP(), 0, 0, 0, 0))
                {
                    Combo.clear();
                    Combo.add(Hand.get(i));
                }
            }
            else if (Hand.get(i).getVP() == 5)
            {
                if (comparing(1, Hand.get(i).getATK(), 0, 0, 0, 0, Hand.get(i).getVP(), 0, 0, 0, 0))
                {
                    Combo.clear();
                    Combo.add(Hand.get(i));
                }
            }
        }
        //--------------------------------------------------
        if (Combo.size() > 1)
        {
            for (int i = 0; i < Combo.size(); i++)
            {
                for (int ii = i + 1; ii < Combo.size(); ii++)
                {
                    if (Combo.get(i).getATK() < Combo.get(ii).getATK()) //swap elements if not in order
                    {
                        Card temp = Combo.get(i);
                        Combo.set(i, Combo.get(ii));
                        Combo.set(ii, temp);
                    }
                    else if (Combo.get(i).getATK() == Combo.get(ii).getATK())
                    {
                        if (Combo.get(i).getHP() < Combo.get(ii).getHP()) //swap elements if not in order
                        {
                            Card temp = Combo.get(i);
                            Combo.set(i, Combo.get(ii));
                            Combo.set(ii, temp);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < Combo.size(); i++)
        {
            order = Hand.indexOf(Combo.get(i));
            Card temp = Hand.get(i);
            Hand.set(i, Combo.get(i));
            Hand.set(order, temp);
        }
        return Hand;
    }
    //--------------------------------------------------
    public static boolean comparing (int amt, int ATK1, int ATK2, int ATK3, int ATK4, int ATK5, int VP1, int VP2, int VP3, int VP4, int VP5)
    {
        Compare_ATK = ATK1 + ATK2 + ATK3 + ATK4 + ATK5;
        Compare_VP = VP1 + VP2 + VP3 + VP4 + VP5;
        if (Compare_ATK > Best_ATK)
        {
            Best_ATK = Compare_ATK;
            Best_VP = Compare_VP;
            return true;
        }
        else if (Compare_ATK == Best_ATK)
        {
            if (Compare_VP > Best_VP)
            {
                Best_ATK = Compare_ATK;
                Best_VP = Compare_VP;
                return true;
            }
            else if (Compare_VP == Best_VP && Combo.size() > amt)
            {
                Best_ATK = Compare_ATK;
                Best_VP = Compare_VP;
                return true;
            }
        }
        return false;
    }
}