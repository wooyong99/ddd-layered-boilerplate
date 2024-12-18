package com.example.dddlayerdboilerplate.domain.member.service;

import com.example.dddlayerdboilerplate.domain.member.model.aggregate.Member;
import com.example.dddlayerdboilerplate.domain.member.repository.IMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberDuplicationChecker {

    private final IMemberRepository memberRepository;

    @Autowired
    public MemberDuplicationChecker(IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean isExistsByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    public boolean isExistsById(Long id){
        return memberRepository.existsById(id);
    }
}
