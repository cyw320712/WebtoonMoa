package skku.swcoaching.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import skku.swcoaching.domain.webtoon.Tag;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagRepository {

    private final EntityManager em;

    public void save(Tag tag){
        em.persist(tag);
    }

    public List<Tag> findAll(){
        return em.createQuery("select t from Tag t", Tag.class)
                .getResultList();
    }
}
