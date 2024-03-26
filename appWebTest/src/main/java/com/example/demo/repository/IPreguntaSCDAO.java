package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.PreguntaSC;

public interface IPreguntaSCDAO extends PagingAndSortingRepository<PreguntaSC, Long>, CrudRepository<PreguntaSC, Long> {

}
