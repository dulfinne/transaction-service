package com.dulfinne.randomgame.transactionservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

import static com.dulfinne.randomgame.transactionservice.entity.Transaction.FIELD_ID;

@Entity
@Table(
    name = "transaction",
    indexes = {
        @Index(name = "idx_username", columnList = FIELD_ID)
    }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = FIELD_ID)
  Long id;

  @Column(name = FIELD_USERNAME)
  String username;

  @Column(name = FIELD_AMOUNT)
  BigDecimal amount;

  @Column(name = FIELD_DESCRIPTION)
  String description;

  @Column(name = FIELD_DATE)
  Instant date;

  @Enumerated(EnumType.STRING)
  @Column(name = FIELD_TYPE)
  TransactionType type;

  @PrePersist
  void onCreate() {
    date = Instant.now();
  }

  public static final String FIELD_ID = "id";
  public static final String FIELD_USERNAME = "username";
  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_DESCRIPTION = "description";
  public static final String FIELD_DATE = "date";
  public static final String FIELD_TYPE = "type";
}
