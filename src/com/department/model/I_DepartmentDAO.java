package com.department.model;

import java.util.List;

public interface  I_DepartmentDAO {
	public void insertDep(DepartmentVO departmentVO);
	public void updateDep(DepartmentVO departmentVO);
	public List<DepartmentVO> getAllDep();
}