package org.cesar.CesarLog.model.repository;

import org.cesar.CesarLog.model.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
