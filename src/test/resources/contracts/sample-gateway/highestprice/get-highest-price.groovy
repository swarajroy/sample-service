package highestprice

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name("get highest price")
    request {
        method'GET'
        url '/sample-service/api/1/highestprice?currency=USD'
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body(file("get-highest-price.response.json"))
        headers {
            contentType(applicationJson())
        }
    }
}