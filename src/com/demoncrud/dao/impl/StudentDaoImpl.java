package com.demoncrud.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.demoncrud.model.Student;

public class StudentDaoImpl extends GeneralDaoImpl<Student> {

	public StudentDaoImpl() {
		super(Student.class);
	}

	@Override
	public Student convertToObject(ResultSet rs) {
		try {
			return new Student(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("address")
					);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
