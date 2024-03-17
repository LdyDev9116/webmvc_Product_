package com.ssg.springex3.config;


// 필요한 클래스들을 임포트합니다.
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 이 클래스가 빈(Bean) 정의 메소드를 포함하고 있으며, 스프링의 설정 소스임을 나타냅니다.
@Configuration
public class ModelMapperConfig {

    // 해당 메소드의 실행 결과로 반환 된 객체를 스프링의 빈으로 등록시키는 역할
    @Bean
    public ModelMapper modelMapper() {
        // ModelMapper의 새 인스턴스를 생성합니다.
        ModelMapper modelMapper = new ModelMapper();

        // ModelMapper를 설정합니다:
        // 필드 매칭을 활성화합니다(동일한 이름의 필드는 자동으로 매칭됩니다).
        // 필드 접근 수준을 PRIVATE로 설정합니다(매칭을 위해 private 필드도 고려됩니다).
        // 매칭 전략을 LOOSE로 설정합니다(정확한 데이터 타입 일치를 요구하지 않는 덜 엄격한 매칭).
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // 스프링에 의해 관리될 구성된 ModelMapper 인스턴스를 반환합니다.
        return modelMapper;
    }
}