package com.suprun.atm.entity;

/**
 * {@code Atm} class represents a Atm entity.
 *
 * @author Philip Suprun
 */
public class Atm {
    private String AtmNumber;
    private Double remainingBalance;
    private Double topUpLimit;

    private Atm() {

    }

    public Atm(String atmNumber, Double remainingBalance, Double topUpLimit) {
        AtmNumber = atmNumber;
        this.remainingBalance = remainingBalance;
        this.topUpLimit = topUpLimit;
    }

    public String getAtmNumber() {
        return AtmNumber;
    }

    public void setAtmNumber(String atmNumber) {
        AtmNumber = atmNumber;
    }

    public Double getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(Double remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public Double getTopUpLimit() {
        return topUpLimit;
    }

    public void setTopUpLimit(Double topUpLimit) {
        this.topUpLimit = topUpLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Atm atm = (Atm) o;

        if (AtmNumber != null ? !AtmNumber.equals(atm.AtmNumber) : atm.AtmNumber != null) return false;
        if (remainingBalance != null ? !remainingBalance.equals(atm.remainingBalance) : atm.remainingBalance != null)
            return false;
        return topUpLimit != null ? topUpLimit.equals(atm.topUpLimit) : atm.topUpLimit == null;
    }

    @Override
    public int hashCode() {
        int result = AtmNumber != null ? AtmNumber.hashCode() : 0;
        result = 31 * result + (remainingBalance != null ? remainingBalance.hashCode() : 0);
        result = 31 * result + (topUpLimit != null ? topUpLimit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Atm{");
        sb.append("AtmNumber='").append(AtmNumber).append('\'');
        sb.append(", remainingBalance=").append(remainingBalance);
        sb.append(", topUpLimit=").append(topUpLimit);
        sb.append('}');
        return sb.toString();
    }
}
