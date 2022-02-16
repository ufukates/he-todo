# HE TO-DO
- TODO app

### Developed With
- Java `1.8`
- Spring Boot Framework `2.6.3`

### Built With
* [Maven](https://maven.apache.org/) - Dependency Management

```
mvn clean verify
```

### Dependencies
- ProjectLombok `1.18.22`
- Springfox `2.9.2`
- Jupiter `5.8.1`
- Testcontainers `1.16.3`

## Before Application Run
```
Install Couchbase.
docker run -d --name he-cb -p 8091-8096:8091-8096 -p 11210-11211:11210-11211 couchbase
```

### After run couchbase on docker
`Visit http://localhost:8091 on browser.`
- Setup new Cluster.
```
Cluster Name: hecluster
Create Admin Username: Administrator
Create Password: admin1234
Confirm Password: admin1234
```
- Create Bucket.
```
Click: Bucket > Add Bucket for Add Data Bucket.
Name: hebucket
```
- Create User
```
Click: Security > Add User for Add New User.
Username: he_user
Fullname: he_user
Password: asd123
Verify Password: asd123
Roles: Click Bucket > Application Access > Select Bucket > Add > Save Changes (hepsiemlak for our case)
```
- Create Collection to Bucket for our datas.(users, todos, todoItems)
```
Click: Bucket > Scopes & Collections > Add Collection (for _default scope)
Name: users 
PS: Repeat this for each collection we need. (users, todos, todoItems)
```
- Create Index for collections.
```
Click: Query (execute below queries in query editor)
CREATE PRIMARY INDEX ON `default`:`hebucket`.`_default`.`users` USING GSI;
CREATE PRIMARY INDEX ON `default`:`hebucket`.`_default`.`todos` USING GSI;
CREATE PRIMARY INDEX ON `default`:`hebucket`.`_default`.`todoItems` USING GSI;
```

### After configure couchbase
```
Open Application.yml. 
ProjectRoot > src > main > resources > application.yml Change values for couchbase with our configure above.
For our case.

spring:
  couchbase:
    connection-string: ${COUCHBASE_CONNECTION_STRING:couchbase://localhost:11210}
    username: ${COUCHBASE_USERNAME:he_user}
    password: ${COUCHBASE_PASSWORD:asd123}
  data:
    couchbase:
      bucket-name: ${COUCHBASE_BUCKET:hebucket}
      auto-index: true
```


Done. Run project.

### Api Documentation
* [Swagger](http://localhost:8080/api/swagger-ui.html#/)