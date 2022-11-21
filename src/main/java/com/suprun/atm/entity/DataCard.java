package com.suprun.atm.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code DataCard} class represents a DataCard entity.
 *
 * @author Philip Suprun
 */
public class DataCard {
    private Map<String, BankCard> cardList = new HashMap<>();

    private DataCard() {

    }

    public static DataCardBuilder builder() {
        return new DataCard().new DataCardBuilder();
    }

    public Map<String, BankCard> getCardList() {
        return cardList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataCard dataCard = (DataCard) o;

        return cardList != null ? cardList.equals(dataCard.cardList) : dataCard.cardList == null;
    }

    @Override
    public int hashCode() {
        return cardList != null ? cardList.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DataCard{");
        sb.append("cardList=").append(cardList);
        sb.append('}');
        return sb.toString();
    }

    public class DataCardBuilder {
        private DataCardBuilder() {

        }

        public DataCardBuilder setCardList(Map<String, BankCard> cardList) {
            DataCard.this.cardList = cardList;
            return this;
        }

        public DataCardBuilder of(DataCard dataCard) {
            DataCard.this.cardList = cardList;
            return this;
        }

        public DataCard build() {
            return DataCard.this;
        }
    }

    private BankCard addBankCard(BankCard bankCard) {
        String serialNumber = bankCard.getSerialNumber();
        cardList.put(serialNumber, bankCard);
        return cardList.put(serialNumber, bankCard);
    }
}
