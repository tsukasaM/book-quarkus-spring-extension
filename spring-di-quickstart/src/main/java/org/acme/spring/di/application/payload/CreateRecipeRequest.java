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
public class CreateRecipeRequest {

  private String title;

  private String makingTime;

  private String serves;

  private String ingredients;

  private Integer cost;

  public Recipe toModel() {
    return Recipe.builder()
                 .title(title)
                 .makingTime(makingTime)
                 .serves(serves)
                 .ingredients(ingredients)
                 .cost(cost)
                 .build();
  }
}
