# Blockchain Simulator

A **Proof of Stake Blockchain Simulator** implemented in Java, designed to showcase how blockchains handle transactions, smart contract deployments, and validator-based consensus mechanisms. This project highlights the core principles of blockchain technology in a simplified and educational way.

---

## Features

- **Node Management**:
  - Supports validator and non-validator nodes.
  - Validators can stake tokens and participate in consensus.
- **Transaction Handling**:
  - Various types of Transactions with sender, recipient, amount to transfer and other parameters related to the specific Transaction type.
- **Smart Contract Creation**:
  - You can create your personalized Smart Contract by extending the class SmartContractBase.
- **Smart Contract Execution**:
  - Utilizes Java Reflection for dynamic execution of contract methods.
  - Supports deployment and validation of contracts.
- **Consensus Mechanism**:
  - Implements a Proof of Stake consensus to validate transactions and blocks.
- **Block Management**:
  - Each block contains a list of transactions.
  - Blockchain maintains integrity through hashing and linking blocks.

---

## Technologies Used

- **Java**: Core programming language.
- **JUnit**: For unit testing.
- **Reflection API**: To execute smart contracts methods dynamically.
- **Hashing Utilities**: For ensuring blockchain integrity (e.g., SHA-256).

---

## Getting Started

### Prerequisites

- **Java 11+**: Ensure you have Java Development Kit (JDK) installed.
- **Gradle** : For dependency management and builds.

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/blockchain-simulator.git
   cd blockchain-simulator
   ```
2. Compile the project:
   ```bash
   ./gradlew build
   ```
3. Run tests:
   ```bash
   ./gradlew test
   ```

---

## Usage

1. **Create Nodes:**

   - Instantiate nodes (validators and non-validators) and join the blockchain.

   ```java
   Node validator = new ValidatorNode();
   blockchain.registerNode(validator);
   ```

2. **Handle Transactions:**

   - Create and submit monetary or smart contract transactions.

   ```java
   Transaction tx = new MonetaryTransaction("txn123", "0xABC", "0xDEF", 100, 1);
   blockchain.requestTransactionRegistration(tx);
   ```

3. **Deploy Smart Contracts:**

   - Deploy and execute smart contracts.

   ```java
   SmartContractBase contract = new ExampleSmartContract("...");
   Transaction deployTx = new SmartContractDeployTransaction(..., contract, ...);
   blockchain.requestTransactionRegistration(deployTx);
   ```

4. **Consensus:**
   - Validators validate transactions and propose blocks.

---

## Acknowledgments

- Inspired by real-world blockchain systems like Ethereum and Bitcoin.
- Special thanks to Professor Tramontana Emiliano Alessio for the idea, guidance and feedback.

---
