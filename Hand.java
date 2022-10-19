//Scott Lee
//CS1400
//Assignment 5
//April 6 2021

import java.util.ArrayList;

public class Hand 
{
    private ArrayList<Card> handOfCards = new ArrayList<Card>();

    public void addCard(Card inputCard)
    {
        handOfCards.add(inputCard);
    }

    public int calculateHandValue()
    {
        int total = 0;
        for(int i = 0; i < handOfCards.size(); i++)
        {
            total = total + handOfCards.get(i).getValue();
        }
        return total;
    }

    public int getSize()
    {
        return handOfCards.size();
    }
}
