package org.acme.spring.di.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.spring.di.domain.Recipe.Recipe;
import org.acme.spring.di.domain.RecipeService;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusTest
class RecipeControllerTest {

  @InjectMock
  RecipeService recipeService;

  @Test
  void test_指定したidでレシピを取得できる() {

    when(recipeService.getRecipe(1)).thenReturn(Recipe.builder()
                                                      .id(1)
                                                      .title("チキンカレー")
                                                      .makingTime("45分")
                                                      .serves("4人")
                                                      .ingredients("玉ねぎ,肉,スパイス")
                                                      .cost(1000)
                                                      .build());

      given()
        .when()
          .pathParam("id", "1")
          .get("/recipe/{id}")
        .then()
           .statusCode(200)
           .body("id", equalTo(1))
           .body("title", equalTo("チキンカレー"))
           .body("makingTime", equalTo("45分"))
           .body("serves", equalTo("4人"))
           .body("ingredients", equalTo("玉ねぎ,肉,スパイス"))
           .body("cost", equalTo(1000));

      verify(recipeService).getRecipe(1);
  }

//  @Test
//  void test_POSTでリクエストしてレシピが登録できる事() {
//
//    when(recipeService.createRecipe(Recipe.builder()
//                                          .title("チキンカレー")
//                                          .makingTime("45分")
//                                          .serves("5人")
//                                          .ingredients("玉ねぎ,肉,スパイス")
//                                          .cost(450)
//                                          .build()))
//        .thenReturn(Recipe.builder()
//                          .id(1)
//                          .title("チキンカレー")
//                          .makingTime("45分")
//                          .serves("5人")
//                          .ingredients("玉ねぎ,肉,スパイス")
//                          .cost(450)
//                          .build());
//
//
//    given()
//        .when()
//        .contentType("application/json")
//        .body(marshalToJson(CreateRecipeRequest.builder()
//                                               .title("チキンカレー")
//                                               .makingTime("45分")
//                                               .serves("5人")
//                                               .ingredients("玉ねぎ,肉,スパイス")
//                                               .cost(450)
//                                               .build()))
//        .post("/recipe")
//        .then()
//        .statusCode(200)
//        .body("message", equalTo("Recipe successfully created!"))
//        .body("title", equalTo("チキンカレー"))
//        .body("making_time", equalTo("45分"))
//        .body("serves", equalTo("5人"))
//        .body("ingredients", equalTo("玉ねぎ,肉,スパイス"))
//        .body("cost", equalTo(450));
//  }

}