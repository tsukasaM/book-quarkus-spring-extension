package org.acme.spring.di.integration.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface RecipeRepository extends CrudRepository<RecipeEntity, Integer> {

  /**
   * ID を指定してレシピのエンティティを取得します.
   *
   * @param id レシピの ID
   * @return 取得したレシピのエンティティ
   */
  Optional<RecipeEntity> findById(Integer id);

//  /**
//   * レシピを登録します.
//   *
//   * @param recipeEntity 新規登録するレシピ
//   * @return 新規登録されたレシピのエンティティ
//   */
//  RecipeEntity create(RecipeEntity recipeEntity);

//  /**
//   * 複数のレシピのエンティティを取得します.
//   *
//   * @return 取得したレシピのエンティティのリスト
//   */
//  List<RecipeEntity> findAll();
//
//  /**
//   * レシピを更新します.
//   *
//   * @param recipeEntity 更新するレシピ
//   * @return 更新されたレシピのエンティティ
//   */
//  RecipeEntity update(RecipeEntity recipeEntity);
//
//  /**
//   * 指定した ID のレシピを削除します.
//   *
//   * @param id 削除するレシピの ID
//   * @return 削除されたレシピのエンティティ
//   */
//  RecipeEntity delete(Integer id);
}
