package com.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cache.dao.PersonRepository;
import com.cache.entity.Person;
import com.cache.service.DemoService;

@Service
public class PersonServiceImpl implements DemoService {

	@Autowired
	PersonRepository personRepository;

	@Override
	@CachePut(value = "people", key = "#person.id")
	public Person save(Person person) {
		Person p = personRepository.save(person);
		System.out.println("为id、key为" + p.getId() + "的数据做了缓存");
		return p;
	}

	@Override
	@CacheEvict(value = "person")
	public void remove(Long id) {
		System.out.println("删除了id、key为" + id + "的数据缓存");
		personRepository.deleteById(id);
	}

	@Override
	@Cacheable(value = "person", key = "#person.id")
	public Person findOne(Person person) {
		Person p = personRepository.getOne(person.getId());
		System.out.println("为id、key为：" + p.getId() + "数据做了缓存");
		return p;
	}

}
