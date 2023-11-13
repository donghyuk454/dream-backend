package com.dream.application.domain.member.entity;
import com.dream.application.common.entity.BaseEntity;
import com.dream.application.domain.subscribe.entity.MemberSubscription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MEMBER")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberSubscription> memberSubscription;
}
