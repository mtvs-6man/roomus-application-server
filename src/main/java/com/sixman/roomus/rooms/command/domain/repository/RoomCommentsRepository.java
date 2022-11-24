package com.sixman.roomus.rooms.command.domain.repository;

import com.sixman.roomus.rooms.command.domain.model.RoomComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomCommentsRepository extends JpaRepository<RoomComment, Integer> {
    Optional<RoomComment> findByCommentNoAndIsDelete(int commentNo, boolean isDelete);
}
