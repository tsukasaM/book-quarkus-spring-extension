/*
 * (c)Copyright Since 2020, SOFTBANK Corp. All rights reserved.
 *
 */

package org.acme.spring.di.integration.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "recipes")
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RecipeEntity {

  @Id
  @GeneratedValue
  public Integer id;

  @Column(name = "title", length = 100)
  private String title;

  @Column(name = "making_time", length = 100)
  private String makingTime;

  @Column(name = "serves", length = 100)
  private String serves;

  @Column(name = "ingredients", length = 300)
  private String ingredients;

  @Column(name = "cost")
  private Integer cost;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;
}
