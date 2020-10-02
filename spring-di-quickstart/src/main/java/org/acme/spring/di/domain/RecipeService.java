/*
 * (c)Copyright Since 2020, SOFTBANK Corp. All rights reserved.
 *
 */

package org.acme.spring.di.domain;

import org.acme.spring.di.domain.Recipe.Recipe;

/**
 * レシピに関するサービスのインターフェース。
 */
public interface RecipeService {

  /**
   * 指定した id のレシピを取得します。
   *
   * @param recipeId 取得したいレシピ id
   * @return 取得したレシピ
   */
  Recipe getRecipe(Integer recipeId);


  /**
   * 指定したレシピを登録するためのエンドポイントです
   *
   * @param recipe 登録したいレシピのドメインモデル
   * @return 登録したレシピ
   */
  Recipe createRecipe(Recipe recipe);
}
