package com.sixman.roomus.member.command.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sixman.roomus.commons.vo.DateSet;
import com.sixman.roomus.config.jwt.JwtConfig;
import com.sixman.roomus.member.Dto.JoinDto;
import com.sixman.roomus.member.Dto.TokenDTO;
import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.command.domain.model.MemberInfo;
import com.sixman.roomus.member.command.domain.model.MyProduct;
import com.sixman.roomus.member.command.domain.model.vo.Role;
import com.sixman.roomus.member.command.repository.MemberRepository;
import com.sixman.roomus.member.command.repository.MyproductRepository;
import com.sixman.roomus.member.command.repository.RelationRepository;
import com.sixman.roomus.product.command.domain.model.Product;
import com.sixman.roomus.product.command.domain.repository.ProductRepository;
import com.sixman.roomus.member.command.domain.model.Relation;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Service
public class MemberService {

    private MemberRepository memberRepository;
    private MyproductRepository myproductRepository;
    private RelationRepository relationRepository;
    private ProductRepository productRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private JwtConfig jwtConfig;

    public MemberService(MemberRepository memberRepository,MyproductRepository myproductRepository ,
                         ProductRepository productRepository, BCryptPasswordEncoder passwordEncoder,
                         RelationRepository relationRepository,JwtConfig jwtConfig){
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
        this.myproductRepository = myproductRepository;
        this.productRepository = productRepository;
        this.relationRepository = relationRepository;
    }

    public String memberSinup(JoinDto joinDto){
        LocalDate now = LocalDate.now();

        Member findId = memberRepository.findByMemberId(joinDto.getMemberId());

        if(findId != null){
            return "??????";
        }

        Member member = new Member();

        member.setMemberId(joinDto.getMemberId());
        member.setPassword(passwordEncoder.encode(joinDto.getPwd()));
        member.setMemberInfo(new MemberInfo(joinDto.getMemberName(),joinDto.getMemberEmail()));
        member.setDate(new DateSet(now.toString()));
        member.setRole(Role.USER);
        member.setState("Y");

        Member value = memberRepository.save(member);

        if(value.equals(null)){
            return "??????";
        }


        return "???????????? ??????";
    }

    public boolean passConfirm(String token, String confirmPass) throws JsonProcessingException {


        TokenDTO tokenDTO = jwtConfig.decryption(token);

        Member member = memberRepository.findByMemberId(tokenDTO.getMemberId());

        if(!passwordEncoder.matches(confirmPass, member.getPassword())){
            return false;
        }

        return true;
    }

    public Member chagePass(String token, String changePass) throws JsonProcessingException {

        TokenDTO tokenDTO = jwtConfig.decryption(token);

        Member member = memberRepository.findByMemberId(tokenDTO.getMemberId());
        member.setPassword(passwordEncoder.encode(changePass));

        Member chanageMember = memberRepository.save(member);
        if(member.getPassword().equals(chanageMember.getPassword())){
            return chanageMember;
        }else{
            return null;
        }

    }

    public String memberRanke(String token) throws JsonProcessingException {

        TokenDTO tokenDTO = jwtConfig.decryption(token);
        Member member = memberRepository.findByMemberId(tokenDTO.getMemberId());

        if((member.getRoleList().contains(Role.SELLER.toString())) || (member.getRoleList().contains(Role.ADMIN.toString()))){
            return "????????? ????????? ?????? ?????? ?????????.";
        }

        member.setRole(Role.SELLER);

        Member changeMember = memberRepository.save(member);


        if(changeMember.getRole().equals(Role.SELLER.toString())){
            return "????????? ????????? ?????? ???????????????.";
        }

        return "????????? ????????? ?????????????????????.";
    }

