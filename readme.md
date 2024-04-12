# For Running the app on local

## Step 1: run the below command inside the project folder:

```
mvn clean install
```
It will generate a target folder with application jar in it


## Step 2: Run the below command to start the application

```
mvn spring-boot:run
```

## Step 3: Now you can access the app on your browser or rest client on localhost:8080.
Endpoint for creating new theatre.

```
POST: http://localhost:8080/theatres
```

Request : {
    "name": "INOX Dreamz",
    "town": "Gurgaon"
}

Headers :
Content-Type : application/json


Endpoint to create new movie.

```
POST http://localhost:8080/movies
```
Request : {
    "title" : "MovieName",
    "genre" : "Action",
    "duration" : 200
}

Headers :
Content-Type : application/json


Endpoint to create new shows mapped to specific theatre and movie,

```
POST http://localhost:8080/shows
```
Request : {
    "dateTime": "2023-09-20T19:00:00",
    "theatre" : {
        "theatre_id" : 1
    }, 
    "movie" : {
        "movie_id" : 1
    }
}

Headers :
Content-Type : application/json


Endpoint to browse theatres currently running the show (movie selected) in the town, including show timing by a chosen date.

```
GET http://localhost:8080/theatres/shows?movieTitle=MovieName&town=TownName&dateTime=2023-09-20T19:00:00
```