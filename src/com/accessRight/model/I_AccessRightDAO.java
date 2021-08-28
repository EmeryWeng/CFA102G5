package com.accessRight.model;

import java.util.List;


public interface I_AccessRightDAO {
	public void insert(AccessRightVO accessrightVO);
	public void update(AccessRightVO accessrightVO,Integer dep_no,Integer fun_no);
	public void delete(AccessRightVO accessrightVO,Integer dep_no,Integer fun_no);
	public List<AccessRightVO> findDepByFK(int dep_no);
	public List<AccessRightVO> findFunByFK(int fun_no);
	public List<AccessRightVO> getAllAcc();
}
