package com.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entity.StudentEntity;
import com.web.repository.StudentRepository;
import com.web.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public List<StudentEntity> getAllStudent() {
		
		return studentRepo.findAll();
	}
	@Override
	public StudentEntity save(StudentEntity entity) {
		// TODO Auto-generated method stub
		return studentRepo.save(entity);
	}

	@Override
	public StudentEntity getStudentById(Integer id) {
		// TODO Auto-generated method stub
		return studentRepo.findById(id).get();
	}

	@Override
	public StudentEntity update(StudentEntity entity) {
		// TODO Auto-generated method stub
		return studentRepo.save(entity);
	}
	@Override
	public void delete(Integer id) {
		studentRepo.deleteById(id);
		
	}

}
