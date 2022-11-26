package com.sixman.roomus.member.query.repository;

import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.query.dto.UserSerchDTO;
import com.sixman.roomus.member.query.model.MemberData;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberDataRepository extends JpaRepository<MemberData, Integer> {

    Boolean existsByMemberNo(int memberNo);

    MemberData findByMemberNo(int memberNo);
    @Query(name = "UserSerchQuery", nativeQuery = true)
    List<UserSerchDTO> findUser(@Param("name") String name);


}
