package com.boot.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.boot.entity.Employee;
import com.boot.entity.LoginTable;

@Repository
public interface LoginTableRepository  extends JpaRepository<LoginTable, Long> {
	

	

}
