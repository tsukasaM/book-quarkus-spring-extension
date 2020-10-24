package org.acme.spring.di.domain;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.spring.di.domain.Book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;


import static com.ninja_squad.dbsetup.Operations.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookServiceImpl に対するテスト")
@QuarkusTest
class BookServiceImplTest {

  @Inject
  BookServiceImpl target;

  public static final Operation DELETE_RECIPES = deleteAllFrom("books");
  public static final Operation INSERT_RECIPES
      = insertInto("books").columns("id",
                                          "title",
                                          "author",
                                          "cost",
                                          "created_at",
                                          "updated_at")
                             .values(1,
                                 "java 入門",
                                 "hoge",
                                 5000,
                                 "2016-01-10 12:10:12",
                                 "2016-01-10 12:10:12")
                             .values(2,
                                 "swift 入門",
                                 "huga",
                                 3000,
                                 "2016-01-11 13:10:12",
                                 "2016-01-11 13:10:12")
                             .build();


  @BeforeEach
  void setUp() {
    Operation operation = sequenceOf(DELETE_RECIPES, INSERT_RECIPES);
    DbSetup dbSetup = new DbSetup(new DriverManagerDestination("jdbc:h2:mem:test;MODE=MSSQLServer;DB_CLOSE_DELAY=-1", "sa", "sa"), operation);
    dbSetup.launch();
  }

  @Test
  void test_指定したidの本を正常に取得できる() {
    Book actual = target.getBook(1);
    Book expected = Book.builder()
                        .id(1)
                        .title("java 入門")
                        .author("hoge")
                        .cost(5000)
                        .build();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void test_指定した本を正常に登録できる事() {

    Book actual = target.createBook(Book.builder()
                                        .title("Vue.js 入門")
                                        .author("piyo")
                                        .cost(3000)
                                        .build());
    Book expected = Book.builder()
                        .id(3)
                        .title("Vue.js 入門")
                        .author("piyo")
                        .cost(3000)
                        .build();

    assertThat(actual).isEqualTo(expected);
  }


}