# CinemaRESTSpring

A project for [Hyperskill's Java Backend Developer track](https://hyperskill.org/projects/189).
By Alibi Zhenis

# Tech Stack
Tech Stack Used: Java, Spring Boot, Jackson, Lombok

# Endpoints and example requess

```
# to get available seats
GET /seats

# to buy a ticket
POST /purchase
{
    "row": 3,
    "column": 4
}
Example response 1:
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556",
    "ticket": {
        "row": 3,
        "column": 4,
        "price": 10
    }
}
Example response 2:
{
    "error": "The ticket has been already purchased!"
}
Example response 3:
{
    "error": "The number of a row or a column is out of bounds!"
}

# to return a ticket
POST /return
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
Example response 1:
{
    "returned_ticket": {
        "row": 1,
        "column": 2,
        "price": 10
    }
}
Example response 2:
{
    "error": "Wrong token!"
}

# to get statistics
POST /stats?password=super_secret

Example response 1:
{
    "current_income": 0,
    "number_of_available_seats": 81,
    "number_of_purchased_tickets": 0
}
Example response 2:
{
    "error": "The password is wrong!"
}

```

