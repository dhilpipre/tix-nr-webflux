package com.tiket.entity;

import com.tiket.common.MongoFields;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@SuperBuilder
@Data
public class BaseMongo implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Field(value = MongoFields.ID)
  private String id;

  @CreatedDate
  private Date createdDate;

  private String createdBy;

  @LastModifiedDate
  private Date updatedDate;

  private String updatedBy;

  private int isDeleted;
}
