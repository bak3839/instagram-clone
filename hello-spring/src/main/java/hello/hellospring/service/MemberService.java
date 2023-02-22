package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public Optional<Member> login(String Id, String pw) {
        Optional<Member> result;

        int C = 0;

        if(Id.contains("@")) {
            C = 1;
        }
        else if(validPhoneNum(Id)){
            C = 2;
        }
        else {
            C = 3;
        }

        result = memberRepository.findByLogin(pw, Id, C);

        if(result.isEmpty()) {
            throw new IllegalStateException("잘못된 회원정보입니다.");
        }

        System.out.println("로그인 성공입니다.");
        return result;
    }

    public boolean validPhoneNum(String num) {
        Pattern pattern = Pattern.compile("\\d{11}");
        Matcher matcher = pattern.matcher(num);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findByid(memberId);
    }
}
