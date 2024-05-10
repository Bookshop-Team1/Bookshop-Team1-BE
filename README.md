# Dev environment setup for - Bookstore

### Compile & Run the app

## Useful Gradle commands

### List all Gradle tasks

List all the tasks that Gradle can do, such as `build` and `test`.

```bash
$ ./gradlew tasks
```

### Build the project

Compiles the project, runs the test and then creates an executable JAR file

```bash
$ ./gradlew build
```

Run the application using Java and the executable JAR file produced by the Gradle `build` task. The application will be
listening to port `8080`.

### Run the tests

- Run unit tests only, navigate to console

  ```bash 
  $ ./gradlew test
  ```

### Run the app

-Run app , navigate to console

  ```bash
 $ ./gradlew bootRun --args='--spring.profiles.active=dev'
  ```

## Docker Setup

Install colima to run the docker

```bash
brew install docker colima
docker pull postgres
```

# Start SQL Server
Make sure colima is already running. 
#### Step 1: Then run the below command to start sql server locally:
```bash
docker run --cap-add SYS_PTRACE -e 'ACCEPT_EULA=1' -e 'MSSQL_USER=sa' \
-e 'MSSQL_PID=Developer'  -e 'MSSQL_SA_PASSWORD=123456789Ab' -p 1433:1433 \
--name azuresqledge -d mcr.microsoft.com/azure-sql-edge
```
#### Step 2: Download azure studio as DB client
https://go.microsoft.com/fwlink/?linkid=2261572

#### Step 3: Connect to the DB client with the following params
``` bash 
  Connection type: Microsoft SQL Server
  Input type: Parameters
  Server: 127.0.0.1
  Authentication Type: SQL Login
  User name: sa
  Password: 123456789Ab
  Trust Server certificate: True
```
#### Step 4: execute below cmd to create new database
``` bash
   create DATABASE bookshop;
```

# Start postgress
docker run --name postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=bookshop-db -p5432:5432 postgres

# Connect to postgres

```bash
        docker container ls
        docker exec -it --user postgres “replace this with container id” /bin/bash
        psql
        \c bookshop-db —for connecting to the db
        \dt for listing out the tables
        \d users — describe a specific table
  ```

