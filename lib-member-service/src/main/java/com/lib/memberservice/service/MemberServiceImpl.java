package com.lib.memberservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.lib.memberservice.dto.AddressResponse;
import com.lib.memberservice.dto.MemberRequest;
import com.lib.memberservice.dto.MemberResponse;
import com.lib.memberservice.model.Address;
import com.lib.memberservice.model.Member;
import com.lib.memberservice.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<MemberResponse> getAllmembers() {
		List<Member> members=memberRepository.findAll();
		List<MemberResponse> membersResponse=members.stream().map(member->mapToMemberResponse(member)).collect(Collectors.toList());
		return membersResponse;
	}
	
	private MemberResponse mapToMemberResponse(Member member) {
		MemberResponse memberResponse=modelMapper.map(member, MemberResponse.class);
		memberResponse.setAddress(modelMapper.map(member.getAddress(),AddressResponse.class));
		return memberResponse;
	}

	@Override
	public String saveMember(MemberRequest memberRequest) {
		Member member=new Member();
		Address address=new Address();
		member=modelMapper.map(memberRequest,Member.class);
		address=modelMapper.map(memberRequest.getAddress(),Address.class);
		member.setAddress(address);
		member=memberRepository.save(member);
		log.info("member saved with id:{}",member.getId());		
		return "Book with Book id:"+member.getId()+" saved successfully";
	}

	@Override
	public MemberResponse getMemberById(Long id) {
		MemberResponse memberResponse=new MemberResponse();
		Member member=memberRepository.findById(id).get();
		memberResponse=modelMapper.map(member,MemberResponse.class);
		memberResponse.setAddress(modelMapper.map(member.getAddress(),AddressResponse.class));
		return memberResponse;
		
	}


}
