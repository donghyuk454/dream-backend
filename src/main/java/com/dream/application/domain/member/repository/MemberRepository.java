package com.dream.application.domain.member.repository;

import com.dream.application.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m " +
            "LEFT JOIN FETCH m.memberSubscriptions ms " +
            "LEFT JOIN FETCH ms.subscription s " +
            "LEFT JOIN FETCH s.player " +
            "WHERE m.memberId = :memberId")
    Optional<Member> findWithMemberSubscriptionsById(@Param("memberId") Long memberId);
}