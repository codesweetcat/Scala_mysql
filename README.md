# play-scala-starter-haoming

[<img src="https://img.shields.io/travis/playframework/play-scala-starter-example.svg"/>](https://travis-ci.org/playframework/play-scala-starter-example)

This is a starter application that shows how Play works.  Please see the documentation at <https://www.playframework.com/documentation/latest/Home> for more details.

## MySQL

id, first_name, last_name
1	Haoming	wang
2	Irfan	g
3	haoming	wangwang
4	Arno	wrecle


## Controllers

- HomeController.scala:

  Shows how to handle simple HTTP requests.

- ClientController.scala:

  1. upload a file
  2. getPersons from DB
  3. insert a User via form


## Routes:

-POST  /             controllers.ClientController.upload()

-GET  /newuser          controllers.ClientController.index1

-POST    /insert/user   controllers.ClientController.insertUser

-GET  /users             controllers.ClientController.getPersons


## Tests

- HomeControllerSpec:

- models/UserRepositorySpec:


