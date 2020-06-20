package com.gnwoo.authservice;

import com.gnwoo.userservice.authRPC.AuthProto;
import com.gnwoo.userservice.authRPC.AuthServiceGrpc;
import io.grpc.stub.StreamObserver;
import com.gnwoo.authservice.data.repo.AuthRepo;
import com.gnwoo.authservice.data.table.Auth;
import com.gnwoo.authservice.handlers.JWTHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
    @Autowired
    private AuthRepo authRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private JWTHandler jwtHandler = new JWTHandler();

    @Override
    public void signUp(AuthProto.PasswordRequest request, StreamObserver<AuthProto.GeneralResponse> responseObserver) {
        // get uuid and password from request
        Long uuid = request.getUuid();
        String password = request.getPassword();

        // hash the password
        String hashed_password = passwordEncoder.encode(password);

        // save the (uuid, hashed_password) to the db
        authRepo.save(new Auth(uuid, hashed_password));

        // response true to user service
        String feedback = "uuid: " + uuid + "'s password hashed and saved";
        responseObserver.onNext(AuthProto.GeneralResponse.newBuilder().setDecision(true).setFeedback(feedback).build());
        responseObserver.onCompleted();
    }

    @Override
    public void login(AuthProto.PasswordRequest request,
                      StreamObserver<AuthProto.CredientialResponse> responseObserver) {
        // get uuid and password from request
        Long uuid = request.getUuid();
        String password = request.getPassword();

        // get user's hashed_password from db
        List<String> relations = authRepo.findHashedPassword(uuid);

        // invalid login: uuid does not exist
        if(relations.isEmpty())
        {
            String feedback = "uuid: " + uuid + " does not exit";
            responseObserver.onNext(AuthProto.CredientialResponse.newBuilder()
                                                                 .setDecision(false).setFeedback(feedback).build());
            responseObserver.onCompleted();
        }

        // otherwise, uuid found
        String hashed_password = relations.get(0);

        // valid login: uuid and password combination matched
        if(passwordEncoder.matches(password, hashed_password))
        {
            // construct a JWT token
            String JWT_token = jwtHandler.consturctJWT(uuid);
            System.out.println(jwtHandler.verifyJWT(JWT_token));

            // response true with JWT_token to user_service
            String feedback = "uuid: " + uuid + " login success";
            responseObserver.onNext(AuthProto.CredientialResponse.newBuilder()
                                                                 .setDecision(true)
                                                                 .setFeedback(feedback).setJWT(JWT_token).build());
            responseObserver.onCompleted();
        }
        // otherwise, invalid login
        String feedback = "uuid: " + uuid + " invalid uuid or password";
        responseObserver.onNext(AuthProto.CredientialResponse.newBuilder().setDecision(false).setFeedback(feedback).build());
        responseObserver.onCompleted();
    }

    @Override
    public void logout(AuthProto.JWTRequest request, StreamObserver<AuthProto.GeneralResponse> responseObserver) {
        super.logout(request, responseObserver);
    }

    @Override
    public void logoutEverywhere(AuthProto.JWTRequest request, StreamObserver<AuthProto.GeneralResponse> responseObserver) {
        super.logoutEverywhere(request, responseObserver);
    }

    @Override
    public void changePassword(AuthProto.PasscodeRequest request, StreamObserver<AuthProto.CredientialResponse> responseObserver) {
        super.changePassword(request, responseObserver);
    }

    @Override
    public void checkAuth(AuthProto.AuthRequest request, StreamObserver<AuthProto.GeneralResponse> responseObserver) {
        super.checkAuth(request, responseObserver);
    }
}
