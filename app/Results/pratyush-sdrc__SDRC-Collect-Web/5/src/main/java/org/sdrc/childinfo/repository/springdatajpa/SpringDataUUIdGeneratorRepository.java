package org.sdrc.childinfo.repository.springdatajpa;
 import org.sdrc.childinfo.domain.UUIdGenerator;
import org.sdrc.childinfo.repository.UUIdGeneratorRepository;
import org.springframework.data.repository.Repository;
public interface SpringDataUUIdGeneratorRepository extends UUIdGeneratorRepository, Repository<UUIdGenerator, Integer>{


}