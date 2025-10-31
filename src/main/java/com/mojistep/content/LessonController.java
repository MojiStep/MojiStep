package com.mojistep.content;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class LessonController {
  private final LessonRepository repo;

  @GetMapping
  public Page<Lesson> list(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="20") int size){
    return repo.findAll(PageRequest.of(page, size));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Lesson> get(@PathVariable Integer id){
    return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

//  @PreAuthorize("hasAuthority('LESSON_WRITE')")
  @PostMapping
  public Lesson create(@RequestBody Lesson body){ return repo.save(body); }

//  @PreAuthorize("hasAuthority('LESSON_WRITE')")
  @PutMapping("/{id}")
  public ResponseEntity<Lesson> update(@PathVariable Integer id, @RequestBody Lesson body){
    return repo.findById(id).map(existing -> {
      body.setId(id);
      return ResponseEntity.ok(repo.save(body));
    }).orElse(ResponseEntity.notFound().build());
  }

//  @PreAuthorize("hasAuthority('LESSON_WRITE')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id){
    if(repo.existsById(id)){ repo.deleteById(id); return ResponseEntity.noContent().build(); }
    return ResponseEntity.notFound().build();
  }
}
