# Archtouch Challenge
## Prerequisite

* Android Studio 3.0 or later
* Kotlin Support

## Libraries

* Retrofit 2 (API)
Retrofit is a good library to do network requests and it have a big support for custom implementations and integrations (if needed)

* Fresco (Image Loader)
Builded by Facebook, this library is an excelent library to load images. It has a lot of options as LocalCache, zoonable image and is easy to use

## Architecture

Was choiced a MVP architeture to build this project. 
This architeture was selected because is fast and easy to implement. We can improve and increment the project in an easy way.
We has a Repository layer to turn easy change the fonts of informations.
A API proxy was insered to inject the api_key and locale in the API requests.
