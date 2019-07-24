package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String> {

    public Friend findByUseridAndFriendid(String userid,String friendid);

    @Modifying
    @Query(value = "UPDATE tb_friend set islike = ? where userid = ? and friendid = ?",nativeQuery = true)
    public void updateIslike(String islike,String userid,String friendid);

    @Modifying
    @Query(value = "delete from tb_friend where userid = ? and friendid = ?",nativeQuery = true)
    void deletefriend(String userId, String friendid);
}
