# Distance Tracker

Distance tracker is an application that is able to measure and display trip distance covered from the starting point to the finish.
The app is prepared to run in background, but it doesn't seem to work and I had no time to debug what is the problem, although it works fine when it runs in the foreground.

## Architecture
I used a mixed MVVM - [Flux](https://facebook.github.io/flux/docs/in-depth-overview) approach, where Flux provides a straightforward - unidirectional - data flow model and besides that Room+RxJava works perfectly as the dispatcher.
The application is separated into 4 modules. The *domain* module holds the models required for the presentation layer, the *data* module handles the data flow, the *common* module contains base classes and common bindings and the *app* module holds the presentation layer.

## Places of improvement
* Fine-tune the location updates
* Add trip names (the domain already includes this functionality)
* Delete trips
* Detailed trips with start and end addresses (requires internet permission)
* Tests
