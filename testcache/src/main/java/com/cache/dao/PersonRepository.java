package com.cache.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cache.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
