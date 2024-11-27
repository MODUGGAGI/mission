package umc.mission.service.foodcategoryservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.mission.repository.FoodCategoryRepository;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService{

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public boolean existsById(Long id) {
        return foodCategoryRepository.existsById(id);
    }
}
