package skku.swcoaching.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skku.swcoaching.domain.webtoon.Day;
import skku.swcoaching.domain.webtoon.Webtoon;
import skku.swcoaching.domain.webtoon.WebtoonSearch;
import skku.swcoaching.dto.WebtoonDto;
import skku.swcoaching.repository.WebtoonRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;

    /**
     * 웹툰 정보 업데이트 (크롤링 서비스에서 사용)
     * @param webtoonId 업데이트할 웹툰 id
     * @param webtoonDto 업데이트 될 웹툰 데이터
     */
    @Transactional
    public void update(Long webtoonId, WebtoonDto webtoonDto){
        Webtoon findWebtoon = webtoonRepository.findOne(webtoonId);

        // 아래 부분을 Dto 를 사용해서 더 깔끔하게 만들 수 없나?
        // findWebtoon.setAuto(webtoonDto.toEntity());
        // mapstruct 와 같은 툴이 있긴 하다! (dozer, modelMapper 와 같은 패키지가 있긴했음)
        // 자동으로 이름 같은거 넣어주는 툴, 이렇게 하면 유지보수에서 문제가 생긴다.
        // 객체가 바꼈을 때 암묵적으로 mapping 해주면 오류를 찾기 힘들 수 있음.
        findWebtoon.setTitle(webtoonDto.getTitle());
        findWebtoon.setWriter(webtoonDto.getWriter());
        findWebtoon.setThumbnail(webtoonDto.getThumbnail());
        findWebtoon.setPlatform(webtoonDto.getPlatform());
        findWebtoon.setUrl(webtoonDto.getUrl());
        
        webtoonRepository.save(findWebtoon);
        // 코드가 길어지더라도 가독성을 위해서 이렇게 save 를 명시적으로 해주는게 낫다.
        // 코드는 항상 명시적으로!
        // (변경 값은 자동으로 변경 감지에 의해서 데이터에 반영됨)
    }
    
    /**
     * 요일별 웹툰 조회
     */
    public List<Webtoon> searchWebtoonForToday(Day day, int page){
        return webtoonRepository.findDay(day, page);
    }

    /**
     * 검색어 + Tag 조회
     */
    public List<Webtoon> searchWebtoonForConditions(WebtoonSearch webtoonSearch){
        return webtoonRepository.findByConditions(webtoonSearch);
    }

}
