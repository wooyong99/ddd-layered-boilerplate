package com.example.dddlayerdboilerplate.domain.member.model.aggregate;

import com.example.dddlayerdboilerplate.common.constants.Constants;
import com.example.dddlayerdboilerplate.domain.member.model.vo.Status;
import com.example.dddlayerdboilerplate.infra.persistence.MemberJpaRepository;
import com.example.dddlayerdboilerplate.infra.persistence.MemberRepository;
import com.example.dddlayerdboilerplate.infra.persistence.entity.MemberEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("회원 엔티티 테스트")
class MemberEntityTest {

    @Autowired
    MemberJpaRepository repository;

    @Test
    void 회원엔티티_필수값_이메일_NULL () {
        MemberEntity entity = MemberEntity.create(
                null,
                "테스트비밀번호",
                "테스트자기소개",
                "테스트주소",
                "RefreshToken",
                Status.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(entity);
        });
    }

    @Test
    void 회원엔티티_필수값_비밀번호_NULL () {
        MemberEntity entity = MemberEntity.create(
                "테스트이메일",
                null,
                "테스트자기소개",
                "테스트주소",
                "RefreshToken",
                Status.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(entity);
        });

        System.out.println("msg : " +dataIntegrityViolationException.getMessage());
    }

    @Test
    void 회원_생성_이메일_불일치() {
        MemberEntity entity = MemberEntity.create(
                "테스트이메일",
                "테스트비밀번호",
                "테스트자기소개",
                "테스트주소",
                "RefreshToken",
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(entity);
        });
    }
}