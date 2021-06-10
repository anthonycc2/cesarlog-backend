package org.cesar.CesarLog.model.repository;

import org.cesar.CesarLog.model.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	@Query("select e from Employee e where e.project.id = ?1")
	Iterable<Employee> findByProject(int id);
}
