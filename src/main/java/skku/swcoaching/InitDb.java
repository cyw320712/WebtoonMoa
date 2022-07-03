package skku.swcoaching;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import skku.swcoaching.domain.webtoon.Tag;
import skku.swcoaching.domain.webtoon.Webtoon;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.webtoonInit();
    }

    @Component
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        @Transactional
        public void webtoonInit(){
            Tag tag1 = new Tag("판타지");
            Tag tag2 = new Tag("로맨스");
            Tag tag3 = new Tag("무협");
            Tag tag4 = new Tag("일상");
            Tag tag5 = new Tag("액션");
            Tag tag6 = new Tag("드라마");
            em.persist(tag1);
            em.persist(tag2);
            em.persist(tag3);
            em.persist(tag4);
            em.persist(tag5);
            em.persist(tag6);

            Webtoon webtoon = new Webtoon();
            webtoon.setTitle("독립일기");
            webtoon.setPlatform("네이버");
            webtoon.setWriter("자까");
            webtoon.setThumbnailUrl("https://shared-comic.pstatic.net/thumb/webtoon/748105/thumbnail/thumbnail_IMAG10_becd3e24-cc09-4243-a1c9-646270f4a8db.jpg");
            webtoon.setUpdateDate(DayOfWeek.SUNDAY);
            webtoon.setUrl("https://comic.naver.com/webtoon/list?titleId=748105&weekday=sun");

            // 연관관계 매핑
            List<Tag> tagList1 = new ArrayList<>();
            tagList1.add(tag4);
            tag4.getWebtoons().add(webtoon);

            webtoon.setTags(tagList1);
            em.persist(webtoon);

            Webtoon webtoon1 = new Webtoon();
            webtoon1.setTitle("판사 이한영");
            webtoon1.setPlatform("네이버");
            webtoon1.setWriter("문성호");
            webtoon1.setThumbnailUrl("https://shared-comic.pstatic.net/thumb/webtoon/759567/thumbnail/thumbnail_IMAG10_6cee56e9-d777-4c30-9500-503d6b7a879d.jpg");
            webtoon1.setUpdateDate(DayOfWeek.SUNDAY);
            webtoon1.setUrl("https://comic.naver.com/webtoon/list?titleId=759567&weekday=sun");

            // 연관관계 매핑
            List<Tag> tagList2 = new ArrayList<>();
            tagList2.add(tag1);
            tagList2.add(tag5);
            tag1.getWebtoons().add(webtoon1);
            tag5.getWebtoons().add(webtoon1);

            webtoon.setTags(tagList2);
            em.persist(webtoon1);
        }
    }
}
