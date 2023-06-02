/*
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
    protected static int AiHP, playedVP = 0, ran, full, power, blocks, beat;
    private static final int AimaxHP = 150;
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
        power = full = blocks = 0;
        beat = Player.getHP();
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
            for (int i = 0; i < 5; i++)
            {
                if(AICardManager.AIPlayed[i] != null)
                {
                    if (Player.PlayerPlayedCards[i] != null)
                    {
                        if ((Player.PlayerPlayedCards[i].getCard().getHP() - AICardManager.AIPlayed[i].getCard().getATK()) < 0)
                        {
                            power = power + (AICardManager.AIPlayed[i].getCard().getATK() - Player.PlayerPlayedCards[i].getCard().getHP());
                        }
                    }
                    else
                    {
                        power = power + AICardManager.AIPlayed[i].getCard().getATK();
                    }
                    full++;
                }
                if(Player.PlayerPlayedCards[i] != null)
                {
                    beat = beat + Player.PlayerPlayedCards[i].getCard().getHP();
                    blocks++;
                }
            }
            System.out.println("Full spaces: " + full + "\nCurrent: " + power + "\nPlaying: " + KillShotAI.BestATK(AiHandList, full, power));
            System.out.println("User has " + blocks + " on the field.\nAttack has to be over " + beat);
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
        for (int i = 0; i < 5; i++)
        {
            if ((playedVP + AiHandList.get(i).getVP()) <= 5)
            {
                b: for (int ii = 0; ii < 5; ii++)
                {
                    if (AICardManager.AIPlayed[ii] == null)
                    {
                        System.out.println(ii);
                        if (Player.PlayerPlayedCards[ii] == null)
                        {
                            AICardManager.playCard(AiHandList.get(i), ii);
                            playedVP = playedVP + AiHandList.get(i).getVP();
                            AiHandList.set(i, CardDeck.drawCard());
                            System.out.println("No issues");
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
                                        System.out.println("Null at " + iii);
                                        break b;
                                    }
                                    else if (iii == 4) //make check for lowest health
                                    {
                                        AICardManager.playCard(AiHandList.get(i), ii);
                                        playedVP = playedVP + AiHandList.get(i).getVP();
                                        AiHandList.set(i, CardDeck.drawCard());
                                        System.out.println("No nulls");
                                        break b;
                                    }
                                }
                                else if (iii == 4) //ditto as else if above
                                {
                                    AICardManager.playCard(AiHandList.get(i), ii);
                                    playedVP = playedVP + AiHandList.get(i).getVP();
                                    AiHandList.set(i, CardDeck.drawCard());
                                    System.out.println("No nulls");
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
    }
    //--------------------------------------------------
    private void attack() {
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