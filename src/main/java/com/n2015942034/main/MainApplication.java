package com.n2015942034.main;

import com.n2015942034.main.domain.Basic;
import com.n2015942034.main.domain.Profile;
import com.n2015942034.main.repository.BasicRepository;
import com.n2015942034.main.repository.ProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(ProfileRepository profileRepository, BasicRepository basicRepository){
        return (args) -> IntStream.rangeClosed(1,20).forEach(index -> {
            basicRepository.save(Basic.builder()
                    .name("홍길동" + index)
                    .label("도적" + index)
                    .email("hong@gmail.com" + index)
                    .phone("012-3456-789" + index)
                    .build());
            profileRepository.save(Profile.builder()
                    .network("트위터" + index)
                    .userName("@hong" + index)
                    .url("https://twitter/@home"+index)
                    .createDat(LocalDateTime.of(2019,05,21,19,33,21))
                    .updateDat(LocalDateTime.now())
                    .build());

        });
    }
}
