package com.umar.grpc;


import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;



@GrpcService
public class ExternalIntegrationGrpcService extends ExternalIntegrationServiceGrpc.ExternalIntegrationServiceImplBase {

    @Override
    public void getCachedTle(GetCachedTleRequest request, StreamObserver<GetCachedTleResponse> responseObserver) {
        super.getCachedTle(request, responseObserver);
    }

    @Override
    public void streamProviderHealth(StreamProviderHealthRequest request, StreamObserver<ProviderHealthUpdate> responseObserver) {
        super.streamProviderHealth(request, responseObserver);
    }
}
