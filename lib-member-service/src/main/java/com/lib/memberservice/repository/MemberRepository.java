package com.lib.memberservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lib.memberservice.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
