package com.dulfinne.randomgame.transactionservice.grpc;

import com.dulfinne.randomgame.transactionservice.entity.Transaction;
import com.dulfinne.randomgame.transactionservice.mapper.TransactionMapper;
import com.dulfinne.randomgame.transactionservice.service.TransactionService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
@RequiredArgsConstructor
public class GrpcTransactionService extends TransactionServiceGrpc.TransactionServiceImplBase {

  private final TransactionService transactionService;
  private final TransactionMapper transactionMapper;

  @Override
  public void saveTransaction(
      TransactionProto.Transaction request,
      StreamObserver<TransactionProto.SavedReply> responseObserver
  ) {
    Transaction transaction = transactionMapper.fromGrpc(request);
    log.info("Received transaction message from GRPC: {}", transaction);
    transactionService.saveTransaction(transaction);

    TransactionProto.SavedReply reply = TransactionProto.SavedReply.newBuilder()
                                                                   .setIsSaved(true)
                                                                   .build();

    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}
