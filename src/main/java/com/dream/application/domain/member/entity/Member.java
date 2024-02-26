package com.dream.application.domain.member.entity;

import com.dream.application.common.entity.BaseEntity;
import com.dream.application.domain.subscribe.entity.MemberSubscription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "EMAIL", unique = true, updatable = false)
    private String email;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberSubscription> memberSubscriptions;

    public Member(String email, String memberName) {
        this(email, memberName, new ArrayList<>());
    }

    protected Member(String email, String memberName, List<MemberSubscription> memberSubscriptions) {
        this.email = email;
        this.memberName = memberName;
        this.memberSubscriptions = memberSubscriptions;
    }

    public void subscribe(MemberSubscription memberSubscription) {
        memberSubscriptions.add(memberSubscription);
    }
}
