package pl.szymanski.sharelibrary.repositories.adapters;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.szymanski.sharelibrary.entity.Language;
import pl.szymanski.sharelibrary.repositories.jpa.LanguageJPARepository;
import pl.szymanski.sharelibrary.repositories.ports.LanguageRepository;
import pl.szymanski.sharelibrary.utils.generator.LanguageGenerator;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.times;


@SpringBootTest
class LanguageRepositoryImplTest {

    @Autowired
    private LanguageRepositoryImpl languageRepository;

    @Autowired
    private LanguageJPARepository languageJPARepository;

    @BeforeEach
    void setUp() {
        languageJPARepository.deleteAll();
    }

    @AfterEach
    void cleanUp() {
        languageJPARepository.deleteAll();
    }

    @Test
    void shouldReturnLanguage() {
        //given
        Language language = languageJPARepository.save(LanguageGenerator.getLanguage());
        //when
        Optional<Language> result = languageRepository.getLanguageById(language.getId());
        //then
        Assertions.assertThat(result.get().getId()).isEqualTo(language.getId());

    }

    @Test
    void shouldReturnLanguages() {
        //given
        Language language = languageJPARepository.save(LanguageGenerator.getLanguage());
        //when
        Set<Language> result = languageRepository.getAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void shouldCallMethodFindAllFromJpaRepository() {
        //given
        LanguageJPARepository languageJPA = Mockito.mock(LanguageJPARepository.class);
        LanguageRepository repository = new LanguageRepositoryImpl(languageJPA);
        //when
        Set<Language> result = repository.getAll();
        //then
        Mockito.verify(languageJPA, times(1)).findAll();
    }

    @Test
    void shouldCallMethodFindByIdFromJpaRepository() {
        //given
        LanguageJPARepository languageJPA = Mockito.mock(LanguageJPARepository.class);
        LanguageRepository repository = new LanguageRepositoryImpl(languageJPA);
        //when
        Optional<Language> result = repository.getLanguageById(1);
        //then
        Mockito.verify(languageJPA, times(1)).findById(1);
    }
}