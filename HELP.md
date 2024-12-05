# Getting Started

### Docker command for database connection
docker run -p 3306:3306 --name springsecurity -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=eazybank -d mysql -u root


#To activate the particular profile, set below env variable: SPRING_PROFILES_ACTIVE=default
