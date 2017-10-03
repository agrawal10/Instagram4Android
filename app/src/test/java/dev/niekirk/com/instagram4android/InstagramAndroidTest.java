package dev.niekirk.com.instagram4android;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.niekirk.com.instagram4android.requests.InstagramDirectShareRequest;
import dev.niekirk.com.instagram4android.requests.InstagramReelsTrayRequest;
import dev.niekirk.com.instagram4android.requests.InstagramSearchUsernameRequest;
import dev.niekirk.com.instagram4android.requests.InstagramSuggestedBroadcastRequest;
import dev.niekirk.com.instagram4android.requests.InstagramUserStoryFeedRequest;
import dev.niekirk.com.instagram4android.requests.payload.InstagramBroadcast;
import dev.niekirk.com.instagram4android.requests.payload.InstagramReelsTrayFeedResult;
import dev.niekirk.com.instagram4android.requests.payload.InstagramSearchUsernameResult;
import dev.niekirk.com.instagram4android.requests.payload.InstagramStoryTray;
import dev.niekirk.com.instagram4android.requests.payload.InstagramSuggestedBroadcastResult;
import dev.niekirk.com.instagram4android.requests.payload.InstagramUser;
import dev.niekirk.com.instagram4android.requests.payload.InstagramUserStoryFeedResult;

/**
 * Created by root on 20/08/17.
 */

public class InstagramAndroidTest {

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

    /*
    @Test
    public void sendMessageIsSuccessful() throws IOException {

        InstagramSearchUsernameResult result = instagram4Android.sendRequest(new InstagramSearchUsernameRequest("nieks759"));
        instagram4Android.sendRequest(InstagramDirectShareRequest.builder(InstagramDirectShareRequest.ShareType.MESSAGE,
                Arrays.asList("" + result.getUser().getPk())).message("Hello").build());

    }*/


    @Test
    public void storiesWorking() throws IOException {
        InstagramSuggestedBroadcastResult result = instagram4Android.sendRequest(new InstagramSuggestedBroadcastRequest());
        for(InstagramBroadcast broadcast : result.getBroadcasts()) {
            System.out.println(broadcast.getBroadcast_owner().getUsername());
        }
    }

    /*
    @Test
    public void userFeedResultWorking() throws IOException {
        InstagramFeedResult result = instagram4Android.sendRequest(new InstagramUserFeedRequest(instagram4Android.getUserId(), null, 0L));
        if(result != null) {
            System.out.println(result.getItems().get(0).getImage_versions2().getCandidates().get(0).getUrl());
        }
        assertEquals(1, 1);
    }*/

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
