export const spotReviews = [{
    "author": "aymeric",
    "review": "un endroit sympa pour la ballade des chiens :)",
    "createdAt": new Date(Date.now()),
    "rating": 5,
    "id": 123,
  },
  {
    "author": "marco",
    "review": "pas mal mais je suis allergique aux arbres :/",
    "createdAt": new Date(Date.now()-1234567890),
    "rating": 3,
    "id": 456,
  },
  {
    "author": "zak",
    "review": "Cool !",
    "createdAt": new Date(Date.now()-987659876599),
    "rating": 4,
    "id": 789,
  }
]

export const creator = {
  "id": 123,
  "firstName": "Aymeric",
  "lastName": "Robini",
  "pseudo" : "M'ric"
}

export const spotView = {
  "name" :"Foret de fontainebleau", 
  "longitude": 12,  latitude : 15,
  "activities" : ["rando", "ballade", "escalade", "sports"],
  "creator" : creator, 
  "address":"10 avenue le notre",
  "currentWeather":"Sunny", 
  "images" : ["123","456"],
  "reviews" : spotReviews
}

export const eventComments = [{
  "author": "Aymeric",
  "message": "Alcool et bitches ? J'y serai :)",
  "createdAt": new Date(),
  },
  {
    "author": "Zak",
    "message": "Je ramène mes 7 femmes !",
    "createdAt": new Date(),
    "id": 123,
  },
  {
    "author": "Marco",
    "message": "Je bois pas :/",
    "createdAt": new Date(),
    "id": 456,
  },
]

export const eventView = {
  "name" : "Soiree dans les bois",
  "description" : "Salut, je propose une petite soiree feu de camp samedi prochain dans les bois avec alcool et bitches :) venez nombreux !",
  "spot" : {
    "id": 123,
    "name" : "Foret de fontainebleau"
  },
  "creator" : creator, 
  "createdAt": new Date(),
  "date" : new Date(2017, 11, 18),
  "subscribers" : [],
  "comments" : eventComments,
  "weather" : null,
}