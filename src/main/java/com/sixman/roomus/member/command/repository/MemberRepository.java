package com.sixman.roomus.member.command.repository;

import com.sixman.roomus.member.command.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer > {

    Member findByMemberId(String memberId);


}
