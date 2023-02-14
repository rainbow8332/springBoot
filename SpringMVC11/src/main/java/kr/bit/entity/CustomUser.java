package kr.bit.entity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

// 스프링 시큐리티에서 인식하는(사용자 인증정보를 저장할 수 있는) 클래스가되려면 User를 상속 받아야함 
@Data
public class CustomUser extends User{
	private Member member;

	public CustomUser(Member member) {
		super(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList("ROLE_"+member.getRole().toString()));
		this.member = member;
		
	}

}
