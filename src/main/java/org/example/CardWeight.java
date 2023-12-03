package org.example;

public class CardWeight implements Comparable<CardWeight> {
    Integer value;
    Integer amount;

    public CardWeight(Integer value, Integer amount) {
        this.value = value;
        this.amount = amount;
    }

       public Integer getAmount() {
        return amount;
    }

    @Override
    public int compareTo(CardWeight card) {
        return amount.compareTo(card.getAmount());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
