package src;/*
Name: Mateo Adebowale
Class: ICS4U0
Assignment: Animal Kingdom: Card Arena
Sub-Assignment: AI base object
Date: 2023-05-16
Description: The main hub where all the AI values will be stored and strategy types will be decided
*/
import java.awt.*;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class AIBase
{
    //--------------------------------------------------
    //note: these should not be static, static means the variable is global across all instances of the object
    //in this case, the ai is an object, don't access it with AIBase, access it through PanelManager.ai, or have it be public in main
    protected LinkedList<Card> AiHandList = new LinkedList<Card>();
    protected static int AiHP, playedVP = 0, ran;
    private static int AimaxHP = 150;
    //--------------------------------------------------
    public AIBase()
    {
        for (int i = 0; i < 5; i++)
        {
            AiHandList.add(CardDeck.drawCard());
        }
        AiHP = AimaxHP;
    }
    //--------------------------------------------------
    public void playAI()
    {
        if (getHP() <= 0)
        {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Main.frame.getContentPane().removeAll();
                        Main.frame.add(Main.Wframe);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else
        {
            playedVP = 0;
            ran = (int)((Math.random()*100) + 1);
            if (ran > 0 && ran < 16)
            {
                AiHandList = RecklessAI.Play(AiHandList);
            }
            else
            {
                if (AiHP <= (PanelManager.player.getHP() - 20))
                {
                    //AiHandList = DefensiveAI.Play(AiHandList);
                    AiHandList = AggressiveAI.Play(AiHandList);
                }
                else
                {
                    AiHandList = AggressiveAI.Play(AiHandList);
                }
            }
            move();
            attack();
        }
    }
    //--------------------------------------------------
    private void move()
    {
        for (int i = 0; i < 5; i++) //checking AI hand
        {
            if ((playedVP + AiHandList.get(i).getVP()) <= 5)
            {
                b: for (int ii = 0; ii < 5; ii++) //AI comparisons
                {
                    if (AICardManager.AIPlayed[ii] == null)
                    {
                        if (Player.PlayerPlayedCards[ii] == null)
                        {
                            AICardManager.playCard(AiHandList.get(i), ii);
                            playedVP = playedVP + AiHandList.get(i).getVP();
                            AiHandList.set(i, CardDeck.drawCard());
                            break;
                        }
                        else
                        {
                            for (int iii = ii; iii < 5; iii++)
                            {
                                if (AICardManager.AIPlayed[iii] == null)
                                {
                                    if (Player.PlayerPlayedCards[iii] == null)
                                    {
                                        AICardManager.playCard(AiHandList.get(i), iii);
                                        playedVP = playedVP + AiHandList.get(i).getVP();
                                        AiHandList.set(i, CardDeck.drawCard());
                                        break b;
                                    }
                                    else if (iii == 4) //make check for lowest health
                                    {
                                        int killable = 99, v = -1;
                                        for (int iv = 0; iv < 5; iv++)
                                        {
                                            if (AICardManager.AIPlayed[iv] == null)
                                            {
                                                if (Player.PlayerPlayedCards[iv].getCard().getHP() < killable)
                                                {
                                                    killable = Player.PlayerPlayedCards[iv].getCard().getHP();
                                                    v = iv;
                                                    System.out.println("Best spot at " + v);
                                                }
                                            }
                                            if (iv == 4)
                                            {
                                                AICardManager.playCard(AiHandList.get(i), v);
                                                playedVP = playedVP + AiHandList.get(i).getVP();
                                                AiHandList.set(i, CardDeck.drawCard());
                                                break b;
                                            }
                                        }
                                    }
                                }
                                else if (iii == 4) //ditto as else if above
                                {
                                    int killable = 99, v = -1;
                                    for (int iv = 0; iv < 5; iv++)
                                    {
                                        if (AICardManager.AIPlayed[iv] == null)
                                        {
                                            if (Player.PlayerPlayedCards[iv].getCard().getHP() < killable)
                                            {
                                                killable = Player.PlayerPlayedCards[iv].getCard().getHP();
                                                v = iv;
                                                System.out.println("Best spot at " + v + " with " + killable + "HP");
                                            }
                                        }
                                        if (iv == 4)
                                        {
                                            System.out.println("Moving on... " + v);
                                            if (v != -1)
                                            {
                                                AICardManager.playCard(AiHandList.get(i), v);
                                                playedVP = playedVP + AiHandList.get(i).getVP();
                                                AiHandList.set(i, CardDeck.drawCard());
                                                break b;
                                            }
                                            else
                                            {
                                                AICardManager.playCard(AiHandList.get(i), ii);
                                                playedVP = playedVP + AiHandList.get(i).getVP();
                                                AiHandList.set(i, CardDeck.drawCard());
                                                break b;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                break;
            }
        }
    }
    //--------------------------------------------------
    protected void attack() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int x;
            @Override
            public void run() {
                if(AICardManager.AIPlayed[x] != null )
                    AICardManager.AIPlayed[x].attack();
                x++;
                if(x > AICardManager.AIPlayed.length-1) {
                    PanelManager.player.resetVP();
                    PanelManager.board.moveDown();
                    PanelManager.endTurnButton.setEnabled(true);
                    this.cancel();
                }
            }
        },700,100);
    }
    //--------------------------------------------------
    public int getHP()
    {
        return this.AiHP;
    }
    //--------------------------------------------------
    //--------------------------------------------------
    public int getMaxHP()
    {
        return this.AimaxHP;
    }
    //--------------------------------------------------
    public static void resetHP() { AiHP = AimaxHP; }
    //--------------------------------------------------
    public void show ()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println(AiHandList.get(i).getName() + " {" + AiHandList.get(i).getHP() + "/" + AiHandList.get(i).getATK() + "/" + AiHandList.get(i).getVP() + "}");
        }
    }
}