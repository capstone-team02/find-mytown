package com.team2.findmytown.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    FEMALE("ROLE_FEMALE", "여성"),
    MALE("ROLE_MALE", "남성");

    private final String key;
    private final String title;
}