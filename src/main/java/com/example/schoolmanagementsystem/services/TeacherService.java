package com.example.schoolmanagementsystem.services;

import com.example.schoolmanagementsystem.entities.Teacher;
import com.example.schoolmanagementsystem.exceptions.TeacherException;
import com.example.schoolmanagementsystem.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    public TeacherRepository repository;

    public TeacherService(TeacherRepository repository){
        this.repository = repository;
    }


    public List<Teacher> getAllTeachers(){
        return repository.findAll();
    }

    public Teacher getTeacher(Integer id){
        return repository.findById(id).get();
    }
    public void saveTeacher(Teacher teacher) throws TeacherException {
        if (!repository.findAll().isEmpty()){
            for (Teacher teacher1 : repository.findAll()) {
                if (teacher1.equals(teacher)) {

                    throw new TeacherException("Ky mesues egziston");
                }else {
                    repository.save(teacher);
                }
            }
        }else repository.save(teacher);
    }

    public Teacher loginTeacher(String email, String password){
        return repository.findByEmailAndPassword(email, password);
    }

    public List<Teacher> findTeacherByFirstName(String firstName) throws TeacherException{
        if (!repository.findAll().isEmpty()){
            if (repository.findAllByFirstName(firstName).isEmpty()){
                throw new TeacherException("Nuk ka mesues me kete emer");
            }
            else return repository.findAllByFirstName(firstName);
        }
        else throw new TeacherException("Nuk ka mesues ne databaze");
    }

    public Teacher findByFirstNameAndLastName(String firstName, String lastName) throws TeacherException{
        if (!repository.findAll().isEmpty()){
            return repository.findByFirstNameAndLastName(firstName, lastName);
        }else throw new TeacherException("Nuk ka mesues ne databaze");
    }

    public void updateTeacher(Teacher teacher){
        if (!repository.findAll().isEmpty()){
            Optional<Teacher> updatedTeacher = repository.findById(teacher.getId());
            updatedTeacher.get().setFirstName(teacher.getFirstName());
            updatedTeacher.get().setLastName(teacher.getLastName());
            repository.save(updatedTeacher.get());
        }
    }

    public void deleteTeacher(Teacher teacher) throws TeacherException{
        if (!repository.findAll().isEmpty()){
            repository.findById(teacher.getId()).orElseThrow();
             teacher = repository.findById(teacher.getId()).get();
             repository.delete(teacher);
        }else throw new TeacherException("Nuk ka mesues ne databaze");
    }

    public void deleteTeacherFromId(Integer id){
        repository.deleteById(id);
    }

    public void deleteAllTeachers(){
        if (!repository.findAll().isEmpty()){
            repository.deleteAll();
        }else throw new TeacherException("Nuk ka mesues ne databaze");
    }
}
