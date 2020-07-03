package octils.base.twit;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TwitterManager {

    private static TwitterManager tm;

    private String lastTweet;
    private Twitter twitter;
    private Long lastTweetId;


    private TwitterManager(){
        this.twitter = null;
        this.lastTweetId = -1L;
        this.lastTweet = null;
    }

    public static TwitterManager get() {
        return tm == null ? tm = new TwitterManager() : tm;
    }

    public void setup(String key, String secret, String token, String tokenSecret){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).
        setOAuthConsumerKey(key).
        setOAuthConsumerSecret(secret).
        setOAuthAccessToken(token).
        setOAuthAccessTokenSecret(tokenSecret);
        this.twitter = new TwitterFactory(cb.build()).getInstance();
    }

    public Long getLastTweetId(){
        return this.lastTweetId;
    }

    public String getLastTweet(){
        return this.lastTweet;
    }


    public boolean tweet(String message){
        StatusUpdate status = new StatusUpdate(message);
        status.setMedia(null);
        try {
            this.lastTweetId = twitter.updateStatus(status).getId();
            this.lastTweet = "https://twitter.com/"+twitter.getScreenName()+"/status/"+this.lastTweetId;
            return true;
        }
        catch (TwitterException e) {
            e.getErrorMessage();
            return false;
        }
    }

    public boolean destroyStatus(long l){
        try {
            this.twitter.destroyStatus(l);
            this.lastTweetId = -1L;
            this.lastTweet = null;
            return  true;
        } catch (TwitterException e) {
            e.getErrorMessage();
            return false;
        }
    }

    public void sendMessage(long id, String message){
        try {
            twitter.sendDirectMessage(id, message);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    public Map<Long, String> getRepliesForUhc(long tweetID) {
        Map<Long, String> replies = new HashMap<>();

        try {
            String name = twitter.getScreenName();
            List<Long> ids = twitter.getRetweets(tweetID).stream().map(status -> status.getUser().getId()).collect(Collectors.toList());

            Query query = new Query("to:" + name + " since_id:" + tweetID);
            QueryResult results;

            do {
                results = twitter.search(query);
                results.getTweets().forEach(tweet ->{
                    String response = tweet.getText().replace("@"+name,"").trim();
                    long id = tweet.getUser().getId();
                    if(tweet.getInReplyToStatusId()==tweetID && ids.contains(id)){
                        replies.put(id, response);
                    }
                });
            } while ((query = results.nextQuery()) != null);

        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return replies;
    }
}