    public String followUser(String token, Integer userNo) throws JsonProcessingException {

        TokenDTO tokenDTO = jwtConfig.decryption(token);
        if(Integer.parseInt(tokenDTO.getMemberNo()) == userNo) {
            return "????????? ????????? ??? ??? ????????????.";
        }

        Member relationMember = memberRepository.findByMemberId(tokenDTO.getMemberId());
        Member followMember = memberRepository.findByMemberNo(userNo);

        if(Objects.isNull(relationMember) || Objects.isNull(followMember)){
            return "???????????? ?????? ?????? ?????? ????????? ?????? ?????????.";
        }

        Relation relation = relationRepository.findByRelationUserAndFollowUser(relationMember, followMember);

        if(!Objects.isNull(relation)){
            return "?????? ???????????? ?????? ????????????.";
        }

        Relation newRelation = new Relation();
        newRelation.setRelationUser(relationMember);
        newRelation.setFollowUser(followMember);

        relationRepository.save(newRelation);

        return "????????? ???????????????.";
    }

    public String insertProduct(String token, int productNo) throws JsonProcessingException {

        TokenDTO tokenDTO = jwtConfig.decryption(token);
        Member member = memberRepository.findByMemberId(tokenDTO.getMemberId());
        Product product = productRepository.findByProductNo(productNo);
        //?????? ??? ?????? ????????? ??????
        if(Objects.isNull(member) || Objects.isNull(product))  return "?????? ????????? ???????????? ????????????.";

        //????????? ????????? ?????? ??????
        MyProduct myProduct = myproductRepository.findByMemberNoAndProductNo(member, product);
        if(!Objects.isNull(myProduct)) return "?????? ????????? ???????????????.";

        //?????? ????????? ?????? ??????
        MyProduct registMyproduct = new MyProduct();
        registMyproduct.setMemberNo(member);
        registMyproduct.setProductNo(product);
        registMyproduct.setRegistDate(new Date());

        System.out.println("my Product : " + registMyproduct);

        MyProduct result = myproductRepository.save(registMyproduct);

        if(Objects.isNull(result)) {
            return "????????? ????????? ?????????????????????.";
        }else {
            return "???????????? ?????? ???????????????.";
        }
    }

    public String unfollow(String token, Integer userNo) throws JsonProcessingException {

        TokenDTO tokenDTO = jwtConfig.decryption(token);

        Member member = memberRepository.findByMemberId(tokenDTO.getMemberId());
        Member unFollowMember = memberRepository.findByMemberNo(userNo);

        if(Objects.isNull(member)||Objects.isNull(unFollowMember)) return "????????? ????????? ????????????.";

        Relation relation = relationRepository.findByRelationUserAndFollowUser(member,unFollowMember);
        if(Objects.isNull(relation)) return "????????? ?????? ?????? ?????????.";

        relationRepository.delete(relation);

        return "???????????? ?????? ???????????????.";
    }

    public String delMyProduct(String token, int productNo) throws JsonProcessingException {

        TokenDTO tokenDTO = jwtConfig.decryption(token);

        Member member = memberRepository.findByMemberId(tokenDTO.getMemberId());
        Product product = productRepository.findByProductNo(productNo);

        if(Objects.isNull(member) || Objects.isNull(product)) return "?????? ?????? ???????????? ????????????.";

        MyProduct myProduct = myproductRepository.findByMemberNoAndProductNo(member, product);

        if(Objects.isNull(myProduct)) return "??? ????????? ???????????? ????????????.";

        myproductRepository.delete(myProduct);

        return "??? ???????????? ?????? ???????????????.";

    }

    
    public String seccsionUser(String token) throws JsonProcessingException {

        TokenDTO tokenDTO = jwtConfig.decryption(token);

        Member member = memberRepository.findByMemberNo(Integer.parseInt(tokenDTO.getMemberNo()));

        if(Objects.isNull(member)) return "?????? ????????? ???????????? ????????????. ";
        LocalDate now = LocalDate.now();

        member.setState("N");
        member.setDeleteDate(new DateSet(now.toString()));
        Member updateMember = memberRepository.save(member);

        if(!updateMember.getState().equals("N")) return "?????? ????????? ?????? ????????????. ";

        return "?????? ???????????????.";
    }
}
