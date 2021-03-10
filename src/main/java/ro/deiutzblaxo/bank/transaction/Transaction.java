package ro.deiutzblaxo.bank.transaction;

import ro.deiutzblaxo.bank.player.PlayerBank;


import java.time.LocalDateTime;

public class Transaction {

    private TransactionType type;
    private PlayerBank owner;
    double amount;
    long when;

    public Transaction(PlayerBank owner, TransactionType type, double amount , long when){
        this.type = type;
        this.owner = owner;
        this.amount = amount;
        this.when = when;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public long getWhen() {
        return when;
    }

    @Override
    public String toString() {
        return type.toString()+","+amount+","+when;
    }
}
