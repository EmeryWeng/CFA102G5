package com.department.model;

import java.util.List;

public class DepService {
	private I_DepartmentDAO dao;

	public DepService() {
		dao = new DepartmentDAO();
	}

	public DepartmentVO addDep(Integer dep_no, String dep_name, Boolean dep_state) {

		DepartmentVO departmentVO = new DepartmentVO();
		departmentVO.setDep_no(dep_no);
		departmentVO.setDep_name(dep_name);
		departmentVO.setDep_state(dep_state);

		dao.insertDep(departmentVO);
		return departmentVO;
	}

	public DepartmentVO updateDep(String dep_name, Boolean dep_state, Integer dep_no) {

		DepartmentVO departmentVO = new DepartmentVO();

		departmentVO.setDep_name(dep_name);
		departmentVO.setDep_state(dep_state);
		departmentVO.setDep_no(dep_no);

		dao.updateDep(departmentVO);
		return departmentVO;
	}

	public List<DepartmentVO> getAll() {
		return dao.getAllDep();
	}
	
}
