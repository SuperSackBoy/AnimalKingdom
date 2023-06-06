package src;

import java.util.LinkedList;

public class KillShotAI {
    protected static LinkedList<Card> Combo = new LinkedList<Card>();
    protected static int Best_ATK, Best_amt, Compare_ATK, Compare_amt;
    //--------------------------------------------------
    public KillShotAI ()
    {
        //empty
    }
    //--------------------------------------------------
    public LinkedList<Card> Play(LinkedList<Card> Hand)
    {
        //here
        return Combo;
    }
    //--------------------------------------------------
    public static int BestATK(LinkedList<Card> Hand, int cards, int current)
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
        Best_amt = 0;
        for (int i = 0; i < 5; i++)
        {
            if (Hand.get(i).getVP() == 1)
            {
                comparing(cards, 1, Hand.get(i).getATK(), 0, 0, 0, 0);
                for (int ii = i + 1; ii < 5; ii++)
                {
                    if (Hand.get(ii).getVP() == 1)
                    {
                        comparing(cards, 2, Hand.get(i).getATK(), Hand.get(ii).getATK(), 0, 0, 0);
                        for (int iii = ii + 1; iii < 5; iii++)
                        {
                            if (Hand.get(iii).getVP() == 1)
                            {
                                comparing(cards, 3, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), 0, 0);
                                for (int iv = iii + 1; iv < 5; iv++)
                                {
                                    if (Hand.get(iv).getVP() == 1)
                                    {
                                        comparing(cards, 4, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), Hand.get(iv).getATK(), 0);
                                        for (int v = iv + 1; v < 5; v++)
                                        {
                                            if (Hand.get(v).getVP() == 1)
                                            {
                                                comparing(cards, 5, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), Hand.get(iv).getATK(), Hand.get(v).getATK());
                                            }
                                        }
                                    }
                                    else if (Hand.get(iv).getVP() == 2)
                                    {
                                        comparing(cards, 4, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), Hand.get(iv).getATK(), 0);
                                    }
                                }
                            }
                            else if (Hand.get(iii).getVP() == 2)
                            {
                                comparing(cards, 3, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), 0, 0);
                            }
                            else if (Hand.get(iii).getVP() == 3)
                            {
                                comparing(cards, 3, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), 0, 0);
                            }
                        }
                    }
                    else if (Hand.get(ii).getVP() == 2)
                    {
                        comparing(cards, 2, Hand.get(i).getATK(), Hand.get(ii).getATK(), 0, 0, 0);
                        for (int iii = ii + 1; iii < 5; iii++)
                        {
                            if (Hand.get(iii).getVP() == 2)
                            {
                                comparing(cards, 3, Hand.get(i).getATK(), Hand.get(ii).getATK(), Hand.get(iii).getATK(), 0, 0);
                            }
                        }
                    }
                    else if (Hand.get(ii).getVP() == 3)
                    {
                        comparing(cards, 2, Hand.get(i).getATK(), Hand.get(ii).getATK(), 0, 0, 0);
                    }
                    else if (Hand.get(ii).getVP() == 4)
                    {
                        comparing(cards, 2, Hand.get(i).getATK(), Hand.get(ii).getATK(), 0, 0, 0);
                    }
                }
            }
            else if (Hand.get(i).getVP() == 2)
            {
                comparing(cards, 1, Hand.get(i).getATK(), 0, 0, 0, 0);
                for (int ii = i + 1; ii < 5; ii++)
                {
                    if (Hand.get(ii).getVP() == 2)
                    {
                        comparing(cards, 2, Hand.get(i).getATK(), Hand.get(ii).getATK(), 0, 0, 0);
                    }
                    else if (Hand.get(ii).getVP() == 3)
                    {
                        comparing(cards, 2, Hand.get(i).getATK(), Hand.get(ii).getATK(), 0, 0, 0);
                    }
                }
            }
            else if (Hand.get(i).getVP() == 3)
            {
                comparing(cards, 1, Hand.get(i).getATK(), 0, 0, 0, 0);
            }
            else if (Hand.get(i).getVP() == 4)
            {
                comparing(cards, 1, Hand.get(i).getATK(), 0, 0, 0, 0);
            }
            else if (Hand.get(i).getVP() == 5)
            {
                comparing(cards, 1, Hand.get(i).getATK(), 0, 0, 0, 0);
            }
        }
        return Best_ATK;
    }
    //--------------------------------------------------
    public static int getAmt()
    {
        return Best_amt;
    }
    //--------------------------------------------------
    public static void comparing (int cards, int amt, int ATK1, int ATK2, int ATK3, int ATK4, int ATK5)
    {
        if ((cards + amt) <= 5)
        {
            Compare_ATK = ATK1 + ATK2 + ATK3 + ATK4 + ATK5;
            if (Compare_ATK > Best_ATK)
            {
                Best_ATK = Compare_ATK;
            }
            else if (Compare_ATK == Best_ATK)
            {
                if (amt < Best_amt)
                {
                    Best_ATK = Compare_ATK;
                    Best_amt = amt;
                }
            }
        }
    }
}
