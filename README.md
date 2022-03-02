# Cinema Room Rest Service

Simple Cinema Room with 9 rows and 9 columns.

Endpoints are as follows:

```/seats``` endpoint handles GET requests and returns information about the movie theatre.

```/purchase``` endpoint handles POST requests and marks a seat as purchased.

A purchase request should contain the following data in request body:

row - the row number;

column - the column number.

```/return``` endpoint handles POST requests and allows customers to refund their tickets.

A return request should have the token that identifies the ticket in the request body.

```/stats``` endpoint handles POST requests with URL parameters. If the URL parameters contain a password key with a String value, following statistics are returned:

- current_income - shows the total income of sold tickets.
- number_of_available_seats - shows how many seats are available.
- number_of_purchased_tickets - shows how many tickets were purchased.


# Valid Requests:

## ```GET /seats```

Response body:
```
{
   "total_rows":9,
   "total_columns":9,
   "available_seats":[
      {
         "row":1,
         "column":1,
         "price":10
      },
      {
         "row":1,
         "column":2,
         "price":10
      },
      {
         "row":1,
         "column":3,
         "price":10
      },

      ........

      {
         "row":9,
         "column":8,
         "price":8
      },
      {
         "row":9,
         "column":9,
         "price":8
      }
   ]
}
```

## ```POST /purchase``` correct request

Request body:
```
{
    "row": 3,
    "column": 4
}
```
Response body:
```
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556",
    "ticket": {
        "row": 3,
        "column": 4,
        "price": 10
    }
}
```

## ```POST /purchase``` request, the ticket is already booked

Request body:
```
{
    "row": 3,
    "column": 4
}
```
Response body:
```
{
    "error": "The ticket has been already purchased!"
}
```

## ```POST /purchase``` request, a wrong row number

Request body:
```
{
    "row": 15,
    "column": 4
}
```
Response body:
```
{
    "error": "The number of a row or a column is out of bounds!"
}
```

## ```POST /return``` with the correct token

Request body:
```
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
```
Response body:
```
{
    "returned_ticket": {
        "row": 3,
        "column": 4,
        "price": 10
    }
}
```

## ```POST /return``` with an expired token

Request body:
```
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
```
Response body:
```
{
    "error": "Wrong token!"
}
```


## ```POST /stats``` request with no parameters

Response body:
```
{
    "error": "The password is wrong!"
}
```

## ```POST /stats``` request with correct password

Response body:
```
{
    "current_income": 10,
    "number_of_available_seats": 80,
    "number_of_purchased_tickets": 1
}
```

#
This program is based on the Cinema Room Rest Service project on [JetBrains Academy](https://hyperskill.org).
