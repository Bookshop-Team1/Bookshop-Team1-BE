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

# Start postgress

docker run --name postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=bookshop-db -p5432:5432 postgres

# Connect to postgress

```bash
        docker container ls
        docker exec -it --user postgres “replace this with container id” /bin/bash
        psql
        \c bookshop-db —for connecting to the db
        \dt for listing out the tables
        \d users — describe a specific table
  ```

