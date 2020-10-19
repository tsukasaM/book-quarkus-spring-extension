package org.acme.spring.di.integration.entity;

import org.springframework.data.repository.CrudRepository;


public interface RecipeRepository extends CrudRepository<RecipeEntity, Integer> {

}
