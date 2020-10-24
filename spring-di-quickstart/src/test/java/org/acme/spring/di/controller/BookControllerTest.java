package org.acme.spring.di.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.spring.di.application.payload.CreateBookRequest;
import org.acme.spring.di.domain.Book.Book;
import org.acme.spring.di.domain.BookService;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.acme.spring.di.common.Utils.marshalToJson;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusTest
class BookControllerTest {

  @InjectMock
  BookService bookService;

  @Test
  void test_指定したidで本を取得できる() {

    when(bookService.getBook(1)).thenReturn(Book.builder()
                                                .id(1)
                                                .title("java 入門")
                                                .author("hoge")
                                                .cost(5000)
                                                .build());

      given()
        .when()
          .pathParam("id", "1")
          .get("/book/{id}")
        .then()
           .statusCode(200)
           .body("id", equalTo(1))
           .body("title", equalTo("java 入門"))
           .body("cost", equalTo(5000));

      verify(bookService).getBook(1);
  }

  @Test
  void test_POSTでリクエストして本が登録できる事() {

    when(bookService.createBook(Book.builder()
                                    .title("swift 入門")
                                    .author("huga")
                                    .cost(3000)
                                    .build()))
        .thenReturn(Book.builder()
                        .id(1)
                        .title("swift 入門")
                        .author("huga")
                        .cost(3000)
                        .build());


    given()
        .when()
        .contentType("application/json")
        .body(marshalToJson(CreateBookRequest.builder()
                                             .title("swift 入門")
                                             .author("huga")
                                             .cost(3000)
                                             .build()))
        .post("/book")
        .then()
        .statusCode(200)
        .body("message", equalTo("Book successfully created!"))
        .body("title", equalTo("swift 入門"))
        .body("cost", equalTo(3000));
  }

}