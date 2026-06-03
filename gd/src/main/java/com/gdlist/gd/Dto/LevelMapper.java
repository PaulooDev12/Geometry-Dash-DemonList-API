package com.gdlist.gd.Dto;

import com.gdlist.gd.Model.LevelModel;

import java.util.logging.Level;

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
}
