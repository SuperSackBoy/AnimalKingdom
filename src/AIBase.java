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
    protected boolean Kill = false;
    protected static int AiHP, playedVP = 0, ran, full, beat, current, direct, killable, v;
    private static int AimaxHP = 250;
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
            /*
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {}
            },700,100);
            */
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Main.frame.getContentPane().removeAll();
                        Main.frame.add(Main.Wframe);
                        Main.Wframe.loadBackgroundImage(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else
        {
            playedVP = 0;
            //ran = (int)((Math.random()*100) + 1);
            ran = 20;
            if (ran > 0 && ran < 16)
            {
                AiHandList = RecklessAI.Play(AiHandList);
            }
            else
            {
                //--------------------------------------------------Killshot start
                full = 0;
                beat = PanelManager.player.getHP();
                current = 0;
                direct = 0;
                for (int i = 0; i < 5; i++)
                {
                    if (AICardManager.AIPlayed[i] != null)
                    {
                        full++;
                        if (Player.PlayerPlayedCards[i] == null)
                        {
                            current = current + AICardManager.AIPlayed[i].getCard().getATK();
                        }
                        else
                        {
                            if (AICardManager.AIPlayed[i].getCard().getATK() > Player.PlayerPlayedCards[i].getCard().getHP())
                            {
                                current = current + (AICardManager.AIPlayed[i].getCard().getATK() - Player.PlayerPlayedCards[i].getCard().getHP());
                            }
                        }
                    }
                    else if (Player.PlayerPlayedCards[i] == null)
                    {
                        direct++;
                    }
                }
                KillShotAI.BestATK(AiHandList, full);
                if (direct >= KillShotAI.getAmt())
                {
                    if ((current + KillShotAI.BestATK(AiHandList, full)) >= beat)
                    {
                        System.out.println("DIE! DIE! DIE!");
                        Kill = true;
                    }
                }
                else
                {
                    killable = 99;
                    v = -1;
                    int old = -1;
                    for (int i = 0; i < (KillShotAI.getAmt() - direct); i++)
                    {
                        for (int ii = 0; ii < 5; ii++)
                        {
                            if (AICardManager.AIPlayed[ii] == null && Player.PlayerPlayedCards[ii] != null && Player.PlayerPlayedCards[ii].getCard().getHP() < killable)
                            {
                                if (i == 0)
                                {
                                    killable = Player.PlayerPlayedCards[ii].getCard().getHP();
                                    v = ii;
                                }
                                else if (ii != old && Player.PlayerPlayedCards[ii].getCard().getHP() >= Player.PlayerPlayedCards[old].getCard().getHP())
                                {
                                    killable = Player.PlayerPlayedCards[ii].getCard().getHP();
                                    v = ii;
                                }
                            }
                            if (ii == 4 && v >= 0)
                            {
                                beat = beat + Player.PlayerPlayedCards[v].getCard().getHP();
                                killable = 99;
                                old = v;
                            }
                        }
                    }
                    if ((current + KillShotAI.BestATK(AiHandList, full)) >= beat)
                    {
                        System.out.println("DIE! DIE! DIE!");
                        Kill = true;
                    }
                }
                //--------------------------------------------------Killshot end
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
        if (Kill)
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
                                            killable = 99;
                                            v = -1;
                                            for (int iv = 0; iv < 5; iv++)
                                            {
                                                if (AICardManager.AIPlayed[iv] == null)
                                                {
                                                    if (Player.PlayerPlayedCards[iv].getCard().getHP() < killable)
                                                    {
                                                        killable = Player.PlayerPlayedCards[iv].getCard().getHP();
                                                        v = iv;
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
                                        killable = 99;
                                        v = -1;
                                        for (int iv = 0; iv < 5; iv++)
                                        {
                                            if (AICardManager.AIPlayed[iv] == null)
                                            {
                                                if (Player.PlayerPlayedCards[iv].getCard().getHP() < killable)
                                                {
                                                    killable = Player.PlayerPlayedCards[iv].getCard().getHP();
                                                    v = iv;
                                                }
                                            }
                                            if (iv == 4)
                                            {
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
            Kill = false;
        }
        else
        {
            for (int i = 0; i < 5; i++)
            {
                if ((playedVP + AiHandList.get(i).getVP()) <= 5)
                {
                    for (int ii = 0; ii < 5; ii++)
                    {
                        if (AICardManager.AIPlayed[ii] == null)
                            if (AICardManager.AIPlayed[ii] == null)
                            {
                                AICardManager.playCard(AiHandList.get(i), ii);
                                playedVP = playedVP + AiHandList.get(i).getVP();
                                AiHandList.set(i, CardDeck.drawCard());
                                break;
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

    public static void setMaxHP(int newValue) {
        AimaxHP = newValue;
    }
    public static void setHP(int newValue)
    {
        AiHP = newValue;
    }
}