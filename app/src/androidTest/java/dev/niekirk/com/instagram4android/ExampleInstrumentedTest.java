package dev.niekirk.com.instagram4android;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.niekirk.com.instagram4android.requests.InstagramDirectShareRequest;
import dev.niekirk.com.instagram4android.requests.InstagramReelsTrayRequest;
import dev.niekirk.com.instagram4android.requests.InstagramSearchUsernameRequest;
import dev.niekirk.com.instagram4android.requests.InstagramUserStoryFeedRequest;
import dev.niekirk.com.instagram4android.requests.payload.InstagramReelsTrayFeedResult;
import dev.niekirk.com.instagram4android.requests.payload.InstagramSearchUsernameResult;
import dev.niekirk.com.instagram4android.requests.payload.InstagramStoryTray;
import dev.niekirk.com.instagram4android.requests.payload.InstagramUserStoryFeedResult;
import dev.niekirk.com.instagram4android.requests.payload.StatusResult;
import lombok.SneakyThrows;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    // Replace with real credentials for actual testing
    private static final String USERNAME = "1000_follower_shoutout";
    private static final String PASSWORD = "Checks759";

    private Instagram4Android instagram4Android;

    @Before
    public void init() {
        instagram4Android = Instagram4Android.builder().username(USERNAME).password(PASSWORD).build();
        instagram4Android.setup();
        try {
            instagram4Android.login();
        } catch (IOException e) {
            //System.out.println(e.getMessage());
        }
    }

    @Test
    @SneakyThrows
    public void getMpdManifest() {

        InstagramReelsTrayFeedResult result = instagram4Android.sendRequest(new InstagramReelsTrayRequest());
        List<InstagramStoryTray> trays = result.getTray();
        List<InstagramUserStoryFeedResult> userStories = new ArrayList<>();
        for(InstagramStoryTray tray : trays) {
            if(tray != null)
                userStories.add(instagram4Android.sendRequest(new InstagramUserStoryFeedRequest("" + tray.getUser().getPk())));
        }
        for(InstagramUserStoryFeedResult story : userStories) {
            if(story.getReel() == null) {
                System.out.println("NULL");
            } else {
                System.out.println(story.getReel().getBroadcast().getDash_abr_playback_url());
            }
        }

    }

    /*
    @Test
    public void sendMessageIsSuccessful() throws IOException {

        InstagramSearchUsernameResult result = instagram4Android.sendRequest(new InstagramSearchUsernameRequest("nieks759"));
        StatusResult result1 = instagram4Android.sendRequest(InstagramDirectShareRequest.builder(InstagramDirectShareRequest.ShareType.MESSAGE,
                Arrays.asList("" + result.getUser().getPk())).message("Hello").build());

        System.out.println("Hello" + result1.getMessage());
    }*/

}
