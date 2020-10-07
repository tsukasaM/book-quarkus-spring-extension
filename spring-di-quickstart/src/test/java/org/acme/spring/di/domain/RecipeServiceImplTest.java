package org.acme.spring.di.domain;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.spring.di.domain.Recipe.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;


import static com.ninja_squad.dbsetup.Operations.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RecipeServiceImpl に対するテスト")
@QuarkusTest
class RecipeServiceImplTest {

  @Inject
  RecipeServiceImpl target;

  public static final Operation DELETE_RECIPES = deleteAllFrom("recipes");
  public static final Operation INSERT_RECIPES
      = insertInto("recipes").columns("id",
      "title",
      "making_time",
      "serves",
      "ingredients",
      "cost",
      "created_at",
      "updated_at")
                             .values(1,
                                 "チキンカレー",
                                 "45分",
                                 "4人",
                                 "玉ねぎ,肉,スパイス",
                                 1000,
                                 "2016-01-10 12:10:12",
                                 "2016-01-10 12:10:12")
                             .values(2,
                                 "オムライス",
                                 "30分",
                                 "2人",
                                 "玉ねぎ,卵,スパイス,醤油",
                                 700,
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
  void test_指定したidのレシピを正常に取得できる() {
    Recipe actual = target.getRecipe(1);
    Recipe expected = Recipe.builder()
                            .id(1)
                            .title("チキンカレー")
                            .makingTime("45分")
                            .serves("4人")
                            .ingredients("玉ねぎ,肉,スパイス")
                            .cost(1000)
                            .build();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void test_指定したレシピを正常に登録できる事() {

    Recipe actual = target.createRecipe(Recipe.builder()
                                              .title("チキンカレー")
                                              .makingTime("45分")
                                              .serves("5人")
                                              .ingredients("玉ねぎ,肉,スパイス")
                                              .cost(450)
                                              .build());
    Recipe expected = Recipe.builder()
                            .id(3)
                            .title("チキンカレー")
                            .makingTime("45分")
                            .serves("5人")
                            .ingredients("玉ねぎ,肉,スパイス")
                            .cost(450)
                            .build();

    assertThat(actual).isEqualTo(expected);
  }


}