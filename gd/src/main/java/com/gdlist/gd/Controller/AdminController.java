package com.gdlist.gd.Controller;

import com.gdlist.gd.Dto.AdminReqDto;
import com.gdlist.gd.Dto.AdminResponse;
import com.gdlist.gd.Dto.ResponseDto;
import com.gdlist.gd.Model.LevelModel;
import com.gdlist.gd.Service.GdListService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final GdListService gdListService;

    public AdminController(GdListService gdListService) {
        this.gdListService = gdListService;
    }

    @PostMapping("/lote")
    public ResponseEntity<List<ResponseDto>> postLista(@Valid @RequestBody List<AdminReqDto> dto){
        return ResponseEntity.ok(gdListService.salvarLista(dto));
    }

    @PostMapping("/post")
    public ResponseEntity<LevelModel> save(@Valid @RequestBody AdminReqDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(gdListService.save(dto));
    }

    @GetMapping("/listaradm")
    public ResponseEntity<List<AdminResponse>> listar(){
        return ResponseEntity.ok(gdListService.listarAdmin());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        gdListService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/level/{id}")
    public ResponseEntity<Void> edit(@PathVariable String id, @Valid @RequestBody AdminReqDto dto){
        gdListService.edit(id, dto);
        return ResponseEntity.ok().build();
    }
}

