package com.example.schoolmanagementsystem.services;

import com.example.schoolmanagementsystem.entities.Student;
import com.example.schoolmanagementsystem.exceptions.StudentException;
import com.example.schoolmanagementsystem.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class StudentService {

    public StudentRepository repository;

    public StudentService(StudentRepository repository){
        this.repository = repository;
    }

    public List<Student> getAllStudents(){
        return repository.findAll();
    }

    public Student getStudentById(Long id){
        return repository.findById(id).get();
    }

    public void saveStudent(Student student) throws StudentException {
        if (!repository.findAll().isEmpty()){
            for (Student student1 : repository.findAll()){
                if (student1.equals(student)){
                    throw new StudentException("Studenti egziston");
                }else {
                    repository.save(student);
                }
            }
        }else repository.save(student);

    }

    public List<Student> loginStudent(String email, String password){
        return repository.findByEmailAndPassword(email, password);
    }

    public List<Student> findByFirstName(String firstName) throws StudentException{
        List<Student> students = new ArrayList<>();
        for (Student student : repository.findAll()){
            if (student.getFirstName().equals(firstName)){
                students.add(student);
            }else {
                continue;
            }
        }
        if (students.isEmpty()){
            throw new StudentException("Nuk ka student me kete emer");
        }
        return students;
    }


    public List<Student> findingByFirstName(String firstName) throws StudentException{
        if (repository.findAllByFirstName(firstName).isEmpty()){
            throw new StudentException("Nuk ka student me emerin " + firstName);
        }else
            return repository.findAllByFirstName(firstName);
    }

    public List<Student> orderStudentsByAge(int age) throws StudentException{
        if (repository.findAllByAgeOrderByAge(age).isEmpty()){
            throw new StudentException("Nuk ka student ne moshen "+ age);
        }else
            return repository.findAllByAgeOrderByAge(age);
    }


    public List<Student> orderByAge() throws StudentException{
        List<Student> students =repository.findAll();
        List<Student> sortedStudents = new ArrayList<>();
        for (int i = 0; i < repository.findAll().size(); i++) {
            if (i + 1 == repository.findAll().size()){
                break;
            }
            if (students.get(i).getAge() <= students.get(i + 1).getAge()){
                sortedStudents.add(students.get(i));
            }
        }
        return sortedStudents;
    }

    public void deleteStudent(Student student) throws StudentException{
        for (Student student1 : repository.findAll()) {
            if (student1.equals(student))
                repository.delete(student);
            else throw new StudentException("Ky student nuk egziston");
        }
    }


    public void deleteStudentById(Long id){
        repository.deleteById(id);
    }

    public void updateStudent(Student student){
        repository.save(student);
    }

    public Student findByEmail(String email){
        return repository.findByEmail(email).get();
    }
}
