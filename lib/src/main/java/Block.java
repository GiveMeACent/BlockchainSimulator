import java.util.HashMap;
import java.time.LocalDate;

public class Block {
  private String previousBlockHash;
  private HashMap<String, Transaction> transactions;
  private LocalDate timestamp;
  private String hash;
  private Integer height;

  public Block(String previousHashBlock, Integer height) {
    this.previousBlockHash = previousHashBlock;
    this.transactions = new HashMap<String, Transaction>();
    this.timestamp = LocalDate.now();
    this.height = height;
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

  public String[] getAllTransactionIds() {
    return (String[]) transactions.keySet().toArray();
  }

  public Integer getHeight() {
    return this.height;
  }

  public String addTransaction(Transaction transaction) {
    String transactionAddress = HashUtils.generateRandomAddress();
    this.transactions.put(transactionAddress, transaction);
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
