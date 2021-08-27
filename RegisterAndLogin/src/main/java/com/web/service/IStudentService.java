package com.web.service;

import java.util.List;

import com.web.entity.StudentEntity;

public interface IStudentService {
	List<StudentEntity> getAllStudent();
	StudentEntity save(StudentEntity entity);
	StudentEntity getStudentById(Integer id);
	StudentEntity update(StudentEntity entity);
	void delete(Integer id);
}
