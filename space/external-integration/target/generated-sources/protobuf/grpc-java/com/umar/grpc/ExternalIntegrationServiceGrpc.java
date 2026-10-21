package com.umar.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class ExternalIntegrationServiceGrpc {

  private ExternalIntegrationServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "grpc.ExternalIntegrationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.umar.grpc.GetCachedTleRequest,
      com.umar.grpc.GetCachedTleResponse> getGetCachedTleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCachedTle",
      requestType = com.umar.grpc.GetCachedTleRequest.class,
      responseType = com.umar.grpc.GetCachedTleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.umar.grpc.GetCachedTleRequest,
      com.umar.grpc.GetCachedTleResponse> getGetCachedTleMethod() {
    io.grpc.MethodDescriptor<com.umar.grpc.GetCachedTleRequest, com.umar.grpc.GetCachedTleResponse> getGetCachedTleMethod;
    if ((getGetCachedTleMethod = ExternalIntegrationServiceGrpc.getGetCachedTleMethod) == null) {
      synchronized (ExternalIntegrationServiceGrpc.class) {
        if ((getGetCachedTleMethod = ExternalIntegrationServiceGrpc.getGetCachedTleMethod) == null) {
          ExternalIntegrationServiceGrpc.getGetCachedTleMethod = getGetCachedTleMethod =
              io.grpc.MethodDescriptor.<com.umar.grpc.GetCachedTleRequest, com.umar.grpc.GetCachedTleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCachedTle"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.umar.grpc.GetCachedTleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.umar.grpc.GetCachedTleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ExternalIntegrationServiceMethodDescriptorSupplier("GetCachedTle"))
              .build();
        }
      }
    }
    return getGetCachedTleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.umar.grpc.StreamProviderHealthRequest,
      com.umar.grpc.ProviderHealthUpdate> getStreamProviderHealthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamProviderHealth",
      requestType = com.umar.grpc.StreamProviderHealthRequest.class,
      responseType = com.umar.grpc.ProviderHealthUpdate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.umar.grpc.StreamProviderHealthRequest,
      com.umar.grpc.ProviderHealthUpdate> getStreamProviderHealthMethod() {
    io.grpc.MethodDescriptor<com.umar.grpc.StreamProviderHealthRequest, com.umar.grpc.ProviderHealthUpdate> getStreamProviderHealthMethod;
    if ((getStreamProviderHealthMethod = ExternalIntegrationServiceGrpc.getStreamProviderHealthMethod) == null) {
      synchronized (ExternalIntegrationServiceGrpc.class) {
        if ((getStreamProviderHealthMethod = ExternalIntegrationServiceGrpc.getStreamProviderHealthMethod) == null) {
          ExternalIntegrationServiceGrpc.getStreamProviderHealthMethod = getStreamProviderHealthMethod =
              io.grpc.MethodDescriptor.<com.umar.grpc.StreamProviderHealthRequest, com.umar.grpc.ProviderHealthUpdate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamProviderHealth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.umar.grpc.StreamProviderHealthRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.umar.grpc.ProviderHealthUpdate.getDefaultInstance()))
              .setSchemaDescriptor(new ExternalIntegrationServiceMethodDescriptorSupplier("StreamProviderHealth"))
              .build();
        }
      }
    }
    return getStreamProviderHealthMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ExternalIntegrationServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExternalIntegrationServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExternalIntegrationServiceStub>() {
        @java.lang.Override
        public ExternalIntegrationServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExternalIntegrationServiceStub(channel, callOptions);
        }
      };
    return ExternalIntegrationServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static ExternalIntegrationServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExternalIntegrationServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExternalIntegrationServiceBlockingV2Stub>() {
        @java.lang.Override
        public ExternalIntegrationServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExternalIntegrationServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return ExternalIntegrationServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ExternalIntegrationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExternalIntegrationServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExternalIntegrationServiceBlockingStub>() {
        @java.lang.Override
        public ExternalIntegrationServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExternalIntegrationServiceBlockingStub(channel, callOptions);
        }
      };
    return ExternalIntegrationServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ExternalIntegrationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExternalIntegrationServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExternalIntegrationServiceFutureStub>() {
        @java.lang.Override
        public ExternalIntegrationServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExternalIntegrationServiceFutureStub(channel, callOptions);
        }
      };
    return ExternalIntegrationServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getCachedTle(com.umar.grpc.GetCachedTleRequest request,
        io.grpc.stub.StreamObserver<com.umar.grpc.GetCachedTleResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCachedTleMethod(), responseObserver);
    }

    /**
     */
    default void streamProviderHealth(com.umar.grpc.StreamProviderHealthRequest request,
        io.grpc.stub.StreamObserver<com.umar.grpc.ProviderHealthUpdate> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamProviderHealthMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ExternalIntegrationService.
   */
  public static abstract class ExternalIntegrationServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ExternalIntegrationServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ExternalIntegrationService.
   */
  public static final class ExternalIntegrationServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ExternalIntegrationServiceStub> {
    private ExternalIntegrationServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExternalIntegrationServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExternalIntegrationServiceStub(channel, callOptions);
    }

    /**
     */
    public void getCachedTle(com.umar.grpc.GetCachedTleRequest request,
        io.grpc.stub.StreamObserver<com.umar.grpc.GetCachedTleResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCachedTleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void streamProviderHealth(com.umar.grpc.StreamProviderHealthRequest request,
        io.grpc.stub.StreamObserver<com.umar.grpc.ProviderHealthUpdate> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamProviderHealthMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ExternalIntegrationService.
   */
  public static final class ExternalIntegrationServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<ExternalIntegrationServiceBlockingV2Stub> {
    private ExternalIntegrationServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExternalIntegrationServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExternalIntegrationServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public com.umar.grpc.GetCachedTleResponse getCachedTle(com.umar.grpc.GetCachedTleRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetCachedTleMethod(), getCallOptions(), request);
    }

    /**
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<?, com.umar.grpc.ProviderHealthUpdate>
        streamProviderHealth(com.umar.grpc.StreamProviderHealthRequest request) {
      return io.grpc.stub.ClientCalls.blockingV2ServerStreamingCall(
          getChannel(), getStreamProviderHealthMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service ExternalIntegrationService.
   */
  public static final class ExternalIntegrationServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ExternalIntegrationServiceBlockingStub> {
    private ExternalIntegrationServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExternalIntegrationServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExternalIntegrationServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.umar.grpc.GetCachedTleResponse getCachedTle(com.umar.grpc.GetCachedTleRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCachedTleMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.umar.grpc.ProviderHealthUpdate> streamProviderHealth(
        com.umar.grpc.StreamProviderHealthRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamProviderHealthMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ExternalIntegrationService.
   */
  public static final class ExternalIntegrationServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ExternalIntegrationServiceFutureStub> {
    private ExternalIntegrationServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExternalIntegrationServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExternalIntegrationServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.umar.grpc.GetCachedTleResponse> getCachedTle(
        com.umar.grpc.GetCachedTleRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCachedTleMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CACHED_TLE = 0;
  private static final int METHODID_STREAM_PROVIDER_HEALTH = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CACHED_TLE:
          serviceImpl.getCachedTle((com.umar.grpc.GetCachedTleRequest) request,
              (io.grpc.stub.StreamObserver<com.umar.grpc.GetCachedTleResponse>) responseObserver);
          break;
        case METHODID_STREAM_PROVIDER_HEALTH:
          serviceImpl.streamProviderHealth((com.umar.grpc.StreamProviderHealthRequest) request,
              (io.grpc.stub.StreamObserver<com.umar.grpc.ProviderHealthUpdate>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetCachedTleMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.umar.grpc.GetCachedTleRequest,
              com.umar.grpc.GetCachedTleResponse>(
                service, METHODID_GET_CACHED_TLE)))
        .addMethod(
          getStreamProviderHealthMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.umar.grpc.StreamProviderHealthRequest,
              com.umar.grpc.ProviderHealthUpdate>(
                service, METHODID_STREAM_PROVIDER_HEALTH)))
        .build();
  }

  private static abstract class ExternalIntegrationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ExternalIntegrationServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.umar.grpc.ExternalIntegrationProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ExternalIntegrationService");
    }
  }

  private static final class ExternalIntegrationServiceFileDescriptorSupplier
      extends ExternalIntegrationServiceBaseDescriptorSupplier {
    ExternalIntegrationServiceFileDescriptorSupplier() {}
  }

  private static final class ExternalIntegrationServiceMethodDescriptorSupplier
      extends ExternalIntegrationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ExternalIntegrationServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ExternalIntegrationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ExternalIntegrationServiceFileDescriptorSupplier())
              .addMethod(getGetCachedTleMethod())
              .addMethod(getStreamProviderHealthMethod())
              .build();
        }
      }
    }
    return result;
  }
}
