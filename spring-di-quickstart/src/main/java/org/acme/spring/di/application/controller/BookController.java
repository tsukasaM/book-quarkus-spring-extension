package org.acme.spring.di.application.controller;

import org.acme.spring.di.application.payload.CreateBookRequest;
import org.acme.spring.di.application.payload.CreateBookResponse;
import org.acme.spring.di.application.payload.BookResponse;
import org.acme.spring.di.domain.Book.Book;
import org.acme.spring.di.domain.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("book")
public class BookController {

  @Autowired
  private BookService bookService;

  /**
   * 指定した id の本を取得するためのエンドポイントです。
   *
   * @param bookId 取得したい本の id
   * @return 取得した本
   */
  @GetMapping(path = "/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public BookResponse getBook(@PathVariable("id") String bookId) {
    Book book = bookService.getBook(Integer.parseInt(bookId));
    return BookResponse.of(book);
  }

  /**
   * 指定した本を登録するためのエンドポイントです
   *
   * @param request 登録したい本のリクエスト
   * @return 登録した本
   */
  @PostMapping
  @Produces(MediaType.APPLICATION_JSON)
  public CreateBookResponse createBook(CreateBookRequest request) {
    Book book = bookService.createBook(request.toModel());
    return CreateBookResponse.of(book);
  }
}