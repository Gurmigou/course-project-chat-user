package com.videochat.videochatuser.repository;

import com.videochat.videochatuser.model.entity.Talk;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalkRepository extends CrudRepository<Talk, Long> {
    @Query("""
            SELECT t FROM Talk t
            JOIN FETCH t.firstPeer fp
            JOIN FETCH t.secondPeer sp
            WHERE fp.username = ?1 OR sp.username = ?1
            """)
    List<Talk> findAllWhereUsernameIsPeer(String username);
}
