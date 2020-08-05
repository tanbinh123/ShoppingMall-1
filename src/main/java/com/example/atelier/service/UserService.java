package com.example.atelier.service;

import com.example.atelier.domain.Member;
import com.example.atelier.mapper.MemberMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log
@Service

public class UserService implements UserDetailsService {
    private static final String Role_PREFIX = "ROLE_";
    @Autowired
    MemberMapper mm;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Member member= mm.findById(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role_PREFIX+member.getRole()));
        return new User(member.getUid(),member.getUpw(),authorities);

    }

}
