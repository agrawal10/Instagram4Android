package dev.niekirk.com.instagram4android;

import android.util.Log;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dev.niekirk.com.instagram4android.requests.InstagramFollowRequest;
import dev.niekirk.com.instagram4android.requests.InstagramGetUserFollowingRequest;
import dev.niekirk.com.instagram4android.requests.InstagramSearchUsernameRequest;
import dev.niekirk.com.instagram4android.requests.InstagramTimelineFeedRequest;
import dev.niekirk.com.instagram4android.requests.InstagramUserFeedRequest;
import dev.niekirk.com.instagram4android.requests.payload.InstagramFeedItem;
import dev.niekirk.com.instagram4android.requests.payload.InstagramFeedResult;
import dev.niekirk.com.instagram4android.requests.payload.InstagramSearchUsernameResult;
import dev.niekirk.com.instagram4android.requests.payload.InstagramTimelineFeedItem;
import dev.niekirk.com.instagram4android.requests.payload.InstagramTimelineFeedResult;
import dev.niekirk.com.instagram4android.requests.payload.InstagramUser;
import dev.niekirk.com.instagram4android.requests.payload.InstagramUserSummary;

import static org.junit.Assert.assertEquals;

/**
 * Created by root on 20/08/17.
 */

public class InstagramAndroidTest {

    // Replace with real credentials for actual testing
    private static final String USERNAME = "XXXX";
    private static final String PASSWORD = "XXXX";

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
    public void userFeedResultWorking() throws IOException {
        InstagramFeedResult result = instagram4Android.sendRequest(new InstagramUserFeedRequest(instagram4Android.getUserId(), null, 0L));
        if(result != null) {
            System.out.println(result.getItems().get(0).getImage_versions2().getCandidates().get(0).getUrl());
        }
        assertEquals(1, 1);
    }

    /*
    @Test
    public void fixesImplementedCorrectly() throws IOException, InterruptedException {

        String maxId = null;

        for(int i = 0; i < 4; i++) {
            if(i > 0) {
                System.out.println("MAX ID: " + maxId);
            }
            InstagramTimelineFeedResult feedResult = instagram4Android.sendRequest(new InstagramTimelineFeedRequest(maxId, null));
            for(InstagramTimelineFeedItem item : feedResult.getFeed_items()) {
                if(item.getMedia_or_ad() == null || item.getMedia_or_ad().getImage_versions2() == null ||
                        item.getMedia_or_ad().getImage_versions2().getCandidates() == null) {
                    System.out.println("NO");
                } else {
                    System.out.println(item.getMedia_or_ad().getImage_versions2().getCandidates().get(0).getUrl());
                }
            }

            maxId = feedResult.getNext_max_id();
            Thread.sleep(5000);
        }
        assertEquals(1 , 1);
    }*/

    /*
    @Test
    public void instagramFollowUserWorksAsExpected() throws IOException {
        InstagramSearchUsernameResult usernameResult = instagram4Android.sendRequest(new InstagramSearchUsernameRequest("davidbeckham"));
        InstagramUser user = usernameResult.getUser();
        instagram4Android.sendRequest(new InstagramFollowRequest(user.getPk()));

        InstagramSearchUsernameResult myUsernameResult = instagram4Android.sendRequest(new InstagramSearchUsernameRequest(USERNAME));
        List<InstagramUserSummary> following = instagram4Android.sendRequest(new InstagramGetUserFollowingRequest(myUsernameResult.getUser().getPk())).getUsers();

        for(Iterator<InstagramUserSummary> iterator = following.iterator(); iterator.hasNext(); ) {
            InstagramUserSummary userSummary = iterator.next();
            if(!userSummary.getUsername().equalsIgnoreCase("davidbeckham")) {
                iterator.remove();
            }
        }

        assertEquals(1, following.size());
    }*/

}
