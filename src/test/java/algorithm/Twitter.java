package algorithm;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Twitter {

    class User {
        public List<Integer> list = new ArrayList<>();

        public Set<Integer> set = new HashSet<>();
    }

    public Map<Integer, Integer> map = new HashMap<>();//存id，时间

    public Map<Integer, User> userMap = new HashMap<>();

    AtomicInteger time = new AtomicInteger();


    /** Initialize your data structure here. */
    public Twitter() {
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        User user = userMap.get(userId);
        if (user == null) {
            user = new User();
            userMap.put(userId, user);
        }
        user.list.add(tweetId);
        if (user.list.size() > 10) {
            user.list.remove(0);
        }
        map.put(tweetId, time.getAndIncrement());
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> list = new ArrayList<>();
        User user = userMap.get(userId);
        if (user != null) {
            list.addAll(user.list);
            if (user.set != null && user.set.size() > 0) {
                for (Integer integer : user.set) {
                    if (!integer.equals(userId)) {
                        User followee = userMap.get(integer);
                        if (followee != null && followee.list.size() > 0) {
                            list.addAll(followee.list);
                        }
                    }
                }
            }
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (int)(map.get(o2) - map.get(o1));
            }
        });
        if (list.size() > 10) {
            return list.subList(0, 10);
        }
        return list;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        User user = userMap.get(followerId);
        if (user == null) {
            user = new User();
            userMap.put(followerId, user);
        }
        user.set.add(followeeId);

    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        User user = userMap.get(followerId);
        if (user == null) {
            user = new User();
            userMap.put(followerId, user);
        }
        user.set.remove(followeeId);
    }

    public static void main(String[] args) {
        Twitter obj = new Twitter();
        obj.postTweet(1,5);
        obj.postTweet(1,3);
        List<Integer> param_2 = obj.getNewsFeed(1);
        obj.follow(1,2);
        obj.postTweet(2,6);
        List<Integer> param_3 = obj.getNewsFeed(1);
        obj.unfollow(1,2);

    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */