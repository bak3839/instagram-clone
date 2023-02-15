package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        Optional<Member> result;
        if(!member.getEmail().isEmpty()) {
            result = memberRepository.findByEmail(member.getEmail());
        }
        else {
            result = memberRepository.findByPhoneNumber(member.getPhoneNumber());
        }

        Optional<Member> result1 = memberRepository.findByNickname(member.getNickname());

        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 이메일 또는 전화번호입니다.");
        });

        result1.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 사용자 이름 입니다.");
        });

        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findByid(memberId);
    }
}
