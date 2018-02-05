# Instagram4Android

[![](https://jitpack.io/v/charlieAndroidDev/Instagram4Android.svg?style=flat-square)](https://jitpack.io/#charlieAndroidDev/Instagram4Android)  [![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)  [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Instagram4Android-brightgreen.svg?style=flat-square)](https://android-arsenal.com/details/1/6042)

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Instagram_logo_2016.svg/1024px-Instagram_logo_2016.svg.png" alt="Drawing" width="200" height="200"/>

This library directly talks to the private Instagram API, anything (well quite a lot) you can do on the IG app you can replicate with this library.  You also bypass the public API's rate limits. 

## Support me
Any support, money or otherwise, is greatly appreciated and motivates me to keep this library alive!  Also as a student beer money is always appreciated ;) !

Bitcoin: 1BFLxaSGkk9DRjx7WTg9NkcWtRUzF2Z7qe

## Credit
This was originally a port of a popular [Java repository](https://github.com/brunocvcunha/instagram4j) that provides access to Instagrams' private API.  This has been changed around to use OkHttp and various other fixes have been made to make it work with Android.  I will be updating this with more endpoints and so on as time goes on.

Project not in any way afilliated with Instagram.

## Fair Use
Do NOT use this to spam anyone at all.<br/>
Do NOT use this for any illegal activities.

## Installation

Add the following to your root ```build.gradle``` file:
```Gradle
allprojects {
    repositories {
	...
	maven { url 'https://jitpack.io' }
    }
}
```

Then add the following to your app level ```build.gradle``` file, use the version number of the latest release:
```Gradle
dependencies {
    compile 'com.github.charlieAndroidDev:Instagram4Android:$VERSION'
}
```
Make sure your min sdk is 19 or higher in your app-level build.gradle, like [this](https://i.imgur.com/DcopQLm.png).


## Instructions

### Login
```Java
Instagram4Android instagram = Instagram4Android.builder().username(username).password(password).build();
instagram.setup();
instagram.login();
```

You must call ```setup()``` before ```login()``` otherwise an exception is thrown.

Below is my implementation of logging in to Instagram in my presenter (MVP) using RxJava2 and RxAndroid:

```Java
private void loginToInstagram(final String username, final String password) {

        Instagram4Android instagram = Instagram4Android.builder().username(username).password(password).build();

        attemptLogin(instagram, username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(instagramLoginResult -> {

                    if(instagramLoginResult.getStatus().equals(view.getStringResource(R.string.login_success))) {
                        view.loginFinished(instagram);
                    } else {
                        view.loginFailed(instagramLoginResult.getMessage());
                    }

                });

    }

private Observable<InstagramLoginResult> attemptLogin(final Instagram4Android instagram) {

        Observable<InstagramLoginResult> observable = Observable.create(observableEmitter -> {

            instagram.setup();
            observableEmitter.onNext(instagram.login());

        });

        return observable;

    }       
```

The String resource ```R.string.login_success``` is ```"ok"```.

### Login with Facebook

This is in beta right now hence the weird login flow.

```Java
Instagram4Android instagram = Instagram4Android.builder().username("").password(FACEBOOK_ACCESS_TOKEN).build();
instagram.setup();
InstagramLoginResult result = instagram.loginFb();
```

After that, the rest is the same.

### Get user info

```Java
InstagramSearchUsernameResult result = instagram.sendRequest(new InstagramSearchUsernameRequest("example_username"));
InstagramUser user = result.getUser();
```

Send the request using your ```Instagram4Android``` instance, and then call ```getUser()``` on the result.  Now poke around and see what info you can get from that ```user``` object.

### Follow user

```Java
instagram.sendRequest(new InstagramFollowRequest(user.getPk()));
```

Very simple.

### Get stories (the ones you see at the top of your homepage)

```Java
InstagramReelsTrayFeedResult result = instagram4Android.sendRequest(new InstagramReelsTrayRequest());
List<InstagramStoryTray> trays = result.getTray();
```

```trays``` now contains a list of ```InstagramStoryTray``` which, for the first few elements, will have a list of story items and info about the user whose story it is.  After the first 3-5 elements the list of story items will be null (We'll fix that below).

To get the rest of the story items for everyone in your reel tray do this:

```Java
InstagramReelsTrayFeedResult result = instagram4Android.sendRequest(new InstagramReelsTrayRequest());
List<InstagramStoryTray> trays = result.getTray();

List<InstagramUserStoryFeedResult> userStories = new ArrayList<>();
for(InstagramStoryTray tray : trays) {
    if(tray != null) {
        userStories.add(instagram4Android.sendRequest(new InstagramUserStoryFeedRequest("" + tray.getUser().getPk())));
    }
}

// To print the url's of the first items in everyones story do this
for(InstagramUserStoryFeedResult story : userStories) {
    if(story.getReel() == null) {
        System.out.println("Null check for safety, hardly ever null");
    } else {
        System.out.println(story.getReel().getItems().get(0).getImage_versions2().getCandidates().get(0).getUrl());
    }
}
```
