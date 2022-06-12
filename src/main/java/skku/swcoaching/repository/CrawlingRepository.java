package skku.swcoaching.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import skku.swcoaching.domain.crawling.CrawlingTarget;
import skku.swcoaching.domain.webtoon.Day;
import skku.swcoaching.domain.webtoon.Webtoon;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CrawlingRepository {
    private final EntityManager em;

    public void save(CrawlingTarget crawlingTarget){
        em.persist(crawlingTarget);
    }

    public CrawlingTarget findOne(Long id) {
        return em.find(CrawlingTarget.class, id);
    }

    public List<CrawlingTarget> findByWebtoon(Webtoon webtoon){
        return em.createQuery("select c from CrawlingTarget c where c.webtoon = :webtoon", CrawlingTarget.class)
                .setParameter("webtoon", webtoon)
                .getResultList();
    }

}
