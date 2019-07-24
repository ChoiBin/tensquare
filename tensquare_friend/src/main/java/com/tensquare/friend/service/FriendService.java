package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userid,String friendid){
        //先判断userId到friendid是否有数据，有就是重复添加，返回0
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if(friend != null){
            return 0;
        }
        //直接添加好友，让好友表中userID到friendID方向的type为0
        friend = new Friend();
        friend.setUserid(userid);
        friend.setUserid(userid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断friendid到userid是否有数据，有的话，就把双方的type都改为1
        if(friendDao.findByUseridAndFriendid(friendid,userid) != null){
            //把双方的isLike更新为1
            friendDao.updateIslike("1",userid,friendid);
            friendDao.updateIslike("1",friendid,userid);
        }
        return 1;
    }

    public int addNoFriend(String userId, String friendid) {
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userId, friendid);
        if(noFriend != null){
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }

    public void deleteFriend(String userId, String friendid) {
        friendDao.deletefriend( userId, friendid);
        friendDao.updateIslike("0",userId,friendid);
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
}
