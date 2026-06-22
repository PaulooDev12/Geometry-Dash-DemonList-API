package com.gdlist.gd.Service;

import com.gdlist.gd.Dto.AdminReqDto;
import com.gdlist.gd.Dto.AdminResponse;
import com.gdlist.gd.Dto.LevelMapper;
import com.gdlist.gd.Dto.ResponseDto;
import com.gdlist.gd.Exception.BadRequestException;
import com.gdlist.gd.Exception.LevelAlredyExists;
import com.gdlist.gd.Exception.ResourceNotFoundException;
import com.gdlist.gd.ListRepository.LevelsRepository;
import com.gdlist.gd.Model.LevelModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GdListService {
    @Autowired
    private final LevelsRepository levelRepo;

    public GdListService(LevelsRepository levelRepo) {
        this.levelRepo = levelRepo;
    }


    @Transactional
    public LevelModel save(AdminReqDto dto) {

        if (dto.position() != null && dto.position() < 1) {
                throw new BadRequestException("Requested position is less than 1 / Requesição feita em uma posição menor que 1");
            }

            if(levelRepo.existsByPosition(dto.position())){
           List<LevelModel> levels = levelRepo.findAll();
           List<LevelModel> newLevels = levels.stream()
                   .filter(levelModel -> levelModel.getPosition().compareTo(dto.position()) >= 0)
                   .toList();
           for(LevelModel level : newLevels){
               level.setPosition(level.getPosition() + 1);
           }
            levelRepo.saveAll(newLevels);
        }

        LevelModel level =  new LevelModel();
        level.setName(dto.name());
        level.setDescription(dto.description());
        level.setPosition(dto.position());

        if(level.getPosition() == null){

            level.setPosition(
                    levelRepo.findMaxPosition() + 1
            );
        }
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
public List<AdminResponse> listarAdmin() {
        List<LevelModel> levels = levelRepo.findAllByOrderByPositionAsc();
        return levels.stream()
                .map(LevelMapper::MapperToAdmin)
                .toList();
}
public void deleteById(String id) {
        LevelModel level =  levelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("level not found"));
        Integer position = level.getPosition();
        List<LevelModel> levels = levelRepo.findAll().stream()
            .filter(l -> !l.getId().equals(level.getId()) && l.getPosition() >= position)
                .toList();
        levels.forEach(l -> l.setPosition(l.getPosition() - 1));
        levelRepo.delete(level);
    }

public void edit(String id, AdminReqDto dto) {
        LevelModel level = levelRepo.findById(id).orElseThrow(() -> new LevelAlredyExists(id));
        Integer oldPosition = level.getPosition();
        Integer newPosition = dto.position();

            if (newPosition < oldPosition) {

                List<LevelModel> afetados = levelRepo.findAll().stream()
                    .filter(l -> !l.getId().equals(level.getId())
                            && l.getPosition() >= newPosition
                            && l.getPosition() < oldPosition)
                    .toList();

                afetados.forEach(l ->
                    l.setPosition(l.getPosition() + 1));
            levelRepo.saveAll(afetados);
        }
        else if(newPosition > oldPosition){
            List<LevelModel> afetados = levelRepo.findAll().stream()
                    .filter(l -> !l.getId().equals(level.getId())
                            && l.getPosition() <= newPosition
                            && l.getPosition() > oldPosition)
                    .toList();
            afetados.forEach(l ->
                    l.setPosition(l.getPosition() - 1));
            levelRepo.saveAll(afetados);
        }
            level.setImage(dto.image() == null ? level.getImage() : dto.image());
            level.setPosition(newPosition);
            levelRepo.save(level);
    }
}

