package crud.example.demo.controller;

import crud.example.demo.entity.Student;
import crud.example.demo.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Student> getAll() { return repo.findAll(); }

    @PostMapping
    public Student create(@RequestBody Student student) { return repo.save(student); }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student updated) {
        Student s = repo.findById(id).orElseThrow();
        s.setName(updated.getName());
        s.setEmail(updated.getEmail());
        return repo.save(s);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "Deleted successfully!";
    }
}

