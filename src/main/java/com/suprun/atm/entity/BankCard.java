package com.suprun.atm.entity;


/**
 * {@code BankCard} class represents a BankCard entity.
 *
 * @author Philip Suprun
 */
public class BankCard {

    private String pinCode;
    private String serialNumber;
    private boolean isActive;
    private Double cardBalance;
    private Long blockTime;

    private BankCard() {
    }

    public static BankCardBuilder builder() {
        return new BankCard().new BankCardBuilder();
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Double getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(Double cardBalance) {
        this.cardBalance = cardBalance;
    }

    public Long getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(Long blockTime) {
        this.blockTime = blockTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankCard bankCard = (BankCard) o;

        if (isActive != bankCard.isActive) return false;
        if (pinCode != null ? !pinCode.equals(bankCard.pinCode) : bankCard.pinCode != null) return false;
        if (serialNumber != null ? !serialNumber.equals(bankCard.serialNumber) : bankCard.serialNumber != null)
            return false;
        if (cardBalance != null ? !cardBalance.equals(bankCard.cardBalance) : bankCard.cardBalance != null)
            return false;
        return blockTime != null ? blockTime.equals(bankCard.blockTime) : bankCard.blockTime == null;
    }

    @Override
    public int hashCode() {
        int result = pinCode != null ? pinCode.hashCode() : 0;
        result = 31 * result + (serialNumber != null ? serialNumber.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (cardBalance != null ? cardBalance.hashCode() : 0);
        result = 31 * result + (blockTime != null ? blockTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BankCard{");
        sb.append("pinCode='").append(pinCode).append('\'');
        sb.append(", serialNumber='").append(serialNumber).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append(", cardBalance=").append(cardBalance);
        sb.append(", blockDate=").append(blockTime);
        sb.append('}');
        return sb.toString();
    }

    public class BankCardBuilder {
        private BankCardBuilder() {

        }

        public BankCardBuilder setPinCode(String pinCode) {
            BankCard.this.pinCode = pinCode;
            return this;
        }

        public BankCardBuilder setSerialNumber(String serialNumber) {
            BankCard.this.serialNumber = serialNumber;
            return this;
        }

        public BankCardBuilder setIsActive(boolean isActive) {
            BankCard.this.isActive = isActive;
            return this;
        }

        public BankCardBuilder setCardBalance(Double cardBalance) {
            BankCard.this.cardBalance = cardBalance;
            return this;
        }

        public BankCardBuilder setBlockTime(Long blockTime) {
            BankCard.this.blockTime = blockTime;
            return this;
        }

        public BankCardBuilder of(BankCard bankCard) {
            BankCard.this.pinCode = bankCard.pinCode;
            BankCard.this.serialNumber = bankCard.serialNumber;
            BankCard.this.isActive = bankCard.isActive;
            BankCard.this.cardBalance = bankCard.cardBalance;
            BankCard.this.blockTime = bankCard.blockTime;
            return this;
        }

        public BankCard build() {
            return BankCard.this;
        }
    }
}
