package kr.bit.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Member {
	@Id
	private String username; // ID
	private String password;
	private String name; // user 이름 
	@Enumerated(EnumType.STRING) // String 열거형으로
	private Role role; // 권한정보 , 위 어노테이션에 의해 role은 String 값으로 설정됨, String 값이 여러개이기 때문에 열거형으로 함 
	private boolean enabled; // 계정의 활성화 여부 

}
