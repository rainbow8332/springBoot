package kr.bit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.bit.entity.Board;

@Repository // 생략가능 
public interface BoardRepository extends JpaRepository<Board, Long>{
	
	// public List<Board> findAll()
	// -> select * from 
	// public Board findById(Long idx);
	// -> select * from Board where idx=#{idx}
	// 쿼리 메서드 
//	public Board findByWrite(String writer);
	// -> select * from Board where writer = #{wirter}
	

}
