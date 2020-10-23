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
  void test_指定したidでレシピを取得できる() {

    when(bookService.getBook(1)).thenReturn(Book.builder()
                                                .id(1)
                                                .title("チキンカレー")
                                                .author("45分")
                                                .cost(1000)
                                                .build());

      given()
        .when()
          .pathParam("id", "1")
          .get("/book/{id}")
        .then()
           .statusCode(200)
           .body("id", equalTo(1))
           .body("title", equalTo("チキンカレー"))
           .body("cost", equalTo(1000));

      verify(bookService).getBook(1);
  }

  @Test
  void test_POSTでリクエストしてレシピが登録できる事() {

    when(bookService.createBook(Book.builder()
                                    .title("チキンカレー")
                                    .author("45分")
                                    .cost(450)
                                    .build()))
        .thenReturn(Book.builder()
                        .id(1)
                        .title("チキンカレー")
                        .author("45分")
                        .cost(450)
                        .build());


    given()
        .when()
        .contentType("application/json")
        .body(marshalToJson(CreateBookRequest.builder()
                                             .title("チキンカレー")
                                             .author("45分")
                                             .cost(450)
                                             .build()))
        .post("/book")
        .then()
        .statusCode(200)
        .body("message", equalTo("Recipe successfully created!"))
        .body("title", equalTo("チキンカレー"))
        .body("cost", equalTo(450));
  }

}