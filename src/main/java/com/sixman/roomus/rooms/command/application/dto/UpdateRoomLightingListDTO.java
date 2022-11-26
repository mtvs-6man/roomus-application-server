package com.sixman.roomus.rooms.command.application.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoomLightingListDTO {
    List<RoomLightingRequestDTO> lights;
}
