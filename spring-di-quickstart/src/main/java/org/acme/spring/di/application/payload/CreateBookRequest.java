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
public class CreateBookRequest {

  private String title;

  private String author;

  private Integer cost;

  public Book toModel() {
    return Book.builder()
               .title(title)
               .author(author)
               .cost(cost)
               .build();
  }
}
