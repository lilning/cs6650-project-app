# cs6650-project-app
final project for cs6650

- Add to your system environment variables GOOGLE_APPLICATION_CREDENTIALS and set it to the file called credentials 
- Follow first two steps in this link: https://cloud.google.com/docs/authentication/provide-credentials-adc
- Download Kafka
- https://kafka.apache.org/quickstart
- Follow quickstart step 2 w/ zookeeper
- Follow quickstart step 3: topic name = transaction_topics instead of quickstart-events

- Then run the docker file and then run the spring boot file
-Use postman to connect to 'http//localhost:8080/api/v1/transactions'
- JSON POST queries will represent a transaction of sending money from a sender to a recipient if there is enough credit in the balance
- The database is prepopulated with users: 'ben', 'jiayi', 'matthew' with balances of 20 each
-The post would look like this: {
    "recipient": "jiayi",
    "sender" : "ben",
    "amount" : 5,
    "comment" : "first transaction"
}
