/*
 * (c)Copyright Since 2020, SOFTBANK Corp. All rights reserved.
 *
 */

package org.acme.spring.di.domain;

import org.acme.spring.di.domain.Book.Book;

/**
 * レシピに関するサービスのインターフェース。
 */
public interface BookService {

  /**
   * 指定した id のレシピを取得します。
   *
   * @param recipeId 取得したいレシピ id
   * @return 取得したレシピ
   */
  Book getBook(Integer recipeId);


  /**
   * 指定したレシピを登録するためのエンドポイントです
   *
   * @param book 登録したいレシピのドメインモデル
   * @return 登録したレシピ
   */
  Book createBook(Book book);
}
