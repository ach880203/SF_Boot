package com.example.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass  //테이블은 안만들고, 컬럼만 물려주는 부모 클래스
@Getter
public class BaseEntity extends BaseTimeEntity {

    @CreatedBy  //작성자(생성자)
    @Column(updatable = false)
    private String createBy;

    @LastModifiedBy //수정자
    private String modifiedBy;
}
