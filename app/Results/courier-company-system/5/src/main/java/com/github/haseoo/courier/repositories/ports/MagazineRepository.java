package com.github.haseoo.courier.repositories.ports;
 import com.github.haseoo.courier.models.MagazineModel;
import java.util.List;
import java.util.Optional;
public interface MagazineRepository {


public MagazineModel saveAndFlush(MagazineModel magazineModel)
;

public List<MagazineModel> getActiveMagazines()
;

public Optional<MagazineModel> getById(Long id)
;

public List<MagazineModel> getList()
;

}