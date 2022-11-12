# Coderbyte
## _Simple steps to run the application locally!_

This assessment was given to me as a pre-request to have an oppurtunity to join **Lendo!** 

## Features

- Fully authenticated application using JWT Concept.
- Get all users from third party.
- Get all posts 
- Get all posts for single User
- Get all comments 
- Get all comments for single 

## Installation

##### Pre-requisite
- Java 8 or newer version installed locally.
- For a better results if you need to modify the code, you need to set up lombok library if you are using STS, here is a simple steps of how to set it up: https://stackoverflow.com/questions/11803948/lombok-is-not-generating-getter-and-setter/45217235#45217235

##### Build and Run
After opening the project folder, run the following commands:
1. Maven Install + Build
```
mvnw install
```

2. Run the Application
```
java -jar ./target/Coderbyte.jar
```

3. Now that the application is up and running, you can consume the APIs with the help of **Codertype.postman_collection.json** file found in the project, you can import it in the Postman and enjoy :) Note that the user credentials is: 
**Username:** admin
**Password:** admin