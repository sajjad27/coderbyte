-HI There, this is a quick demo on how to run the application locally

-Pre-requisite
    - Java 8 or newer version installed locally.
    - MySQL server installed and running locally. 

-Steps: 

After opening the project folder, run the following commands:
-1- Maven install
```
mvnw install
```

-2- Run the application without IDE
```
java -jar ./target/Coderbyte.jar
```

-3-Now that the application is up and running, you can consume the APIs with the help of "Codertype.postman_collection.json" file found in the project, you can import it in the Postman and enjoy :) Note that the credentials are are the users saved in the database , example of users: [atreyi, dinesh] with similar password for all users: "pass@123"
