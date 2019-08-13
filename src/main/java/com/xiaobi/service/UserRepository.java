package com.xiaobi.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xiaobi.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public <list>User findByName(String name);
}
