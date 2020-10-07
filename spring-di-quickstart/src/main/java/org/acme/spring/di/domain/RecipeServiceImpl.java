/*
 * (c)Copyright Since 2020, SOFTBANK Corp. All rights reserved.
 *
 */

package org.acme.spring.di.domain;

import org.acme.spring.di.domain.Recipe.Recipe;
import org.acme.spring.di.integration.entity.RecipeEntity;
import org.acme.spring.di.integration.entity.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


/**
 * {@link RecipeService} の実装クラス。
 */
@Service
@Configuration
public class RecipeServiceImpl implements RecipeService {

  @Autowired
  RecipeRepository recipeRepository;

  private static final String LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT);


  /**
   * {@inheritDoc}
   */
  @Override
  public Recipe getRecipe(Integer recipeId) {

    Optional<RecipeEntity> optionalRecipeEntity = recipeRepository.findById(recipeId);

    if (optionalRecipeEntity.isPresent()) {
      RecipeEntity recipeEntity = optionalRecipeEntity.get();
      return Recipe.builder()
                   .id(recipeEntity.getId())
                   .title(recipeEntity.getTitle())
                   .ingredients(recipeEntity.getIngredients())
                   .makingTime(recipeEntity.getMakingTime())
                   .serves(recipeEntity.getServes())
                   .cost(recipeEntity.getCost())
                   .build();
    } else {
      return null;
    }
  }

  @Override
  public Recipe createRecipe(Recipe recipe) {
    RecipeEntity recipeEntity =  RecipeEntity.builder()
                                             .cost(recipe.getCost())
                                             .title(recipe.getTitle())
                                             .serves(recipe.getServes())
                                             .ingredients(recipe.getIngredients())
                                             .makingTime(recipe.getMakingTime())
                                             .createdAt(Timestamp.valueOf(LocalDateTime.now().format(dateTimeFormatter)))
                                             .updatedAt(Timestamp.valueOf(LocalDateTime.now().format(dateTimeFormatter)))
                                             .build();

    RecipeEntity createdEntity = recipeRepository.save(recipeEntity);

    return Recipe.builder()
                 .id(createdEntity.getId())
                 .title(createdEntity.getTitle())
                 .makingTime(createdEntity.getMakingTime())
                 .serves(createdEntity.getServes())
                 .ingredients(createdEntity.getIngredients())
                 .cost(createdEntity.getCost())
                 .build();
  }
}
