package com.gdlist.gd.Controller;

import com.gdlist.gd.Dto.AdminReqDto;
import com.gdlist.gd.Dto.ResponseDto;
import com.gdlist.gd.Model.LevelModel;
import com.gdlist.gd.Service.GdListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/levels")
public class LevelController {
    @Autowired
    private final GdListService service;

    public LevelController(GdListService service) {
        this.service = service;
    }
    @PostMapping("/post")
    public ResponseEntity<LevelModel> postLevel(@Valid @RequestBody AdminReqDto dto) {
        return ResponseEntity.ok().body(service.save(dto));
    }
    @GetMapping("/main")
    public ResponseEntity<List<ResponseDto>> findAllByOrderByPositionAsc() {
        return ResponseEntity.ok().body(service.findAllByOrderByPositionAsc());
    }
    @GetMapping("/mainlist")
    public ResponseEntity<Page<ResponseDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }
    @PostMapping("/lote")
    public ResponseEntity<List<ResponseDto>> lote(@Valid @RequestBody List<AdminReqDto> dto) {
        return ResponseEntity.ok(service.salvarLista(dto));
    }
}
