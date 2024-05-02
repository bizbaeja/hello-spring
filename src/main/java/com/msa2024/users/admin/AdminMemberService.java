package com.msa2024.users.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.msa2024.entity.MemberVO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/*
 * MVC
 * Model : B/L 로직을 구현하는 부분(service + dao)
 * View  : 출력(jsp)
 * Controller : model와 view에 대한 제어를 담당
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AdminMemberService  {

	private final AdminMemberMapper memberMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public MemberVO login(MemberVO memberVO) {
		MemberVO resultVO = memberMapper.findByUsername(memberVO.getUsername());
		if (resultVO != null) {
			if (!resultVO.isAccountNonLocked()) {
				throw new LockedException("Account is locked");
			}
			if (bCryptPasswordEncoder.matches(memberVO.getPassword(), resultVO.getPassword())) {
				resetLoginAttempts(resultVO);
				return resultVO;
			} else {
				incrementLoginAttempts(resultVO);
			}
		}
		return null;
	}

	private void incrementLoginAttempts(MemberVO user) {
		user.setMember_login_count(user.getMember_login_count() + 1);
		if (user.getMember_login_count() >= 3) {
			user.setMember_account_locked("Y");
			// Set a timestamp to unlock the account after 10 minutes
			user.setLockoutTime(LocalDateTime.now().plusMinutes(10));
		}
		memberMapper.updateLoginAttempts(user);
	}

	private void resetLoginAttempts(MemberVO user) {
		user.setMember_login_count(0);
		user.setMember_account_locked("N");
		memberMapper.updateLoginAttempts(user);
	}

	@Scheduled(fixedRate = 600000) // 10 minutes
	public void unlockAccounts() {
		List<MemberVO> lockedAccounts = memberMapper.findLockedAccounts();
		LocalDateTime now = LocalDateTime.now();
		for (MemberVO account : lockedAccounts) {
			if (account.getLockoutTime().isBefore(now)) {
				account.setMember_account_locked("N");
				account.setMember_login_count(0);
				memberMapper.updateLoginAttempts(account);
			}
		}
	}

	@Transactional(readOnly = true)
	public List<MemberVO> getAllMembers() {
		try {
			return memberMapper.findAllMembers();
		} catch (Exception e) {
			log.error("Unable to retrieve members", e);
			throw new RuntimeException("Failure during member retrieval", e);
		}
	}

	@Transactional
	public boolean lockUnlockMembers(List<String> memberIds) {
		boolean isAllSuccess = true;
		for (String memberId : memberIds) {
			try {
				MemberVO member = memberMapper.findById(memberId);
				if (member != null) {
					String newLockStatus = "Y".equals(member.getMember_account_locked()) ? "N" : "Y";
					member.setMember_account_locked(newLockStatus);
					memberMapper.updateMemberLockStatus(member);
				} else {
					log.warn("Member not found with ID: {}", memberId);
					isAllSuccess = false;
				}
			} catch (Exception e) {
				log.error("Unable to lock/unlock member with ID: {}", memberId, e);
				isAllSuccess = false;
			}
		}
		return isAllSuccess;
	}

	@Transactional
	public boolean deleteMembers(List<String> memberIds) {
		boolean isAllSuccess = true;
		for (String memberId : memberIds) {
			try {
				memberMapper.deleteMemberById(memberId);
			} catch (Exception e) {
				log.error("Unable to delete member with ID: {}", memberId, e);
				isAllSuccess = false;
			}
		}
		return isAllSuccess;
	}
}





