# Spring boot + CRUD 

SERVER DETAILS
-------------------
server.port=9000;
server.context-path=/api/v1;


REST ENDPOINTS
------------------
Add user(PostMapping):

   curl -X POST \
  http://localhost:9000/api/v1/users \
  -H 'accept: application/json' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: c5826d43-5261-8849-7c16-6a45e80d46d5' \
  -d '{
    "id": "107",
    "fName": "Ragu",
    "lName": "Satheesh",
    "email": "raghusatheesh@mail.com",
    "pinCode": 12345,
    "birthDate": "17-JUL-1992"
  }'
  
  
  Update User(PutMapping):
  
   
   curl -X PUT \
  http://localhost:9000/api/v1/users/user-id/107 \
  -H 'accept: application/json' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 20498f23-8ede-9027-5a14-43dba7d29d5f' \
  -d '{
    "email": "raghusathesh@mail.com",
    "birthDate": "17-JUL-1992"
  }'
  
  
  
  Delete User(Delete Mapping):
  
     curl -X DELETE \
  http://localhost:9000/api/v1/users/user-id/107 \
  -H 'accept: application/json' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' 
  
  
    
