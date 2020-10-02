/*
 * (c)Copyright Since 2020, SOFTBANK Corp. All rights reserved.
 *
 */

package org.acme.spring.di.application.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.spring.di.domain.Recipe.Recipe;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeResponse {

  private Integer id;

  private String title;

  private String makingTime;

  private String serves;

  private String ingredients;

  private Integer cost;

  public static RecipeResponse of(Recipe recipe) {
    return RecipeResponse.builder()
                         .id(recipe.getId())
                         .title(recipe.getTitle())
                         .makingTime(recipe.getMakingTime())
                         .serves(recipe.getServes())
                         .ingredients(recipe.getIngredients())
                         .cost(recipe.getCost())
                         .build();
  }
}
