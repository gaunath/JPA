package com.gaurabe.jpa.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.gaurabe.jpa.demo.dao.UserRepository;
import com.gaurabe.jpa.demo.model.User;

import lombok.var;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repository;

	@PostConstruct
	public void initDB() {
		List<User> users = new ArrayList<>();
		users.add(new User(111, "ram", "IT", 23));
		users.add(new User(675, "shyaam", "IT", 24));
		users.add(new User(432, "laxman", "CIVIL", 26));
		users.add(new User(88, "maruti", "IT", 23));
		users.add(new User(765, "bali", "GOVT", 20));
		repository.saveAll(users);
	}

	public List<User> getUsers() {
		return repository.findAll();
	}

	public List<User> getEmployeeByProfession(String profession) {
		return repository.findByProfession(profession);
	}

	public long getCounts(int age) {
		return repository.countByAge(age);
	}

	public List<User> deleteUser(String name) {
		return repository.deleteByName(name);
	}

	public List<User> findByMultiCondition(String profession, int age) {
		return repository.findByProfessionAndAge(profession, age);
	}

	public List<User> getUsersIgnoreCase(String profession) {
		return repository.findByProfessionIgnoreCase(profession);
	}
	
	public List<User> getUserSort(String field) {
		return repository.findAll(Sort.by(Sort.Direction.ASC, field));
	}
	
	// pagination
	public Page<User> getPaginatedUser() {
		return repository.findAll(PageRequest.of(0, 1));
	}	

	// custom Query
	public List<User> getUsersCustomQuery() {
		return repository.getUsersCustomQuery();
	}
}
