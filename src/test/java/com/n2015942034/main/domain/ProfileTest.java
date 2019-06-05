package com.n2015942034.main.domain;

import com.n2015942034.main.repository.ProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProfileTest {

    @Autowired
    private ProfileRepository profileRepository;

    private Profile saveProfile;

    @Before
    public void init(){
        saveProfile = profileRepository.save(Profile.builder()
                .network("트위터")
                .userName("@hong")
                .url("https:twitter/@home")
                .createDat(LocalDateTime.of(2019,05,21,19,33,21))
                .updateDat(LocalDateTime.of(2019,05,21,19,33,21))
                .build());
    }

    @Test
    public void TestFindIdProfile(){
        Profile foundProfile = profileRepository.findById(saveProfile.getIdx()).orElse(null);
        assertThat(foundProfile.getIdx()).isEqualTo(saveProfile.getIdx());
    }

}
