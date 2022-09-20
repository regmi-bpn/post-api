package com.postapi.feed.repository;

import com.postapi.feed.entity.NewsFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsFeedRepository extends JpaRepository<NewsFeed, Long> {

    @Query("select newsFeed from NewsFeed newsFeed inner join Users users " +
            "on users.id=newsFeed.users.id where newsFeed.users.id=?1 and newsFeed.id=?2")
    Optional<NewsFeed> getNewsFeedById(Long id, Long newsFeedId);

    @Query("select newsFeed from NewsFeed newsFeed inner join Users users on users.id= newsFeed.users.id where newsFeed.users.id=?1 order by newsFeed.id desc")
    List<NewsFeed> getNewsFeedOfUser(Long id);

    @Modifying
    @Query("delete from NewsFeed newsFeed where newsFeed.id=:id")
    void deleteNewsFeed(Long id);

    @Query("select newsFeed from NewsFeed newsFeed where newsFeed.id=:id")
    Optional<NewsFeed> getNewsFeed(Long id);

}
