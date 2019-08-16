package member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import member.dao.MemberDAO;
@Service
public class MemberServiceImpl implements MemberService{
	// MemberDAO에서 @Repository어노테이션을 사용해서 bean객체 설정해놨기 때문에
	@Autowired
	private MemberDAO dao;
	@Override
	public int write(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return dao.write(memberDTO);
	}

	@Override
	public String login(String id, String pwd) {
		// TODO Auto-generated method stub
		return dao.login(id, pwd);
	}

	@Override
	public boolean isExistId(String id) {
		// TODO Auto-generated method stub
		return dao.isExistId(id);
	}

	@Override
	public MemberDTO selectOne(String id) {
		// TODO Auto-generated method stub
		return dao.selectOne(id);
	}

	@Override
	public int modify(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return dao.modify(memberDTO);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

	@Override
	public List<MemberDTO> selectList(int startNum, int endNum) {
		// TODO Auto-generated method stub
		return dao.selectList(startNum, endNum);
	}

	@Override
	public int getTotalMember() {
		// TODO Auto-generated method stub
		return dao.getTotalMember();
	}

}
