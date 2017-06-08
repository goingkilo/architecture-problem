## Problem

Our [coding challenges](https://www.geektrust.in/coding-problem) are set in the planet of Lengaburu. King Shan is the emperor and has been ruling for the last 350 years (they have long lives in Lengaburu you see)!

King Shan is very fond of movies and wants to take his family for a movie. But there is a problem. They can’t agree on which movie to watch as everyone has their own movie preference. So King Shan declares a movie watching spree and takes each person to watch the movie they want to see based on their preferences. 


![Alt text](https://www.geektrust.in/assets/images/shan.png "King Shan")


Your coding challenge is to: 

1. Build a Restful web service API which will accept a search string and userID and return unique movies in the order of preference for that user (user preferences data set & movie data set is provided)

2. Build a Restful web service API which will list out all the users and the top 3 recommended movies for each user based on their preferences.

Develop the application in a language of your choice and host the service in Cloud infrastructure like AWS/Heroku and give us the url endpoints or a Postman project for the same. The input & output for the two web services are given below


### API 1

##### Input 

Request Type : GET

URL : `http://<url>/movies/user/$userId/search?text=<text>`


Where the `$userId` is the id of the user from the user preferences JSON file given and `<text>`  is the search text. The search text could be a multiple words separated by comma, in which case it will search for all of those. Or it could be a single urlencoded entry. The search text is matched against actor, director and title fields (*director_name*, *actor_1_name*, *actor_2_name*, *actor_3_name* and *movie_title* in the movies data) of the movies. All matches are included in the results. 

`<url>` is the domain name in the URL where you are hosting the solution.



For e.g. 

`http://<url>/movies/user/$userId/search?text=Tom%20Hanks`  ->  This will return all the movies matching “Tom Hanks” (usually in one of the actor name fields) considering the preferences of the user


##### Output	

An array of movies names, found based on the preferences of users, sorted in this order

1. First show the movies matching the user’s preferences and search term. This should be further sorted on the alphabetic order of the titles. There is a chance this set of movies could be empty if there is no search result matching the user’s preferences.
2. Next show the movies matching the search term in the alphabetic order of titles (even if it does not match the user’s preferences). If #1 is empty then only these set of movies will be there. 

```javascript
[“Movie 1”,”Movie 2”,”Movie 3”]
```

### API 2

##### Input 

Request Type : GET

URL : `http://<url>/movies/users`


##### Output

A json array of userids and top 3 movies recommended. The movie names are sorted in the alphabetic order of titles. If there is no recommendations then it can be an empty array.


```javascript
[
  {
    "user": "100",
    "movies": [
      "A",
      "B",
      "C"
    ]
  },
  {
    "user": "101",
    "movies": [
      "A",
      "D",
      "C"
    ]
  },
  {
    "user": "102",
    "movies": [
      "A",
      "D",
      "C"
    ]
  },
  {
    "user": "103",
    "movies": [
      "A",
      "DD",
      "C"
    ]
  },
  {
    "user": "104",
    "movies": [
      "AA",
      "D",
      "C"
    ]
  },
  {
    "user": "105",
    "movies": [
      "A",
      "D",
      "CD"
    ]
  },
  {
    "user": "106",
    "movies": [
      "A",
      "D",
      "E"
    ]
  }
]
```


## What we evaluate your solution on:

1. Overall architecture. Please submit documentation to explain your choices and the complete architecture.
2. Code scalability. Can it handle 1000s of hits a second?
3. Clean code, RESTful semantics, and object modelling 
4. Bonus points for automating the deployment of your solution
5. When the instructions are not clear or there are multiple solution possibilities, make a choice that you find reasonable and practical.

#### Need help?

If you have questions, do mail us or join our Slack channel and we'll be happy to help you out. Send a mail to devs@geektrust.in with 'Add to slack', and we'll send you an invite to our Slack channel.


#### Files

We use the [Kaggle](www.kaggle.com) IMDB data set for this problem. This data set is licensed under the [ODC Open Database License](https://opendatacommons.org/licenses/odbl/summary/). Complete movie data set can be downloaded from [here](https://www.kaggle.com/deepmatrix/imdb-5000-movie-dataset/downloads/imdb-5000-movie-dataset.zip)

The user preferences data file is embedded below. It can also be downloaded from [here](https://gist.github.com/dhanush/9409f9afe2d15956dd508d95b933726f)


```javascript

[
  {
    "100": {
      "preferred_languages": [
        "English",
        "Spanish"
      ],
      "favourite_actors": [
        "Denzel Washington",
        "Kate Winslet",
        "Emma Suárez",
        "Tom Hanks"
      ],
      "favourite_directors": [
        "Steven Spielberg",
        "Martin Scorsese",
        "Pedro Almodóvar"
      ]
    }
  },
  {
    "101": {
      "preferred_languages": [
        "English"
      ],
      "favourite_actors": [
        "Denzel Washington",
        "Anne Hathaway",
        "Tom Hanks"
      ],
      "favourite_directors": [
        "Guy Ritchie",
        "Quentin Tarantino"
      ]
    }
  },
  {
    "102": {
      "preferred_languages": [
        "English"
      ],
      "favourite_actors": [
        "Uma Thurman",
        "Charlize Theron",
        "John Travolta"
      ],
      "favourite_directors": [
        "Quentin Tarantino"
      ]
    }
  },
  {
    "103": {
      "preferred_languages": [
        "English"
      ],
      "favourite_actors": [
        "Antonio Banderas",
        "Clint Eastwood",
        "Bruce Willis"
      ],
      "favourite_directors": [
        "Stanley Kubrick",
        "Oliver Stone"
      ]
    }
  },
  {
    "104": {
      "preferred_languages": [
        "English"
      ],
      "favourite_actors": [
        "Anthony Hopkins",
        "Adam Sandler",
        "Bruce Willis"
      ],
      "favourite_directors": [
        "Nora Ephron",
        "Oliver Stone"
      ]
    }
  },
  {
    "105": {
      "preferred_languages": [
        "Spanish"
      ],
      "favourite_actors": [
        "Anthony Hopkins",
        "Bárbara Goenaga",
        "Tenoch Huerta"
      ],
      "favourite_directors": [
        "Amat Escalante",
        "Robert Rodriguez"
      ]
    }
  },
  {
    "106": {
      "preferred_languages": [
        "English",
        "Spanish"
      ],
      "favourite_actors": [
        "Brad Pitt",
        "Robert Downey Jr.",
        "Jennifer Lawrence",
        "Johnny Depp"
      ],
      "favourite_directors": [
        "Steven Spielberg",
        "Martin Scorsese",
        "Ridley Scott"
      ]
    }
  }
]
```
