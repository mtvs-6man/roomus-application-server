package com.sixman.roomus.rooms.query.infra;


import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.command.domain.model.MemberNullError;
import com.sixman.roomus.member.command.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomMemberService {
    private final MemberRepository memberRepository;

    public String getMemberId(int memberNo) {
        Optional<Member> foundMemberOpt = memberRepository.findById(memberNo);
        if (foundMemberOpt.isEmpty()) {
            throw new MemberNullError("사용자를 찾을 수 없습니다.");
        }
        Member foundMember = foundMemberOpt.get();
        return foundMember.getMemberId();

    }

}
