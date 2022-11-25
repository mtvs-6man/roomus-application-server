package com.sixman.roomus.member.command.repository;

import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.command.domain.model.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;


public interface RelationRepository extends JpaRepository<Relation, Integer> {

    Relation findByRelationUserAndFollowUser(Member relation, Member follow);

}
