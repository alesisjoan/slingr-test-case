# slingr-test-case

This is a demonstration project for Slingr.io test case.

`Math web services
 The requirement is to build a public RESTful API that allows to solve math expressions. So given a math expression a number is returned as response....`

### Front End: React

Simple calculator interfaces that allows you to select Spring Boot API or Python API and POST/GET methods to calculate the math expression
Shows last expressions.

github repository: https://github.com/alesisjoan/slingr-test-case/tree/main/react-math-expresions-ui
**demo**: https://math-calculator-fe.herokuapp.com/

### Spring Boot API Rest for Math Services

Simple API rest for evaluating math expressions. Uses net.objecthunter.exp4j to evaluate expressions.
_requires cache service, see Cache Service (enable environment variable CACHE_HOST)_

Usage: 

#### GET
`curl -X GET "http://host/expressions?expression=sqrt(2)*2&digits=2" `
`http://host/expressions?expression=sqrt(2)*2&digits=2`
Responses
200 OK
400 Invalid input
500 Internal error / Math exception

#### POST
`curl -X POST "http://host/expressions" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"expression\": \"sqrt(2)*2\",  \"digits\": 2}"`
Responses
200 OK
400 Invalid input
500 Internal error / Math exception

github repository: https://github.com/alesisjoan/slingr-test-case/tree/main/sb-math-services

### Python Connexion API Rest for Math Services

Simple API rest for evaluating math expressions. Uses python (safe) eval to evaluate expression.
_requires cache service, see Cache Service (enable environment variable CACHE_HOST)_

Usage:

#### GET
`curl -X GET "http://host/expressions?expression=sqrt(2)*2&digits=2" `
`http://host/expressions?expression=sqrt(2)*2&digits=2`
Responses
200 OK
400 Invalid input
500 Internal error / Math exception

#### POST
`curl -X POST "http://host/expressions" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"expression\": \"sqrt(2)*2\",  \"digits\": 2}"`
Responses
200 OK
400 Invalid input
500 Internal error / Math exception

github repository: https://github.com/alesisjoan/slingr-test-case/tree/main/python-math-services


### Cache Services

Simple Cache Service (like Redis) to save MathExpressions (used by both Back End to skip evaluation) 
and to get last expressions (used by Front End)
_requires cache service, see Cache Service (enable environment variable CACHE_HOST)_

Usage:

#### GET
Gets the result for an expression
`curl -X GET "http://host/expressions?expression=sqrt(2)*2" `
Responses
200 OK
400 Invalid input
500 Internal error / Math exception

#### PUT
Saves a key/value element in a WeakHashMap (limited to 5825422 elements ~ 200MB)
This will be used by Back End to not repeat math expression evaluations 
`curl -X PUT "http://host/expressions" -H  "Content-Type: application/json" -d "{  \"key\": \"sqrt(2)*2\",  \"value\": \"2.82842712474619\"}"`
Responses
200 OK
400 Invalid input
500 Internal error / Math exception

#### GET
Get the last expressions sent by both Backends
`curl -X GET "http://host//expressions/last" `
`http://host/expressions?expression=sqrt(2)*2&digits=2`
Responses
200 OK
400 Invalid input
500 Internal error

github repository: https://github.com/alesisjoan/slingr-test-case/tree/main/redislite-services

_______

**demo**: https://math-calculator-fe.herokuapp.com/

contact: alesis4ta@gmail.com | https://alesisjoan.github.io