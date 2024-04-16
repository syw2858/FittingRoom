package net.nwrn.pf_contest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

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
}
