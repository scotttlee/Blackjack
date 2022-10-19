//Scott Lee
//CS1400
//Assignment 5
//April 6 2021

public class Card {
    
    private String face;
    private String suit;
    private int value;

    public Card(String f, String s, int v)
    {
        face = f;
        suit = s;
        value = v;
    }

    public Card() {
    }

    public String getFace()
    {
        return face;
    }

    public String getSuit()
    {
        return suit;
    }

    public int getValue()
    {
        return value;
    }

}
