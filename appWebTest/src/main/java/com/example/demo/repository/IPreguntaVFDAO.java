package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.PreguntaVF;

public interface IPreguntaVFDAO extends PagingAndSortingRepository<PreguntaVF, Long>, CrudRepository<PreguntaVF, Long> { 



}
