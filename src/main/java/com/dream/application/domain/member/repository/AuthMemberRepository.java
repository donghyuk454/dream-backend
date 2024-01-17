package com.dream.application.domain.member.repository;

import com.dream.application.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthMemberRepository extends JpaRepository<Member, Long> {
}
