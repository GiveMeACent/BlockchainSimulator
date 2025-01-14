package Block;

import java.util.HashMap;

import Transaction.Transaction;
import Utils.HashUtils;

import java.time.LocalDate;

public class Block {
  private final String previousBlockHash;
  private HashMap<String, Transaction> transactions;
  private final LocalDate timestamp;
  private String hash;
  private final Integer height;
  private Integer currentSize;

  public static final Integer BLOCK_SIZE = 30;

  public Block(String previousHashBlock, Integer height) {
    this.previousBlockHash = previousHashBlock;
    this.transactions = new HashMap<String, Transaction>(Block.BLOCK_SIZE);
    this.timestamp = LocalDate.now();
    this.height = height;
    this.currentSize = 0;
  }

  public void calculateHash() {
    this.hash = HashUtils.hashSha256(this.buildBlockHeaderString());
  }

  public String getHash() {
    return this.hash;
  }

  public String getPreviousBlockHash() {
    return this.previousBlockHash;
  }

  public String getTimeStamp() {
    return this.timestamp.toString();
  }

  public Transaction getTransaction(String transactionId) {
    return transactions.get(transactionId);
  }

  public Transaction[] getAllTransactions() {
    return (Transaction[]) this.transactions.values().toArray();
  }

  public String[] getAllTransactionIds() {
    return (String[]) transactions.keySet().toArray();
  }

  public Integer getHeight() {
    return this.height;
  }

  public Integer getCurrentSize() {
    return this.currentSize;
  }

  public String addTransaction(Transaction transaction) {
    String transactionAddress = HashUtils.generateRandomAddress();
    this.transactions.put(transactionAddress, transaction);
    this.currentSize += 1;
    return transactionAddress;
  }

  private String buildBlockHeaderString() {
    String header;
    StringBuilder sb = new StringBuilder();

    sb.append(this.previousBlockHash);

    for (Transaction transaction : this.transactions.values())
      sb.append(transaction.getId());
    sb.append(this.timestamp.toString());

    header = sb.toString();
    return header;
  }

}
