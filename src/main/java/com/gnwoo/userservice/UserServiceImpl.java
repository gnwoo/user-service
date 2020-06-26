package com.gnwoo.userservice;

import com.gnwoo.userservice.data.repo.UserRepo;
import com.gnwoo.userservice.authRPC.AuthProto;
import com.gnwoo.userservice.authRPC.AuthServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

}
