# Credit Loan Summary



The initial SQL script is in db folder, we can run the script to create table, the scheme is "credit_loan"

When the Application was start, we can visit http://localhost:8080/swagger-ui.html to test the API of controller.

1. ##### LoginController

   ​    This controller for test users to login the system, we should user spring security or shrio handle authentication and authorization in the real project. The password should be encrypted with MD5 algorithm.

   ###### **The** test json for login method:

   {
     "userName": "tommy",

     "password": "Tommy"

   }

2. ##### LoanProductController

   ​    This controller for test users to add new loan product, update loan product and query loan product with query params.

   ###### The test json for add new loan product:

   {
     "annualInterestRate": 12,
     "createDate": "2023-04-30T08:06:59.064Z",
     "loanMax": 100000,
     "loanMin": 1000,
     "productDescription": "credit loan for car.",
     "productName": "carLoan",
     "updateDate": "2023-04-30T08:06:59.064Z"
   }

   ###### The test json for update loan product:

   {
     "productId": "c1b9c2d9-6f79-46c4-8639-8abadf5a7122",
     "annualInterestRate": 24,
     "loanMax": 200000,
     "loanMin": 1000,
     "createDate": "2023-04-30T08:06:59.064Z",
     "productDescription": "credit loan for car.",
     "productName": "carLoan"
   }

   ###### The test json for query loan product:

   {
     "productId": "c1b9c2d9-6f79-46c4-8639-8abadf5a7122"
   }

3. ##### LoanOrderController

   ​    This controller for test users to apply new loan order, query loan order.

   ###### The test json for apply new loan order:

   {
     "annualInterestRate": 12,
     "items": 12,
     "loanAmount": 40000,
     "loanType": "CAR",
     "productId": "c1b9c2d9-6f79-46c4-8639-8abadf5a7122",
     "userId": "20230001"
   }

   ###### The test json for query loan order:

   {
     "userId": "20230001"
   }

   ###### The test json for query loan order by orderId:

   {
     "orderId": "1"
   }

4. ##### RepaymentController

​    This controller for test users to repay one item, or repay all items.

###### 	The test json for repay one item:

{

  "id": 109,
  "item": 1,
  "userId": "20230001"
}

###### The test json for repay all items:

{
  "orderId": "f0b2eb72-ffc7-4e96-836f-65676a9d9939"
}



There are 3 jobs in job package:

1. ##### RepaymentNotifyJob

   This job notify user to remember repayment, it's running every day at 10:00 am

2. ##### RepaymentAutomaticJob

   This job automatic repayment for users, it's running every day at 16:00 pm

3. ##### CalculatePunishInterestJob 

   This job calculate punish interest for overdue users, it's running every day at 2:00 am