package com.sixman.roomus.rooms.query.repository;

import com.sixman.roomus.rooms.query.dto.RoomRankResultDTO;
import com.sixman.roomus.rooms.query.model.RoomData;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomDataRepository extends JpaRepository<RoomData, Integer> {

    Optional<RoomData> findByRoomNoAndIsDelete(int roomNo, boolean isDelete);
    Optional<RoomData> findByRoomNoAndIsDeleteAndAccess(int roomNo, boolean isDelete, boolean access);

    @Query("select a " +
            " from RoomData a  " +
            "where a.isDelete = false " +
            "  and a.memberNo = :memberNo " +
            "order by a.roomNo")
    List<RoomData> findRoomListByMemberNo(int memberNo);

    List<RoomData> findAllByMemberNoAndIsDeleteOrderByRoomNo(int memberNo, boolean isDelete);

    List<RoomData> findAllByIsDeleteAndAccessOrderByRoomNo(boolean isDelete, boolean access);
    List<RoomData> findAllByMemberNoAndIsDeleteAndAccessOrderByRoomNo(int memberNo, boolean isDelete, boolean access);
    List<RoomData> findAllByCategoryAndIsDeleteAndAccessOrderByRoomNo(String category, boolean isDelete, boolean access);

    @Query("select a " +
           "  from RoomData a " +
            " join fetch a.roomLikesMemberDataList b " +
            "where b.roomLikesMemberPK.memberNo = :memberNo " +
            "  and a.isDelete = false " +
            "order by a.roomNo")
    List<RoomData> findMyLikesList(int memberNo);

    @Query(value = "select " +
            "     d.member_no memberNo " +
            "     , d.room_no roomNo\n" +
            "     , d.category category\n" +
            "     , d.room_name roomName\n" +
            "     , d.description description\n" +
            "     , d.x_size xsize\n" +
            "     , d.y_size ysize\n" +
            "     , d.z_size zsize\n" +
            "     , d.created_date createdDate\n" +
            "     , d.last_modified_date lastModifiedDate\n" +
            "     , d.url_screenshot screenShotUrl\n" +
            "     , c.cntLikes cntLikes\n" +
            "     , c.ranking\n" +
            "from tbl_room d\n" +
            "         join (select a.room_no\n" +
            "                    , count(a.*) cntLikes\n" +
            "                    , rank() over (order by count(a.*) desc ) ranking\n" +
            "                 from tbl_room_likes_member a\n" +
            "               group by a.room_no\n" +
            ") c on d.room_no = c.room_no\n" +
            "where d.is_delete = false\n" +
            "  and d.access = true\n" +
            "  and d.member_no = :memberNo", nativeQuery = true)
    List<RoomRankResultDTO> findLikesRanking(int memberNo);


}