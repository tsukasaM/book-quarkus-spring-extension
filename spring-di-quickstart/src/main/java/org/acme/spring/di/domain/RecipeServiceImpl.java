/*
 * (c)Copyright Since 2020, SOFTBANK Corp. All rights reserved.
 *
 */

package org.acme.spring.di.domain;

import org.acme.spring.di.domain.Recipe.Recipe;
import org.acme.spring.di.integration.entity.RecipeEntity;
import org.acme.spring.di.integration.entity.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * {@link RecipeService} の実装クラス。
 */
@Service
public class RecipeServiceImpl implements RecipeService {

  @Autowired
  RecipeRepository recipeRepository;

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
    return null;
  }

//  @Override
//  @Transactional
//  public Recipe createRecipe(Recipe recipe) {
//    RecipeEntity recipeEntity = RecipeEntityMapper.toEntity(recipe);
//    recipeEntity.persist();
//    return RecipeEntityMapper.fromEntity(recipeEntity);
//  }
}
