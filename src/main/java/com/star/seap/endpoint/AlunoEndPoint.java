package com.star.seap.endpoint;

import com.star.seap.model.Aluno;
import com.star.seap.repository.AlunoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("alunos")
public class AlunoEndPoint {

    private final AlunoRepo repo;

    @Autowired
    public AlunoEndPoint(AlunoRepo repo) {
        this.repo = repo;
    }


    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getAlunoById(@PathVariable("id") Long id){
        Aluno aluno = repo.getOne(id);
        if (aluno == null)
            return new ResponseEntity<>(new Error("Este aluno não existe"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Aluno aluno){
        return new ResponseEntity<>(repo.save(aluno), HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<?> update(@RequestBody Aluno aluno){
        repo.save(aluno);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //@DeleteMapping
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
