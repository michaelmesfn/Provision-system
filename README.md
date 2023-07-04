# Provision-system
GET: /contracts
displays all contracts

POST: /contract/create
creates a contract
ex. {
  "beginDate": "2023-09-30",
  "endDate": "2030-08-30",
  "intermediaryId": "3",
  "intermediaryShare": "20",
  "amount": "1000"
}

DELETE: /contract/delete/{id}
deletes a contract

GET: /intermediaries
displays all intermediaries

GET: /intermediaries-overview
gets the sum of monthly provisions of all intermediaries

POST: /intermediary/create
creates an intermediary, no body needed 

