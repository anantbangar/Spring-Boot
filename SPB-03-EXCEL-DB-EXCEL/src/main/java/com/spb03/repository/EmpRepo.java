package com.spb03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spb03.entities.Emp;

public interface EmpRepo extends JpaRepository<Emp, Integer>
{

}
