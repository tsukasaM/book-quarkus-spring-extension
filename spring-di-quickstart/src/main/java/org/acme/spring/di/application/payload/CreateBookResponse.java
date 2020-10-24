/*
 * (c)Copyright Since 2020, SOFTBANK Corp. All rights reserved.
 *
 */

package org.acme.spring.di.application.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.spring.di.domain.Book.Book;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookResponse {

  private String message;

  private String title;

  private String author;

  private Integer cost;


  public static CreateBookResponse of(Book book) {
    return CreateBookResponse.builder()
                             .message("Book successfully created!")
                             .title(book.getTitle())
                             .author(book.getAuthor())
                             .cost(book.getCost())
                             .build();
  }
}
