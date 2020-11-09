package ru.brambrulet.external;

public class Transaction {
    public long card;
    public long customerId;
    public int account;
    public TransactionType type;
    public long summ;
    public int restaurant;
    public int unitDate;
    public int unitNo;
    public TaxAmount[] taxAmount = new TaxAmount[8];

    public static class TaxAmount {
        long sum;
        int tax;
    }

    public Transaction() { }

    public Transaction(TransactionType type, long card, long summ) {
        this.type = type;
        this.card = card;
        this.summ = summ;
    }
}
