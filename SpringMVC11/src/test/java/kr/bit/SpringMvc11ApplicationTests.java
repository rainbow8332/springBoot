package kr.bit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.bit.entity.Member;
import kr.bit.entity.Role;
import kr.bit.repository.MemberRepository;

@SpringBootTest
class SpringMvc11ApplicationTests {

	@Autowired
	private MemberRepository memberRepository;
	
	// password 인코딩 객체 
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	void createMemeber() {
		Member m = new Member();
		m.setUsername("bitcocom");
		m.setPassword(encoder.encode("bitcocom")); // password 암호화
		m.setName("사용자1");
		m.setRole(Role.ADMIN);
		m.setEnabled(true);
		memberRepository.save(m); // 회원가입 
		
	}

}
