# Team-6-pension-management
Code for Final project in internship training, Pension Management by Team-6 of cohort GN22IOTJ001

## Team Members
1. PRATYAY ROY
2. SUMANTH REDDY
3. RANGANATH KONANKI
4. GANDAVARAPU VIVEKA
5. ANURAG YADAV

## The application has the following services:

1. Authorization Microservice
2. PensionerDetail Microservice
3. PensionDisbursement Microservice
4. ProcessPension  Microservice
6. Pension Management Portal (Front-end in Angular)

## 1. Authorization Microservice
This service is responsible to provide login access to the application and provide security to it using JWT Tokens.

### This service provides two controller END-POINTS:

1. Open your spring boot application and run the service.
2. Open your postman and write this URL - http://localhost:8400/auth/authenticate.
3. For Login
* Select POST method
* Then enter **correct** username and password credentials as follows:

```
{
  "userName": "roy",
  "password": "123"
}
```

* Then hit send and you will see a JWT Token generated. Copy this token to be used in the next step.

5. For Validation
* Open New tab in postman.
* Write this URL - http://localhost:8400/auth/validate
* Then enter previously generated **valid** Token that you had copied into the Authorization header as Bearer Token.
* Then hit send and you would see `true` in the response body if the token is valid else `false`.

## 2. PensionerDetail Microservice
The intent of this Microservice is to provide the Pensioner detail like name, PAN detail, Bank name and bank account number based on Aadhaar number
    
   **Steps and Action**
   
      => This Microservice is to fetch the pensioner detail by the Aadhaar number.
      => Flat file(CSV file with pre-defined data) should be created as part of the Microservice. 
      => This file has to contain data for 20 Pensioners. This has to be read and loaded into List for
          ALL the operations of the microservice.
      
   **Endpoint**
   
   * URL - http://localhost:8200/pensioner/PensionerDetailByAadhaar/420559429029
   * Then enter previously generated valid Token that you had copied into the Authorization header as Bearer Token.
   * This endpoint accept the user request and provides the Pensioner details only to authorized request.
   * Access this using the POSTMAN client
   * Method - GET
   * Input - Aadhaar Number => 420559429029
      
## 3. PensionDisbursement Microservice
This microservice is responsible for verifying pension amount and bank charges for provided aadhaar number.

  **Endpoint**
  
   * URL - http://localhost:8300/disbursement/disbursePension
   * Then enter previously generated **valid** Token that you had copied into the Authorization header as Bearer Token.
   * This endpoint performs the verification only to authorized request. Access this using the POSTMAN client    
   * Method - GET

  **Valid Input**
```
{
    "aadharNumber":"420559429029",
    "pensionAmount":"24400",
    "bankCharge":"550"
}
```
If details are valid, pension amount and bankCharges is correct then user will following response with code "10" else "21"
     
## 4. ProcessPension  Microservice
* It takes in the pensioner detail like the name, aadhaar number, pan detail, self or family or both type of pension
* Verifies if the pensioner detail is accurate by getting the data from PensionerDetail Microservice or not. 
* If not, validation message `“Invalid pensioner detail provided, please provide valid detail.”`
* If valid, then pension calculation is done and the pension detail based on `process code` is returned to the Web application to be displayed on the UI.

### This service provides two controller END-POINTS:

1. Open your spring boot application and run the services.
2. Open your postman and write this URL - http://localhost:8100/process/PensionDetail
3. For Get Pension Details
* Then enter previously generated **valid** Token that you had copied into the Authorization header as Bearer Token.
* Select POST method
* Then enter details as follows:

**Valid Input**
```
{
  "aadharNumber": "420559429029",
  "dateOfBirth": "28-04-1999",
  "name": "Pratyay",
  "pan": "BSDPS1495K",
  "pensionType": "self"
}
```

4. For Process Pension
* Open New tab in postman.
* Write this URL - http://localhost:8100/process/ProcessPension
* Then enter previously generated **valid** Token that you had copied into the Authorization header as Bearer Token.
* Select POST method and hit send.
* Status code of 10 for valid input and if the input has been processed by the disbursement microservice
* Status code of 21 for invalid input.

**Valid Input**

```
{
  "aadharNumber": "420559429029",
  "bankCharge": 550,
  "pensionAmount": 24400
}
```

## 5. Pension Management Portal

This is the front end made using Angular, open VS code and enter `ng serve --open` or `ng serve --open --no-live-reload` in the terminal to run the application at `http:\\localhost:4200`
