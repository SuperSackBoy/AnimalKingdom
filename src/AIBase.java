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
    protected boolean Kill = false, Attack = false, Defend = false;
    protected int AiHP, playedVP = 0, ran, full, beat, current, direct, killable, v;
    private int AimaxHP = 250;
    //--------------------------------------------------
    public AIBase()
    {
        for (int i = 0; i < 5; i++)
        {
            AiHandList.add(draw());
        }
        AiHP = AimaxHP;
    }
    //--------------------------------------------------
    public void playAI()
    {
        Player Player = PanelManager.player;
        if (getHP() <= 0)
        {
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
            if (debugCodes.peek)
            {
                show();
            }
            playedVP = 0;
            ran = (int)((Math.random()*100));
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
                        Kill = true;
                    }
                }
                else
                {
                    killable = 100;
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
                                killable = 100;
                                old = v;
                            }
                        }
                    }
                    if ((current + KillShotAI.BestATK(AiHandList, full)) >= beat)
                    {
                        Kill = true;
                    }
                }
                //--------------------------------------------------Killshot end
                if (AiHP >= (PanelManager.player.getHP() - 20) || Kill)
                {
                    AiHandList = AggressiveAI.Play(AiHandList);
                    Attack = true;
                }
                else
                {
                    AiHandList = DefensiveAI.Play(AiHandList);
                    Defend = true;
                }
            }
            move();
            attack();
        }
    }
    //--------------------------------------------------
    private void move()
    {
        Player Player = PanelManager.player;
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
                                AiHandList.set(i, draw());
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
                                            AiHandList.set(i, draw());
                                            break b;
                                        }
                                        else if (iii == 4) //make check for lowest health
                                        {
                                            killable = 100;
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
                                                    AiHandList.set(i, draw());
                                                    break b;
                                                }
                                            }
                                        }
                                    }
                                    else if (iii == 4) //ditto as else if above
                                    {
                                        killable = 100;
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
                                                    AiHandList.set(i, draw());
                                                    break b;
                                                }
                                                else
                                                {
                                                    AICardManager.playCard(AiHandList.get(i), ii);
                                                    playedVP = playedVP + AiHandList.get(i).getVP();
                                                    AiHandList.set(i, draw());
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
        else if (Attack)
        {
            for (int i = 0; i < 5; i++)
            {
                if ((playedVP + AiHandList.get(i).getVP()) <= 5)
                {
                    killable = 100;
                    v = -1;
                    b: for (int ii = 0; ii < 5; ii++)
                    {
                        if (AICardManager.AIPlayed[ii] == null)
                        {
                            for (int iii = ii; iii < 5; iii++)
                            {
                                if (AICardManager.AIPlayed[iii] == null && Player.PlayerPlayedCards[iii] != null)
                                {
                                    if (Player.PlayerPlayedCards[iii].getCard().getHP() < killable)
                                    {
                                        killable = Player.PlayerPlayedCards[iii].getCard().getHP();
                                        v = iii;
                                    }
                                }
                                if (iii == 4)
                                {
                                    if (v != -1)
                                    {
                                        AICardManager.playCard(AiHandList.get(i), v);
                                        playedVP = playedVP + AiHandList.get(i).getVP();
                                        AiHandList.set(i, draw());
                                        break b;
                                    }
                                    else
                                    {
                                        AICardManager.playCard(AiHandList.get(i), ii);
                                        playedVP = playedVP + AiHandList.get(i).getVP();
                                        AiHandList.set(i, draw());
                                        break b;
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
            Attack = false;
        }
        else if (Defend)
        {
            for (int i = 0; i < 5; i++)
            {
                if ((playedVP + AiHandList.get(i).getVP()) <= 5)
                {
                    killable = 0;
                    v = -1;
                    b: for (int ii = 0; ii < 5; ii++)
                    {
                        if (AICardManager.AIPlayed[ii] == null)
                        {
                            for (int iii = ii; iii < 5; iii++)
                            {
                                if (AICardManager.AIPlayed[iii] == null && Player.PlayerPlayedCards[iii] != null)
                                {
                                    if (Player.PlayerPlayedCards[iii].getCard().getATK() > killable)
                                    {
                                        killable = Player.PlayerPlayedCards[iii].getCard().getATK();
                                        v = iii;
                                    }
                                }
                                if (iii == 4)
                                {
                                    if (v != -1)
                                    {
                                        AICardManager.playCard(AiHandList.get(i), v);
                                        playedVP = playedVP + AiHandList.get(i).getVP();
                                        AiHandList.set(i, draw());
                                        break b;
                                    }
                                    else
                                    {
                                        AICardManager.playCard(AiHandList.get(i), ii);
                                        playedVP = playedVP + AiHandList.get(i).getVP();
                                        AiHandList.set(i, draw());
                                        break b;
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
            Defend = false;
        }
        else
        {
            for (int i = 0; i < 5; i++)
            {
                if ((playedVP + AiHandList.get(i).getVP()) <= 5)
                {
                    v = -1;
                    for (int ii = 0; ii < 5; ii++)
                    {
                        if (AICardManager.AIPlayed[ii] == null)
                        {
                            while (v == -1)
                            {
                                ran = (int)(Math.random()*5);
                                if (AICardManager.AIPlayed[ran] == null)
                                {
                                    v = ran;
                                }
                            }
                            AICardManager.playCard(AiHandList.get(i), v);
                            playedVP = playedVP + AiHandList.get(i).getVP();
                            AiHandList.set(i, draw());
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
    public int getMaxHP()
    {
        return this.AimaxHP;
    }
    //--------------------------------------------------
    public void resetHP() { AiHP = AimaxHP; }
    //--------------------------------------------------
    public void show ()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println(AiHandList.get(i).getName() + " {HP" + AiHandList.get(i).getHP() + "/ATK" + AiHandList.get(i).getATK() + "/VP" + AiHandList.get(i).getVP() + "}");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void setMaxHP(int newValue) {
        AimaxHP = newValue;
    }
    public void setHP(int newValue)
    {
        AiHP = newValue;
    }
    //-----------------------c---------------------------
    public Card draw ()
    {
        Card pull = CardDeck.drawCard();
        while (pull.getName().equals("Mr. Jone"))
        {

            if (debugCodes.allJone)
            {
                pull = CardDeck.findCard("Canada Goose");
            }
            else
            {
                pull = CardDeck.drawCard();
            }
        }
        return pull;
    }
}