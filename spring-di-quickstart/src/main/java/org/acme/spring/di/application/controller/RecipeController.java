package org.acme.spring.di.application.controller;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.acme.spring.di.application.payload.RecipeResponse;
import org.acme.spring.di.domain.Recipe.Recipe;
import org.acme.spring.di.domain.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@NoArgsConstructor
@RestController
@RequestMapping("recipe")
public class RecipeController {

  @NonNull
  private RecipeService recipeService;

  /**
   * 指定した id のレシピを取得するためのエンドポイントです!
   *
   * @param recipeId 取得したいレシピの id
   * @return 取得したレシピ
   */
  @GetMapping(path = "/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public RecipeResponse getRecipe(@PathVariable("id") String recipeId) {
    Recipe recipe = recipeService.getRecipe(Integer.parseInt(recipeId));
    return RecipeResponse.of(recipe);
  }

//  /**
//   * 指定したレシピを登録するためのエンドポイントです
//   *
//   * @param request 登録したいレシピのリクエスト
//   * @return 登録したレシピ
//   */
//  @POST
//  @Produces(MediaType.APPLICATION_JSON)
//  public CreateRecipeResponse getRecipe(CreateRecipeRequest request) {
//    Recipe recipe = recipeService.createRecipe(request.toModel());
//    return CreateRecipeResponse.of(recipe);
//  }
}