/*
 * (c)Copyright Since 2019, SOFTBANK Corp. All rights reserved.
 *
 */

package org.acme.spring.di.common;

import lombok.extern.slf4j.Slf4j;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * 共通のユーティリティクラス。
 */
@Slf4j
public final class Utils {

  private static Jsonb formattedJsonb = null;
  private static Jsonb noFormattedJsonb = null;

  private Utils() {
    // Do Nothing.
  }

  private static Jsonb createFormattedJson() {
    JsonbConfig config = new JsonbConfig();
    config.withFormatting(true);
    return JsonbBuilder.create(config);
  }

  private static Jsonb createNoFormattedJson() {
    return JsonbBuilder.create(new JsonbConfig());
  }

  private static Jsonb getJsonb(boolean formatted) {
    Jsonb result = null;

    if (formatted) {
      if (isNull(formattedJsonb)) {
        formattedJsonb = createFormattedJson();
      }
      result = formattedJsonb;
    } else {
      if (isNull(noFormattedJsonb)) {
        noFormattedJsonb = createNoFormattedJson();
      }
      result = noFormattedJsonb;
    }

    return result;
  }

  /**
   * 指定されたオブジェクトを JSON 文字列にマーシャリングします。
   *
   * @param obj マーシャリングするオブジェクト
   * @return マーシャリングされた JSON の文字列
   */
  public static String marshalToJson(Object obj) {
    if (nonNull(obj)) {
      return getJsonb(true).toJson(obj);
    }

    return "{}";
  }

}
