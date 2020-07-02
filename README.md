# Popular Movies Project

## Project Overview

In this project, **Popular Movies** app allows users to discover the most popular movies playing. Movies data fetched using themoviedb.org API.

## Learning Objectives
- Fetch data from the Internet with theMovieDB API.
- Use adapters and custom list layouts to populate list views.
- Incorporate libraries to simplify the amount of code you need to write
- Use Android Architecture Components (Room, LiveData, ViewModel and Lifecycle) to create a robust an efficient application.

## Features
- Sort movies based on popular, top rated and favorites.
- View details (overview, rating, release date, language) of a movie.
- View cast and trailers of a movie.
- Play and share trailers of a movie on youtube.
- Read reviews of a movie.
- Mark a movie to add it to favorites collection.
- Display favorite movies while offline.
- Handle network failures.

## Libraries
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) 
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
    * [Paging](https://developer.android.com/topic/libraries/architecture/paging/) 
    * [Room](https://developer.android.com/topic/libraries/architecture/room)
- [AndroidX](https://developer.android.com/jetpack/androidx/) 
- [Android Data Binding](https://developer.android.com/topic/libraries/data-binding/)
- [Retrofit 2](http://square.github.io/retrofit/)
- [Picasso](http://square.github.io/picasso/)

## Important Note
Make sure to insert your TheMovieDb API Key inside `gradle.properties` file.

```
API_KEY=<API_KEY>"
```

## Screenshots

### Stage-1
| Main Screen | Movie Details |  Settings Screen |
|:-:|:-:|:-:|
| ![1](demo/main_screen.png?raw=true) | ![2](demo/detail_screen.png?raw=true) | ![3](demo/settings_screen.png?raw=true) |
| No Network Connection |
| ![4](demo/no_network_screen.png?raw=true) |

### Stage-2
| Main Screen | Movie Details |  Settings Screen |
|:-:|:-:|:-:|
| ![1](demo/main_screen_stage2.png?raw=true) | ![2](demo/detail_screen_details_stage2.png?raw=true) | ![3](demo/settings_screen_stage2.png?raw=true) |
| Cast & Trailers | Reviews | No Network Connection |
| ![4](demo/cast_and_trailers_stage2.png?raw=true) | ![5](demo/reviews_stage2.png?raw=true) | ![6](demo/no_network_screen_stage2.png?raw=true) |
