package com.gdlist.gd.Service;

import com.gdlist.gd.Dto.AdminReqDto;
import com.gdlist.gd.Dto.LevelMapper;
import com.gdlist.gd.Dto.ResponseDto;
import com.gdlist.gd.ListRepository.LevelsRepository;
import com.gdlist.gd.Model.LevelModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GdListService {
    @Autowired
    private final LevelsRepository levelRepo;

    public GdListService(LevelsRepository levelRepo) {
        this.levelRepo = levelRepo;
    }

    public LevelModel save(AdminReqDto dto) {
        if(levelRepo.existsByPosition(dto.position())){
            throw new RuntimeException("position already occupied");
        }

        LevelModel level =  new LevelModel();
        level.setName(dto.name());
        level.setDescription(dto.description());
        level.setPosition(dto.position());
        level.setImage(dto.image());
        level.setVideo(dto.video());

        return levelRepo.save(level);
    }
    public List<ResponseDto> findAllByOrderByPositionAsc() {
        List<LevelModel> levels = levelRepo.findAllByOrderByPositionAsc();
        return levels.stream()
                .map(LevelMapper::levelToLevelDto)
                .toList();
    }
}
