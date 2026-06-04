package com.gdlist.gd.Service;

import com.gdlist.gd.Dto.AdminReqDto;
import com.gdlist.gd.Dto.AdminResponse;
import com.gdlist.gd.Dto.LevelMapper;
import com.gdlist.gd.Dto.ResponseDto;
import com.gdlist.gd.Exception.BadRequestException;
import com.gdlist.gd.Exception.LevelAlredyExists;
import com.gdlist.gd.ListRepository.LevelsRepository;
import com.gdlist.gd.Model.LevelModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;

@Service
public class GdListService {
    @Autowired
    private final LevelsRepository levelRepo;

    public GdListService(LevelsRepository levelRepo) {
        this.levelRepo = levelRepo;
    }

    public LevelModel save(AdminReqDto dto) {

        if (dto.position() < 1) {
            throw new BadRequestException("Requested position is less than 1 / Requesição feita em uma posição menor que 1");
        }

        if(levelRepo.existsByPosition(dto.position())){
            throw new LevelAlredyExists("position already occupied");
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
public Page<ResponseDto> listar(Pageable pageable) {
        return levelRepo.findAll(pageable).map(LevelMapper::levelToLevelDto);
}
public List<ResponseDto> salvarLista(List<AdminReqDto> dto) {
        List<LevelModel> levels = dto.stream()
                .map(LevelMapper::levelToEntity)
                .toList();
        List<LevelModel> savedLevels = levelRepo.saveAll(levels);
        return savedLevels.stream()
                .map(LevelMapper::levelToLevelDto)
                .toList();
}
public Page<AdminResponse> listarAdmin(Pageable pageable) {
        return levelRepo.findAll(pageable).map(LevelMapper::MapperToAdmin);
}
}