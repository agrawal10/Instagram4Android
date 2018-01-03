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
import dev.niekirk.com.instagram4android.requests.InstagramTimelineFeedRequest;
import dev.niekirk.com.instagram4android.requests.InstagramUserStoryFeedRequest;
import dev.niekirk.com.instagram4android.requests.payload.InstagramBroadcast;
import dev.niekirk.com.instagram4android.requests.payload.InstagramReelsTrayFeedResult;
import dev.niekirk.com.instagram4android.requests.payload.InstagramSearchUsernameResult;
import dev.niekirk.com.instagram4android.requests.payload.InstagramStoryTray;
import dev.niekirk.com.instagram4android.requests.payload.InstagramSuggestedBroadcastResult;
import dev.niekirk.com.instagram4android.requests.payload.InstagramTimelineFeedItem;
import dev.niekirk.com.instagram4android.requests.payload.InstagramTimelineFeedResult;
import dev.niekirk.com.instagram4android.requests.payload.InstagramUser;
import dev.niekirk.com.instagram4android.requests.payload.InstagramUserStoryFeedResult;

import static junit.framework.Assert.assertEquals;

/**
 * Created by root on 20/08/17.
 */

public class InstagramAndroidTest {

    // Replace with real credentials for actual testing
    private static final String USERNAME = "nieks759";
    private static final String PASSWORD = "Checks759";
    //private static final String ACCESS_TOKEN = "EAABwzLixnjYBAEpNU1KOuZBmPvGiZBzMHLZBAsAwzp1ElHjHvdGnz9ZACsPrO3OYl0OrwSpCHYi8oVwMpCJyjVIyZAPRCmx3zP3He0xY7z9Ve29DJCMlXSkYQ99bDMe0zEgaS3ZAMIHP9lVqTkXZCkIgpjNNoUZC2yQZD";
    private static final String ACCESS_TOKEN = "EAABwzLixnjYBAKPLZCmZAVfHjbZAHCx4stOio87H5Ehf12rMy46NwgBO4NSxLSOsR3hQ4uiFZAaUEnfXbl0dvzbbZCSYp686Dn8gp9y5ZCAMZB9NM0YMkutP7881FVH86rEpZCWBlXK0ZBnJF4PCwCjvdwQu6kbcem7x31wZAP3CaEPNHmR10wjCra08xmuuyWAAd7kjdnb1ZAgewZDZD";

    private Instagram4Android instagram4Android;

    @Before
    public void init() {
        instagram4Android = Instagram4Android.builder().username(USERNAME).password(ACCESS_TOKEN).build();
        instagram4Android.setup();
        try {
            //instagram4Android.login();
            instagram4Android.loginFb();
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


    /*
    @Test
    public void storiesWorking() throws IOException {
        InstagramReelsTrayFeedResult result = instagram4Android.sendRequest(new InstagramReelsTrayRequest());
        List<InstagramStoryTray> trays = result.getTray();

        for(InstagramStoryTray tray : trays) {
            if(tray.getUser().getUsername().equalsIgnoreCase("hrvy")) {
                InstagramUserStoryFeedResult userResult = instagram4Android.sendRequest(new InstagramUserStoryFeedRequest("" + tray.getUser().getPk()));
                System.out.println(userResult.getPost_live_item().getBroadcasts().get(0).getDash_manifest());
            }
        }
    }*/

    @Test
    public void userFeedResultWorking() throws IOException {
        InstagramTimelineFeedResult result = instagram4Android.sendRequest(new InstagramTimelineFeedRequest());
        if(result != null) {
            for(InstagramTimelineFeedItem item : result.getFeed_items()) {
                System.out.println(item.getMedia_or_ad().getCaption().get("text"));
            }
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
