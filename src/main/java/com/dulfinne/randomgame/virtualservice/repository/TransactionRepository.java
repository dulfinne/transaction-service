package com.dulfinne.randomgame.virtualservice.repository;

import com.dulfinne.randomgame.virtualservice.dto.request.FilterRequest;
import com.dulfinne.randomgame.virtualservice.entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dulfinne.randomgame.virtualservice.util.PropertyMapBuilder.fromRecord;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {

  private final EntityManager em;

  public Transaction findTransactionById(Long id) {
    return em.find(Transaction.class, id);
  }

  public void saveTransaction(Transaction transaction) {
    em.persist(transaction);
  }

  public List<Transaction> findAllTransactionsKeyset(Long lastSeenId, Integer limit) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
    Root<Transaction> transaction = query.from(Transaction.class);

    Predicate lastSeenPredicate = cb.lessThan(transaction.get(Transaction.FIELD_ID), lastSeenId);
    query.select(transaction)
         .where(lastSeenPredicate)
         .orderBy(cb.desc(transaction.get(Transaction.FIELD_ID)));

    return em.createQuery(query)
             .setMaxResults(limit)
             .getResultList();
  }

  public List<Transaction> findAllTransactionsOffset(Integer offset, Integer limit) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
    Root<Transaction> transaction = query.from(Transaction.class);

    query.select(transaction)
         .orderBy(cb.desc(transaction.get(Transaction.FIELD_ID)));

    return em.createQuery(query)
             .setFirstResult(offset * limit)
             .setMaxResults(limit)
             .getResultList();
  }

  public List<Transaction> findTransactionsWithFilters(
      FilterRequest filters,
      Long lastSeenId,
      Integer limit
  ) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
    Root<Transaction> root = query.from(Transaction.class);

    Predicate[] filterPredicates = fromRecord(filters).entrySet()
                                                      .stream()
                                                      .map(entry -> cb.equal(root.get(entry.getKey()),
                                                                             entry.getValue()))
                                                      .toArray(Predicate[]::new);
    Predicate lastSeenPredicate = cb.lessThan(root.get(Transaction.FIELD_ID), lastSeenId);

    query.select(root)
         .where(cb.and(filterPredicates))
         .where(lastSeenPredicate)
         .orderBy(cb.desc(root.get(Transaction.FIELD_ID)));

    return em.createQuery(query)
             .setMaxResults(limit)
             .getResultList();
  }
}
