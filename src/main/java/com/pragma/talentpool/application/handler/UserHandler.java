package com.pragma.talentpool.application.handler;

import com.pragma.talentpool.application.mapper.RolRequestMapper;
import com.pragma.talentpool.application.mapper.RolResponseMapper;
import com.pragma.talentpool.application.mapper.UserRequestMapper;
import com.pragma.talentpool.application.mapper.UserResponseMapper;
import com.pragma.talentpool.domain.api.IRolServicePort;
import com.pragma.talentpool.domain.api.IUserServicePort;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler {
    private final IUserServicePort iUserServicePort;
    private final IRolServicePort iRolServicePort;
    private final RolRequestMapper rolRequestMapper;
    private final RolResponseMapper rolResponseMapper;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;
    
}
