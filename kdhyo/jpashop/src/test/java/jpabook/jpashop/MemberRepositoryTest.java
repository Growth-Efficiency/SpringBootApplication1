package jpabook.jpashop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@DisplayName("멤버 저장")
	@Transactional
	@Rollback(value = false)
	void save() {
		// given
		Member member = new Member();
		member.setUserName("memberA");

		// when
		Long savedId = memberRepository.save(member);
		Member findMember = memberRepository.find(savedId);

		// then
		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
		// 영속성 컨텍스트에 있는 걸 가지고 오는 것이기 때문에 같게 나온다.
		assertThat(findMember).isEqualTo(member);
	}

}