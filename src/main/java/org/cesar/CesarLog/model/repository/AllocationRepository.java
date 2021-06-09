package org.cesar.CesarLog.model.repository;

import org.cesar.CesarLog.model.entity.Allocation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AllocationRepository extends CrudRepository<Allocation, Integer> {
	
	@Query("select a from Allocation a where a.employee.project.id =?1")
	Iterable<Allocation> findByProject(int id);
	
	//@Query(value = "select * from allocation a where a.employee_id =?1", nativeQuery = true)
	@Query("select a from Allocation a where a.employee.id =?1")
	Iterable<Allocation> findByEmployee(int id);
	
}
