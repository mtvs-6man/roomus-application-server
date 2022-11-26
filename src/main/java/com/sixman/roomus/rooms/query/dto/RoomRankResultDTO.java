package com.sixman.roomus.rooms.query.dto;

import java.util.Date;

public interface RoomRankResultDTO {
    int getMemberNo();
    int getRoomNo();
    String getCategory();
    String getDescription();
    float getXsize();
    float getYsize();
    float getZsize();
    Date getCreatedDate();
    Date getLastModifiedDate();
    String getScreenShotUrl();
    int getCntLikes();
    int getRanking();
}