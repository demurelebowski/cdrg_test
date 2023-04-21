This is a simple wallet management system that allows users to create and manage their wallets, as well as perform transactions such as withdrawals and deposits.

Requirements
JDK 11 or higher
Maven 3.6.3 or higher
Installation
Clone the repository to your local machine using git clone.
Navigate to the project directory and run mvn clean install to build the project and generate the executable JAR file.

Create a Wallet
To create a wallet, send a POST request to the /wallets endpoint with the following JSON payload:

json

{
  "balance": 10000,
  "currency": "EUR",
  "phone": "+180504433678",
  "first_name": "John",
  "middle_name": "Frank",
  "last_name": "Smith",
  "email": "john.smith@example.com",
  "flag": "some_flag"
}
The balance, currency, phone, first_name, last_name, and email fields are required.

Retrieve a Wallet
To retrieve a wallet by ID, send a GET request to the /wallets/{id} endpoint, where {id} is the ID of the wallet you want to retrieve.

GET http://localhost:8080/wallets/1

Perform a Transaction
To perform a transaction (either a withdrawal or a deposit), send a POST request to the wallet/transactions endpoint with the following JSON payload:

json

{
  "wallet_id": 1,
  "transaction_type": "WITHDRAWAL",
  "amount": 10000,
  "currency": "EUR",
  "comment": "Some comment"
}
The wallet_id, transaction_type, amount in cents, and currency fields are required.

Example request using curl:

sh
Copy code
curl -X POST -H "Content-Type: application/json" -d '{"wallet_id": 1, "date": "2022-12-03 16:26:39", "transaction_type": "WITHDRAWAL", "amount": 1000, "currency": "EUR", "comment": "Some comment"}' http://localhost:8080/transactions
