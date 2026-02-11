package com.aluracursos.challenge_ForoHub.controller;

import com.aluracursos.challenge_ForoHub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
//        System.out.println(datos);
        var topico = new Topico(datos);
        topicoRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>>  listar(@PageableDefault(size = 10, sort = {"curso"}) Pageable paginacion){
        var page = topicoRepository.findAllByActivoTrue(paginacion).map(DatosListaTopico::new);

        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionTopico datos){
        var topico = topicoRepository.getReferenceById(datos.id());
        topico.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity desactivar(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        topico.desactivar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

}
