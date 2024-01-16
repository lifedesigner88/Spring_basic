package com.encore.basic.service;
import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberReqDto;
import com.encore.basic.domain.MemberResDto;
import com.encore.basic.repository.MemberRepository;
import com.encore.basic.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    static int total_id;
    private final MemberRepository repository;

    @Autowired
    public MemberService(MemoryMemberRepository repository) {
        this.repository = repository;
    }

    public void memberCreate(MemberReqDto reqDto) {
        repository.memberCreate(
                new Member(
                        total_id++,
                        reqDto.getName(),
                        reqDto.getEmail(),
                        reqDto.getPassword(),
                        LocalDateTime.now()));
    }

    public List<MemberResDto> members() {
        List<MemberResDto> resDtos = new ArrayList<>();
        List<Member> members = repository.members();
        for (Member member : members)
            resDtos.add(resDto(member));
        return resDtos;
    }

    public MemberResDto member(int id) {
        return resDto(repository.findById(id));
    }

    private MemberResDto resDto(Member member){
        return new MemberResDto(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPassword(),
                member.getCreate_time()
        );
    }

}
