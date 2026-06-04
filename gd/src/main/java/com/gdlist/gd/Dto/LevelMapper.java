package com.gdlist.gd.Dto;

import com.gdlist.gd.Model.LevelModel;

public class LevelMapper {
    public static ResponseDto levelToLevelDto(LevelModel level) {
        return new ResponseDto(
                level.getName(),
                level.getDescription(),
                level.getImage(),
                level.getPosition(),
                level.getVideo()
        );
    }

    public static LevelModel levelToEntity(AdminReqDto dto){
        LevelModel level = new LevelModel();
        level.setName(dto.name());
        level.setDescription(dto.description());
        level.setImage(dto.image());
        level.setPosition(dto.position());
        level.setVideo(dto.video());
        return level;
    }
    public static AdminResponse MapperToAdmin(LevelModel level) {
        return new AdminResponse(
                level.getName(),
                level.getDescription(),
                level.getPosition(),
                level.getImage(),
                level.getVideo(),
                level.getId()
        );
    }
}