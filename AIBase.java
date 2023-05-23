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
    //note: these should not be static, static means the variable is global across all instances of the object
    //in this case, the ai is an object, don't access it with AIBase, access it through PanelManager.ai, or have it be public in main
    protected LinkedList<Card> AiHandList = new LinkedList<Card>();
    protected int HP, PlayerHP = 100, playedVP = 0;
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
    public void playAI()
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
    private void move ()
    {
        for (int i = 0; i < 5; i++)
        {
            if ((playedVP + AiHandList.get(i).getVP()) <= 5)
            {
                for (int ii = 0; ii < 5; ii++)
                {
                    if (AICardManager.AIPlayed[ii] != null)
                    {
                        //AICardManager.playCard(i, ii); //TODO should draw a new card
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

    public int getHP() {
        return this.HP;
    }
}