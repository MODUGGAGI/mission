package umc.mission.service.storeservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.mission.repository.storerepository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;
    @Override
    public boolean existsById(Long id) {
        return storeRepository.existsById(id);
    }
}
