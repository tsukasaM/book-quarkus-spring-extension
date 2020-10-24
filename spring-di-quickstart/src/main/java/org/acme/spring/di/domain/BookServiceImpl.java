/*
 * (c)Copyright Since 2020, SOFTBANK Corp. All rights reserved.
 *
 */

package org.acme.spring.di.domain;

import org.acme.spring.di.domain.Book.Book;
import org.acme.spring.di.integration.entity.BookEntity;
import org.acme.spring.di.integration.entity.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


/**
 * {@link BookService} の実装クラス。
 */
@Service
@Configuration
public class BookServiceImpl implements BookService {

  @Autowired
  BookRepository bookRepository;

  private static final String LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT);


  /**
   * {@inheritDoc}
   */
  @Override
  public Book getBook(Integer bookId) {
    Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookId);

    if (optionalBookEntity.isPresent()) {
      BookEntity bookEntity = optionalBookEntity.get();
      return Book.builder()
                 .id(bookEntity.getId())
                 .title(bookEntity.getTitle())
                 .author(bookEntity.getAuthor())
                 .cost(bookEntity.getCost())
                 .build();
    } else {
      return null;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Book createBook(Book book) {
    BookEntity bookEntity =  BookEntity.builder()
                                       .cost(book.getCost())
                                       .title(book.getTitle())
                                       .author(book.getAuthor())
                                       .createdAt(Timestamp.valueOf(LocalDateTime.now().format(dateTimeFormatter)))
                                       .updatedAt(Timestamp.valueOf(LocalDateTime.now().format(dateTimeFormatter)))
                                       .build();

    BookEntity createdEntity = bookRepository.save(bookEntity);

    return Book.builder()
               .id(createdEntity.getId())
               .title(createdEntity.getTitle())
               .author(createdEntity.getAuthor())
               .cost(createdEntity.getCost())
               .build();
  }
}
