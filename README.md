# Privacy scanner for Android

### A privacy scanner android app. That scanns through all your android system and show privacy status in a stupid simple way. 


Some screenshots attached here for ref.

!["Splash Screen"](./images/splash-screen.jpg)
### Fig: 1.1 Splash Screen with customized icon


!["Splash Screen"](./images/scan-screen.jpg)
### Fig: 1.2 Scan Screen


<img src="./images/home-screen.jpg" alt="Home screen" width="200px"/>
### Fig: 1.3 Home screen after finish scanning



## Features of the application

- User Installs the application
- User taps on the app icon. 
- The app starts immidiately showing a scan screen
- This scanning process doesn't block the UI process. You can see the spinning animation without breaking.
- After scanning the whole system, the application shows all the applications in a LazyList view. 
- User can tap on any application to see it's details. 
- User can minimize the application and the state will still be hold by the lifecycle aware view model. 
- All the heavy computational tasks are carried out by the Kotlin Coroutine where necessary. 

- Taping on scan again button will allow user to scan the whole system again. 
- The share button let's user share a small summary as text with any app that supports text sharing. 


