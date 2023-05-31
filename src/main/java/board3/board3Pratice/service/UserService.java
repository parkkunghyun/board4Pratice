package board3.board3Pratice.service;

import board3.board3Pratice.dto.users.AddUserRequest;
import board3.board3Pratice.entity.User;
import board3.board3Pratice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    // 로그아웃과 회원가입을 담당!

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public Long save(AddUserRequest request) {
        return  userRepository.save(
                User.builder()
                        .email(request.getEmail())
                        .password(bCryptPasswordEncoder.encode(request.getPassword()))
                        .build())
                .getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException());
    }
}
