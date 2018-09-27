package com.star.seap.endpoint;

import com.star.seap.model.Aluno;
import com.star.seap.model.Curso;
import com.star.seap.repository.CursoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cursos")
public class CursoEndPoint {


    private final CursoRepo repo;

    @Autowired
    public CursoEndPoint(CursoRepo repo) {
        this.repo = repo;
    }


    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCursoById(@PathVariable("id") Long id){
        Curso curso = repo.getOne(id);
        if (curso == null)
            return new ResponseEntity<>(new Error("Este curso não existe"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Curso curso){
        return new ResponseEntity<>(repo.save(curso), HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<?> update(@RequestBody Curso curso){
        repo.save(curso);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@DeleteMapping
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/addAluno/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> addAluno(@PathVariable("id") Aluno alunos, Curso curso){

        curso.setAlunos(alunos);

        repo.save(curso);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
