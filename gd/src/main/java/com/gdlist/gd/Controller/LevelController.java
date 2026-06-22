package com.gdlist.gd.Controller;

import com.gdlist.gd.Dto.ResponseDto;
import com.gdlist.gd.Service.GdListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/levels")
public class LevelController {
    @Autowired
    private final GdListService service;

    public LevelController(GdListService service) {
        this.service = service;
    }
    @GetMapping("/mainpage")
    public ResponseEntity<List<ResponseDto>> findAllByOrderByPositionAsc() {
        return ResponseEntity.ok().body(service.findAllByOrderByPositionAsc());
    }
    @GetMapping("/mainlist")
    public ResponseEntity<Page<ResponseDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }
}
