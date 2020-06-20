package com.gnwoo.userservice.authRPC;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.30.0)",
    comments = "Source: auth.proto")
public final class AuthServiceGrpc {

  private AuthServiceGrpc() {}

  public static final String SERVICE_NAME = "AuthService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest,
      com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> getSignUpMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SignUp",
      requestType = com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest.class,
      responseType = com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest,
      com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> getSignUpMethod() {
    io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest, com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> getSignUpMethod;
    if ((getSignUpMethod = AuthServiceGrpc.getSignUpMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getSignUpMethod = AuthServiceGrpc.getSignUpMethod) == null) {
          AuthServiceGrpc.getSignUpMethod = getSignUpMethod =
              io.grpc.MethodDescriptor.<com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest, com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SignUp"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("SignUp"))
              .build();
        }
      }
    }
    return getSignUpMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest,
      com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Login",
      requestType = com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest.class,
      responseType = com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest,
      com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest, com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse> getLoginMethod;
    if ((getLoginMethod = AuthServiceGrpc.getLoginMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getLoginMethod = AuthServiceGrpc.getLoginMethod) == null) {
          AuthServiceGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest, com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("Login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.JWTRequest,
      com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> getLogoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Logout",
      requestType = com.gnwoo.userservice.authRPC.AuthProto.JWTRequest.class,
      responseType = com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.JWTRequest,
      com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> getLogoutMethod() {
    io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.JWTRequest, com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> getLogoutMethod;
    if ((getLogoutMethod = AuthServiceGrpc.getLogoutMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getLogoutMethod = AuthServiceGrpc.getLogoutMethod) == null) {
          AuthServiceGrpc.getLogoutMethod = getLogoutMethod =
              io.grpc.MethodDescriptor.<com.gnwoo.userservice.authRPC.AuthProto.JWTRequest, com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gnwoo.userservice.authRPC.AuthProto.JWTRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("Logout"))
              .build();
        }
      }
    }
    return getLogoutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.JWTRequest,
      com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> getLogoutEverywhereMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LogoutEverywhere",
      requestType = com.gnwoo.userservice.authRPC.AuthProto.JWTRequest.class,
      responseType = com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.JWTRequest,
      com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> getLogoutEverywhereMethod() {
    io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.JWTRequest, com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> getLogoutEverywhereMethod;
    if ((getLogoutEverywhereMethod = AuthServiceGrpc.getLogoutEverywhereMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getLogoutEverywhereMethod = AuthServiceGrpc.getLogoutEverywhereMethod) == null) {
          AuthServiceGrpc.getLogoutEverywhereMethod = getLogoutEverywhereMethod =
              io.grpc.MethodDescriptor.<com.gnwoo.userservice.authRPC.AuthProto.JWTRequest, com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LogoutEverywhere"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gnwoo.userservice.authRPC.AuthProto.JWTRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("LogoutEverywhere"))
              .build();
        }
      }
    }
    return getLogoutEverywhereMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.PasscodeRequest,
      com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse> getChangePasswordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChangePassword",
      requestType = com.gnwoo.userservice.authRPC.AuthProto.PasscodeRequest.class,
      responseType = com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.PasscodeRequest,
      com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse> getChangePasswordMethod() {
    io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.PasscodeRequest, com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse> getChangePasswordMethod;
    if ((getChangePasswordMethod = AuthServiceGrpc.getChangePasswordMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getChangePasswordMethod = AuthServiceGrpc.getChangePasswordMethod) == null) {
          AuthServiceGrpc.getChangePasswordMethod = getChangePasswordMethod =
              io.grpc.MethodDescriptor.<com.gnwoo.userservice.authRPC.AuthProto.PasscodeRequest, com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChangePassword"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gnwoo.userservice.authRPC.AuthProto.PasscodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("ChangePassword"))
              .build();
        }
      }
    }
    return getChangePasswordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.AuthRequest,
      com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> getCheckAuthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckAuth",
      requestType = com.gnwoo.userservice.authRPC.AuthProto.AuthRequest.class,
      responseType = com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.AuthRequest,
      com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> getCheckAuthMethod() {
    io.grpc.MethodDescriptor<com.gnwoo.userservice.authRPC.AuthProto.AuthRequest, com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> getCheckAuthMethod;
    if ((getCheckAuthMethod = AuthServiceGrpc.getCheckAuthMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getCheckAuthMethod = AuthServiceGrpc.getCheckAuthMethod) == null) {
          AuthServiceGrpc.getCheckAuthMethod = getCheckAuthMethod =
              io.grpc.MethodDescriptor.<com.gnwoo.userservice.authRPC.AuthProto.AuthRequest, com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckAuth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gnwoo.userservice.authRPC.AuthProto.AuthRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("CheckAuth"))
              .build();
        }
      }
    }
    return getCheckAuthMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceStub>() {
        @java.lang.Override
        public AuthServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceStub(channel, callOptions);
        }
      };
    return AuthServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingStub>() {
        @java.lang.Override
        public AuthServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceBlockingStub(channel, callOptions);
        }
      };
    return AuthServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceFutureStub>() {
        @java.lang.Override
        public AuthServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceFutureStub(channel, callOptions);
        }
      };
    return AuthServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AuthServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void signUp(com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest request,
        io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSignUpMethod(), responseObserver);
    }

    /**
     */
    public void login(com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest request,
        io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void logout(com.gnwoo.userservice.authRPC.AuthProto.JWTRequest request,
        io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }

    /**
     */
    public void logoutEverywhere(com.gnwoo.userservice.authRPC.AuthProto.JWTRequest request,
        io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLogoutEverywhereMethod(), responseObserver);
    }

    /**
     */
    public void changePassword(com.gnwoo.userservice.authRPC.AuthProto.PasscodeRequest request,
        io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChangePasswordMethod(), responseObserver);
    }

    /**
     */
    public void checkAuth(com.gnwoo.userservice.authRPC.AuthProto.AuthRequest request,
        io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckAuthMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSignUpMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest,
                com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse>(
                  this, METHODID_SIGN_UP)))
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest,
                com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getLogoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gnwoo.userservice.authRPC.AuthProto.JWTRequest,
                com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse>(
                  this, METHODID_LOGOUT)))
          .addMethod(
            getLogoutEverywhereMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gnwoo.userservice.authRPC.AuthProto.JWTRequest,
                com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse>(
                  this, METHODID_LOGOUT_EVERYWHERE)))
          .addMethod(
            getChangePasswordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gnwoo.userservice.authRPC.AuthProto.PasscodeRequest,
                com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse>(
                  this, METHODID_CHANGE_PASSWORD)))
          .addMethod(
            getCheckAuthMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gnwoo.userservice.authRPC.AuthProto.AuthRequest,
                com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse>(
                  this, METHODID_CHECK_AUTH)))
          .build();
    }
  }

  /**
   */
  public static final class AuthServiceStub extends io.grpc.stub.AbstractAsyncStub<AuthServiceStub> {
    private AuthServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceStub(channel, callOptions);
    }

    /**
     */
    public void signUp(com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest request,
        io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSignUpMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void login(com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest request,
        io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logout(com.gnwoo.userservice.authRPC.AuthProto.JWTRequest request,
        io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logoutEverywhere(com.gnwoo.userservice.authRPC.AuthProto.JWTRequest request,
        io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutEverywhereMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void changePassword(com.gnwoo.userservice.authRPC.AuthProto.PasscodeRequest request,
        io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChangePasswordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkAuth(com.gnwoo.userservice.authRPC.AuthProto.AuthRequest request,
        io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCheckAuthMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AuthServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<AuthServiceBlockingStub> {
    private AuthServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse signUp(com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest request) {
      return blockingUnaryCall(
          getChannel(), getSignUpMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse login(com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse logout(com.gnwoo.userservice.authRPC.AuthProto.JWTRequest request) {
      return blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse logoutEverywhere(com.gnwoo.userservice.authRPC.AuthProto.JWTRequest request) {
      return blockingUnaryCall(
          getChannel(), getLogoutEverywhereMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse changePassword(com.gnwoo.userservice.authRPC.AuthProto.PasscodeRequest request) {
      return blockingUnaryCall(
          getChannel(), getChangePasswordMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse checkAuth(com.gnwoo.userservice.authRPC.AuthProto.AuthRequest request) {
      return blockingUnaryCall(
          getChannel(), getCheckAuthMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AuthServiceFutureStub extends io.grpc.stub.AbstractFutureStub<AuthServiceFutureStub> {
    private AuthServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> signUp(
        com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSignUpMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse> login(
        com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> logout(
        com.gnwoo.userservice.authRPC.AuthProto.JWTRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> logoutEverywhere(
        com.gnwoo.userservice.authRPC.AuthProto.JWTRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLogoutEverywhereMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse> changePassword(
        com.gnwoo.userservice.authRPC.AuthProto.PasscodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChangePasswordMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse> checkAuth(
        com.gnwoo.userservice.authRPC.AuthProto.AuthRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCheckAuthMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SIGN_UP = 0;
  private static final int METHODID_LOGIN = 1;
  private static final int METHODID_LOGOUT = 2;
  private static final int METHODID_LOGOUT_EVERYWHERE = 3;
  private static final int METHODID_CHANGE_PASSWORD = 4;
  private static final int METHODID_CHECK_AUTH = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AuthServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AuthServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SIGN_UP:
          serviceImpl.signUp((com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest) request,
              (io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse>) responseObserver);
          break;
        case METHODID_LOGIN:
          serviceImpl.login((com.gnwoo.userservice.authRPC.AuthProto.PasswordRequest) request,
              (io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse>) responseObserver);
          break;
        case METHODID_LOGOUT:
          serviceImpl.logout((com.gnwoo.userservice.authRPC.AuthProto.JWTRequest) request,
              (io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse>) responseObserver);
          break;
        case METHODID_LOGOUT_EVERYWHERE:
          serviceImpl.logoutEverywhere((com.gnwoo.userservice.authRPC.AuthProto.JWTRequest) request,
              (io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse>) responseObserver);
          break;
        case METHODID_CHANGE_PASSWORD:
          serviceImpl.changePassword((com.gnwoo.userservice.authRPC.AuthProto.PasscodeRequest) request,
              (io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.CredentialResponse>) responseObserver);
          break;
        case METHODID_CHECK_AUTH:
          serviceImpl.checkAuth((com.gnwoo.userservice.authRPC.AuthProto.AuthRequest) request,
              (io.grpc.stub.StreamObserver<com.gnwoo.userservice.authRPC.AuthProto.GeneralResponse>) responseObserver);
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

  private static abstract class AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuthServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.gnwoo.userservice.authRPC.AuthProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AuthService");
    }
  }

  private static final class AuthServiceFileDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier {
    AuthServiceFileDescriptorSupplier() {}
  }

  private static final class AuthServiceMethodDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AuthServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AuthServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthServiceFileDescriptorSupplier())
              .addMethod(getSignUpMethod())
              .addMethod(getLoginMethod())
              .addMethod(getLogoutMethod())
              .addMethod(getLogoutEverywhereMethod())
              .addMethod(getChangePasswordMethod())
              .addMethod(getCheckAuthMethod())
              .build();
        }
      }
    }
    return result;
  }
}
