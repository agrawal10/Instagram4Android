# Instagram4Android

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Instagram_logo_2016.svg/1024px-Instagram_logo_2016.svg.png" alt="Drawing" width="200" height="200"/>

This library directly talks to the private Instagram API, anything (well quite a lot) you can do on the IG app you can replicate with this library.  You also bypass the public API's rate limits. 

## Credit
This is a port of a popular [Java repository](https://github.com/brunocvcunha/instagram4j) that provides access to Instagrams' private API.  This has been changed around to use OkHttp and various other fixes have been made to make it work with Android.  I will be updating this with more endpoints and so on as time goes on.  

Project not in any way afilliated with Instagram.

## Fair Use
Do NOT use this to spam anyone at all.<br/>
Do NOT use this for any illegal activities.

## Instructions

### Login
```java
Instagram4Android instagram = Instagram4Android.builder().username(username).password(password).build();
instagram.setup();
instagram.login();
```

You must call ```setup()``` before ```login()``` otherwise an exception is thrown.

Below is my implementation of logging in, using RxJava2 and RxAndroid while also utilising MVP:

```java
Instagram4Android instagram = Instagram4Android.builder().username(username).password(password).build();
Completable.fromAction(() -> instagram.setup())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(() ->
                Completable.fromAction(() -> instagram.login())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> view.loginFinished(instagram)));        
```

### Get user info

```java
InstagramSearchUsernameResult result = instagram.sendRequest(new InstagramSearchUsernameRequest("example_username");
InstagramUser user = result.getUser();
```

Send the request using your ```Instagram4Android``` instance, and then call ```getUser()``` on the result.  Now poke around and see what info you can get from that ```user``` object!

### Follow user

```java
instagram.sendRequest(new InstagramFollowRequest(user.getPk()));
```

Very simple.

## Being updated currently...
