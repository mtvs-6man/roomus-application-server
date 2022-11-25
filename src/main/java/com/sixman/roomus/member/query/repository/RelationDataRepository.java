package com.sixman.roomus.member.query.repository;

import com.sixman.roomus.member.query.dto.RelationDTO;
import com.sixman.roomus.member.query.model.MemberData;
import com.sixman.roomus.member.query.model.RelationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RelationDataRepository extends JpaRepository<RelationData, Integer> {

    @Query(name = "followersList", nativeQuery = true)
    List<RelationDTO> followersList(MemberData memberData);

    @Query(name = "followList", nativeQuery = true)
    List<RelationDTO> followList(MemberData memberData);
}
