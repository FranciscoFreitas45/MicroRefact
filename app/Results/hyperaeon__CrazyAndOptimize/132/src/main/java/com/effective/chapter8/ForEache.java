package com.effective.chapter8;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
public class ForEache {

 static  Collection<Suit> suits;

 static  Collection<Rank> ranks;


public void main(String[] args){
    List<Card> deck = new ArrayList<Card>();
    for (Iterator<Suit> i = suits.iterator(); i.hasNext(); ) {
        for (Iterator<Rank> j = ranks.iterator(); j.hasNext(); ) {
            deck.add(new Card(i.next(), j.next()));
        }
    }
    for (Suit suit : suits) {
        for (Rank rank : ranks) {
            deck.add(new Card(suit, rank));
        }
    }
}


}