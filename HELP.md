# Getting Started

### Docker command for database connection
docker run -p 3306:3306 --name springsecurity -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=eazybank -d mysql -u root


#To activate the particular profile, set below env variable: SPRING_PROFILES_ACTIVE=default

## Register a user first to login:
URL: http://localhost:8080/register
Auth type: basic
Auth credentials:
username: admin@example.com
password: 123

Http call: 
POST /register HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: ••••••
Content-Length: 92

{
    "email": "choudharylakshyaveer@gmail.com",
    "password": "123",
    "role": "user"
}

##
