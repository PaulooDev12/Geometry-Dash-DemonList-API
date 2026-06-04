package com.gdlist.gd.Controller;

import com.gdlist.gd.Dto.AdminReqDto;
import com.gdlist.gd.Dto.AdminResponse;
import com.gdlist.gd.Dto.ResponseDto;
import com.gdlist.gd.Service.GdListService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @GetMapping("/listaradm")
    public ResponseEntity<List<AdminResponse>> listar(){
        return ResponseEntity.ok(gdListService.listarAdmin());
    }
}
