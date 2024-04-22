package net.nwrn.pf_contest;

import lombok.RequiredArgsConstructor;
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

    public UserVO getUser(Long id) {

        Optional<UserVO> optionalUserVO = userRepository.findById(id);
        if (optionalUserVO.isEmpty()) {
            throw new RuntimeException();
        }

        return optionalUserVO.get();
    }
}
