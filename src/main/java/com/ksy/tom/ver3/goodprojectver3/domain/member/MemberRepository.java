package com.ksy.tom.ver3.goodprojectver3.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.account = :account and m.password = :password ")
    Member login(@Param("account") String account, @Param("password") String password);
}
