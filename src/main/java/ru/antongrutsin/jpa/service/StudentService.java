package ru.antongrutsin.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antongrutsin.jpa.model.Student;
import ru.antongrutsin.jpa.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(int id){
        studentRepository.deleteById(id);
    }

    public void updateStudent(Student student){
        Student updatedStudent = studentRepository.findById(student.getId());
        updatedStudent.setMark(student.getMark());
        updatedStudent.setName(student.getName());
        studentRepository.save(updatedStudent);
    }

    public List<Student> findAllStudent(){
        return studentRepository.findAll();
    }

    public Student findById(int id){
        return studentRepository.findById(id);
    }
}
