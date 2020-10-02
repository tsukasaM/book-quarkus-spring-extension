/*
 * (c)Copyright Since 2020, SOFTBANK Corp. All rights reserved.
 *
 */

package org.acme.spring.di.domain;

import lombok.NoArgsConstructor;
import org.acme.spring.di.domain.Recipe.Recipe;

import javax.enterprise.context.ApplicationScoped;

/**
 * {@link RecipeService} の実装クラス。
 */
@NoArgsConstructor
@ApplicationScoped
public class RecipeServiceImpl implements RecipeService {

  /**
   * {@inheritDoc}
   */
  @Override
  public Recipe getRecipe(Integer recipeId) {
    return null;
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
