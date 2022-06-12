package skku.swcoaching.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import skku.swcoaching.domain.webtoon.Day;
import skku.swcoaching.domain.webtoon.Webtoon;
import skku.swcoaching.domain.webtoon.WebtoonSearch;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WebtoonRepository {

    private final EntityManager em;

    public void save(Webtoon webtoon){
        em.persist(webtoon);
    }

    public Webtoon findOne(Long id){
        return em.find(Webtoon.class, id);
    }

    public List<Webtoon> findByConditions(WebtoonSearch webtoonSearch){
        // 검색어와 태그 등의 조건을 사용한 웹툰 탐색

        // 먼저 해당 SubString 을 포함하는 Webtoon 가져오기 (없으면 ""일테니까 괜찮다)
        List<Webtoon> webtoons = em.createQuery("select w from Webtoon w where w.title like :subString", Webtoon.class)
                .setParameter("subString", webtoonSearch.getSubString())
                .getResultList();

        // 이후 지정된 Tag 들을 포함하는지 여부를
        List<Webtoon> ResultList = new ArrayList<>();
        for (Webtoon webtoon : webtoons){
            if(webtoon.getTags().contains(webtoonSearch.getTags())){
                ResultList.add(webtoon);
            }
        }

        return ResultList;
    }

    public List<Webtoon> findDay(Day day, int start){
        // 요일 별로 가져오기
        return em.createQuery("select w from Webtoon w where w.updateDate = :day", Webtoon.class)
                .setParameter("day", day)
                .setFirstResult(start)
                .setMaxResults(30)
                .getResultList();
    }

    public List<Webtoon> findAllAt(int start){
        // 그냥 update 순으로 가져오기 30개씩
        // 추후 User 만들시, 사용자별로 가져오는 개수 수정할 수 있도록 변경 (15, 30, 50?)
        return em.createQuery("select w from Webtoon w", Webtoon.class)
                .setFirstResult(start)
                .setMaxResults(30)
                .getResultList();
    }
}
