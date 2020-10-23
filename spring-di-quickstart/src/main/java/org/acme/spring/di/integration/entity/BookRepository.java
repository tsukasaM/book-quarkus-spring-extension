package org.acme.spring.di.integration.entity;

import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<BookEntity, Integer> {

}
