package com.gdlist.gd.ListRepository;

import com.gdlist.gd.Model.LevelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelsRepository extends JpaRepository<LevelModel, String> {
    List<LevelModel> findByName(String name);
    List<LevelModel> findAllByOrderByPositionAsc();
    boolean existsByPosition(Integer position);
}