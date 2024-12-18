package project.toy.service.search;

import project.toy.web.dto.search.SearchResponseDto;

public interface SearchQueryService {
    SearchResponseDto.HospitalSearchResultDTO getSearchResult(String name, Integer page);
}
