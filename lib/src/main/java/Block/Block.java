package Block;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import Transaction.Transaction;
import Utils.HashUtils;

import java.time.LocalDate;

public class Block {
  private final String previousBlockHash;
  private LinkedHashMap<String, Transaction> transactions;
  private final LocalDate timestamp;
  private String hash;
  private final Integer height;
  private Integer currentSize;

  public static final Integer BLOCK_SIZE = 30;

  public Block(String previousHashBlock, Integer height) {
    this.previousBlockHash = previousHashBlock;
    this.transactions = new LinkedHashMap<String, Transaction>(Block.BLOCK_SIZE);
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

  public List<Transaction> getAllTransactions() {
    return this.transactions.values().stream().collect(Collectors.toList());
  }

  public List<String> getAllTransactionIds() {
    return transactions.keySet().stream().collect(Collectors.toList());
  }

  public Integer getHeight() {
    return this.height;
  }

  public Integer getCurrentSize() {
    return this.currentSize;
  }

  public String addTransaction(Transaction transaction) {
    String transactionAddress = HashUtils.generateRandomAddress();
    transaction.setAddress(transactionAddress);
    this.transactions.put(transactionAddress, transaction);
    this.currentSize += 1;
    return transactionAddress;
  }

  private String buildBlockHeaderString() {
    String header;
    StringBuilder sb = new StringBuilder();

    sb.append(this.previousBlockHash);

    for (String transactionId : this.transactions.keySet())
      sb.append(transactionId);
    sb.append(this.timestamp.toString());

    header = sb.toString();
    return header;
  }

}
