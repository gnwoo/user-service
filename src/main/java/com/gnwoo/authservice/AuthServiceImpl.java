package com.gnwoo.authservice;

import com.gnwoo.authservice.data.repo.PasscodeDAO;
import com.gnwoo.authservice.data.repo.PasscodeDAOImpl;
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

import java.security.SecureRandom;
import java.util.List;

@Component
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {
//    @Bean
//    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
    @Autowired
    private AuthRepo authRepo;
    @Autowired
    private PasscodeDAOImpl passcodeDAO;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    private JWTHandler jwtHandler = new JWTHandler();

    @Override
    public void signUp(AuthProto.PasswordRequest request, StreamObserver<AuthProto.GeneralResponse> responseObserver) {
        // get uuid and password from request
        Long uuid = request.getUuid();
        String password = request.getPassword();

        // hash the password
        String hashed_password = "123";

        // save the (uuid, hashed_password) to the db
//        authRepo.save(new Auth(uuid, hashed_password));

        // response true to user service
        String feedback = "uuid: " + uuid + "'s password hashed and saved";
        responseObserver.onNext(constructGeneralResponse(true, feedback));
        responseObserver.onCompleted();
    }

    @Override
    public void login(AuthProto.PasswordRequest request,
                      StreamObserver<AuthProto.CredentialResponse> responseObserver) {
        // get uuid and password from request
        Long uuid = request.getUuid();
        String password = request.getPassword();

        // get user's hashed_password from db
        List<String> relations = authRepo.findHashedPassword(uuid);

        // invalid login: uuid does not exist
        if(relations.isEmpty())
        {
            String feedback = "uuid: " + uuid + " does not exit";
            responseObserver.onNext(constructCredentialResponse(false, feedback, ""));
            responseObserver.onCompleted();
        }

        // otherwise, uuid found
        String hashed_password = relations.get(0);

        // valid login: uuid and password combination matched
        if(true)
        {
            // construct a JWT token
          String JWT_token = jwtHandler.consturctJWT(1L);
            System.out.println(jwtHandler.verifyJWT(JWT_token));

            // response true with JWT_token to user_service
            String feedback = "uuid: " + uuid + " login success";
            responseObserver.onNext(constructCredentialResponse(true, feedback, JWT_token));
            responseObserver.onCompleted();
        }
        // otherwise, invalid login
        String feedback = "uuid: " + uuid + " invalid uuid or password";
        responseObserver.onNext(constructCredentialResponse(false, feedback, ""));
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
    public void verifyByEmailAddress(AuthProto.EmailVerificationRequest request, StreamObserver<AuthProto.GeneralResponse> responseObserver) {
        // generate a 4-digit passcode
        SecureRandom secureRandom = new SecureRandom();
        String passcode = String.format("%06d", secureRandom.nextInt(1000000));

        // save (uuid, passcode) to Redis
        Long uuid = request.getUuid();
        passcodeDAO.savePasscode(uuid, passcode);

        // TODO: rpc call to email service
        String email = request.getEmail();

        // response true to user service
        String feedback = "uuid: " + uuid + "'s passcode saved and sent";
        responseObserver.onNext(constructGeneralResponse(true, feedback));
        responseObserver.onCompleted();
    }

    @Override
    public void changePassword(AuthProto.PasscodeRequest request, StreamObserver<AuthProto.GeneralResponse> responseObserver) {
        // compare passcode
        Long uuid = request.getUuid();
        String passcode = passcodeDAO.findByUuid(uuid);
        // valid passcode
        if(passcode != null && passcode.equals(request.getPasscode()))
        {
            // update hashed password in db
            String new_hashed_password = "123";
//            authRepo.updateHashedPassword(uuid, new_hashed_password);

            // response true to user service
            String feedback = "uuid: " + uuid + "'s passcode invalid";
            responseObserver.onNext(constructGeneralResponse(false, feedback));
            responseObserver.onCompleted();
        }
        // invalid passcode
        else
        {
            // response false to user service
            String feedback = "uuid: " + uuid + "'s passcode invalid";
            responseObserver.onNext(constructGeneralResponse(false, feedback));
            responseObserver.onCompleted();
        }
    }

    @Override
    public void checkAuth(AuthProto.AuthRequest request, StreamObserver<AuthProto.GeneralResponse> responseObserver) {
        super.checkAuth(request, responseObserver);
    }

    private AuthProto.GeneralResponse constructGeneralResponse(boolean decision, String feedback)
    {
        return AuthProto.GeneralResponse.newBuilder()
                                        .setDecision(decision).setFeedback(feedback)
                                        .build();
    }

    private AuthProto.CredentialResponse constructCredentialResponse(boolean decision, String feedback,
                                                                     String JWT_token)
    {
        return AuthProto.CredentialResponse.newBuilder()
                                           .setDecision(decision).setFeedback(feedback).setJWT(JWT_token)
                                           .build();
    }
}
