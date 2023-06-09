package com.example.memderadmin.app;

import com.example.memderadmin.domain.LoginMember;
import com.example.memderadmin.domain.Member;
import com.example.memderadmin.domain.MemberRepository;
import com.example.memderadmin.exception.AuthenticationMemberException;
import com.example.memderadmin.exception.ExceptionMessages;
import com.example.memderadmin.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.memderadmin.exception.ExceptionMessages.END_TOKEN_EXPIRATION;

@Service
public class AuthService {

    private final TokenHandler tokenHandler;
    private final MemberRepository memberRepository;

    public AuthService(TokenHandler tokenHandler, MemberRepository memberRepository) {
        this.tokenHandler = tokenHandler;
        this.memberRepository = memberRepository;
    }

    public LoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new MemberNotFoundException(ExceptionMessages.NOT_FOUND_MEMBER_LOGIN_ID.formatted(request.loginId())));
        member.checkPassword(request.password());

        String token = tokenHandler.create(request.loginId());

        return LoginResponse.of(token);
    }

    public LoginMember authentication(String token, LocalDateTime now) {
        if (!tokenHandler.validate(token, now)) {
            throw new AuthenticationMemberException(END_TOKEN_EXPIRATION);
        }
        String loginId = tokenHandler.getPayload(token);
//        Member member = memberRepository.findByLoginId(loginId)
//                .orElseThrow(() -> new MemberNotFoundException(ExceptionMessages.NOT_FOUND_MEMBER_LOGIN_ID.formatted(loginId)));
        return new LoginMember(loginId);
    }
}
