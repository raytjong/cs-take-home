Implementation Notes:
- Although the scope of this challenge is quite small, I tried to implement the solution in a way that is extendable and modular
    - Ice cream flavours and order related classes are represented here as models
    - Promotions are not hardcoded but instead are separate classes implementing a common interface
- I made use of Lombok in order to reduce the boilerplate logic used in the models
- I have written unit tests which include the test scenario outlined in the challenge document

Assumptions:
- All three totals per order calculation were added in as the instructions state, but I did decide to show the promotion totals as a positive number instead since I think it made more sense
- Invalid order cases where zero or negative values were passed in as price or quantity were dealth with by just returning zero totals on those entries
    - I would recommend logging this out if we were to productionise this app
- Order quantity was followed precisely: if an order specifies a quantity of 2 under a buy two get one free promotion, under this implementation, we don't expect them to go grab the free third item

Possible improvements (if taking this to production):
- If taking this into production, the actual Ice cream flavours and promotions should be stored in a database and read into this service
- If operating in a microservices environment, I would suggest refactoring this application into a separate standalone service using a framework like Spring Boot or Dropwizard. That way we can expose a few endpoints under a well defined API (limited to possibly just expose the calculation functionality).