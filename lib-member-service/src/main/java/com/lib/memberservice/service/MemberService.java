package com.lib.memberservice.service;

import java.util.List;

import com.lib.memberservice.dto.MemberRequest;
import com.lib.memberservice.dto.MemberResponse;

public interface MemberService {

	List<MemberResponse> getAllmembers();

	String saveMember(MemberRequest memberRequest);

	MemberResponse getMemberById(Long id);

}
