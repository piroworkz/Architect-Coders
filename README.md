# Architect Coders
<!-- ABOUT THE PROJECT -->

### About The Project

Sample e-commerce app for Antonio Leiva´s Architect Coders course. (https://architectcoders.com).


![Screenshot_2022-08-22-21-08-20-224_com piroworkz architectcoders](https://user-images.githubusercontent.com/69691740/186054742-7eb00dcf-2586-44e3-ae1e-af1dcfe94e2e.jpg)
![Screenshot_2022-08-22-21-09-49-631_com piroworkz architectcoders](https://user-images.githubusercontent.com/69691740/186054750-fc910e92-a555-4176-8cae-734a898df7e6.jpg)

![Screenshot_2022-08-22-21-09-57-452_com piroworkz architectcoders](https://user-images.githubusercontent.com/69691740/186054752-0e6e038a-6d26-4c81-a887-867650f37353.jpg)
![Screenshot_2022-08-22-21-10-08-118_com piroworkz architectcoders](https://user-images.githubusercontent.com/69691740/186054755-87c7081a-1d0f-4c05-b9a8-20f8306d6b29.jpg)

https://www.youtube.com/watch?v=CekasCAgnu0


* Used best practices for development such as clean coding standards, modularity, object-oriented programming concepts like inheritance & polymorphism etc.

* App attempts to follow Android’s Guide to app architecture, Separation fo concerns, Drive UI from data models, Single source of truth and Unidirectional Data Flow.

* Unit integration and Ui testing.


### Built with

* Clean Architecture.
* MVVM.
* Material Design.
* Jetpack Navigation.
* Room Database.
* Coroutines.
* Location Services.
* Glide.
* Android Permissions.
* Retrofit.
* Arrow Either.
* Hilt Dependency Injection.
* Firebase
  * Firestore
  * FirebaseAuthtUI
* Junit4
* Mockito
* Turbine
* Truth
<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

*To run this app you need to setUp a firebase project and
add google-services.json file to app module, firebase project will provide products and banners for app store.

*For Facebook sign in, you need to setup these variables:
facebook_application_id
facebook_client_token
fb_login_protocol_scheme
(You could also disable facebook auth provider in file: com.piroworkz.architectcoders.app.ui.login.AuthFirebase)

*For GeoNamesService to work you need to create an account at: http://www.geonames.org/
and add your username to the service query.

*To be able to run Ui Tests you need to set up firebase emulators cli for authentication,
you can find instructions on file : com.piroworkz.architectcoders.app.ui.login.MainUITest

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Installation

Clone the repo
   ```sh
   git clone https://github.com/piroworkz/Architect-Coders.git
   ```
(project has only one branch)

<p align="right">(<a href="#readme-top">back to top</a>)</p>