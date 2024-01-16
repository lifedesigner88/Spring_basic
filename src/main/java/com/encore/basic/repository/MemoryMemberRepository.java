package com.encore.basic.repository;
import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private final List<Member> MemberDb = new ArrayList<>();

    @Override
    public List<Member> members() {return MemberDb;}

    @Override
    public void memberCreate(Member member) {MemberDb.add(member);}

    @Override
    public Member findById(int id) {
        for (Member a : MemberDb)
            if (a.getId() == id) return a;
        return null;
    }
}