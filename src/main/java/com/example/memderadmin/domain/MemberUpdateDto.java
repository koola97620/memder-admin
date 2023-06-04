package com.example.memderadmin.domain;

import com.example.memderadmin.app.MemberUpdateRequest;

import java.time.LocalDate;

public record MemberUpdateDto(
        String name,
        LocalDate birthDate,
        String gender,
        String password,
        String email
) {

    public static MemberUpdateDto of(MemberUpdateRequest request) {
        return new MemberUpdateDto(
                request.name(), request.birthDate(), request.gender(), request.password(), request.email()
        );
    }
}
