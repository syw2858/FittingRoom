package net.nwrn.pf_contest;

import lombok.RequiredArgsConstructor;
import net.nwrn.pf_contest.test.dto.HomeModelDTO;

import net.nwrn.pf_contest.users.repository.UserEntity;
import net.nwrn.pf_contest.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final UserRepository userRepository;

    public TestVO getRecent() {
        List<TestVO> list = testRepository.findAll();
        TestVO t = list.get(list.size()-1);
         return t;
    }

    public void add(String name, Integer age) {
        TestVO t = new TestVO();
        t.setName(name);
        t.setAge(age);
        testRepository.save(t);
    }

    public HomeModelDTO getUser(Long id) {
        if (id == null) {
            return new HomeModelDTO("인증정보가 없습니다", "인증정보가 없습니다", "인증정보가 없습니다");
        } else {
            Optional<UserEntity> optionalUserVO = userRepository.findById(id);
            if (optionalUserVO.isEmpty()) {
                return new HomeModelDTO("인증정보가 없습니다", "인증정보가 없습니다", "인증정보가 없습니다");
            } else {
                return new HomeModelDTO(optionalUserVO.get().getUserId(),
                                        optionalUserVO.get().getPassword(),
                                        String.valueOf(optionalUserVO.get().getId()));
            }
        }
    }
}
