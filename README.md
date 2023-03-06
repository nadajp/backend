# DemoApplication

This is the backend component of a demo fullstack project that consumes the REQRES API (https://reqres.in/). 
It makes calls to reqres API's user and resources endpoints.


## Running the application locally

Run the application, then navigate to `http://localhost:8080/`.

To test, use with accompanying Angular frontend or run locally and hit get endpoints by navigating to them in the browser 
(localhost:8080/resource). For POST endpoints, Postman may be used. 

Only predetermined username/password combinations give a successful response from REQRES API. 
An example is {
    "email": "eve.holt@reqres.in",
    "password": "pistol"
} 
## Running the application on replit
Navigate to https://frontend.nadajp.repl.co/ to access the front end. This api is hosted on https://backend.nadajp.repl.co/


## TODO

Introduce services to handle controller calls
Better error handling. This should be done in the service calls.
Separate interface from impl
Caching
Unit testing, e2e testing. Minimal unit testing has been implemented (see ResourceControllerTest.java)

