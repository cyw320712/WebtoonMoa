package skku.swcoaching;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import skku.swcoaching.domain.webtoon.Webtoon;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.DayOfWeek;

@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1(){
            Webtoon webtoon = new Webtoon();
            webtoon.setTitle("독립일기");
            webtoon.setPlatform("네이버");
            webtoon.setWriter("자까");
            webtoon.setThumbnailUrl("https://shared-comic.pstatic.net/thumb/webtoon/748105/thumbnail/thumbnail_IMAG10_becd3e24-cc09-4243-a1c9-646270f4a8db.jpg");
            webtoon.setUpdateDate(DayOfWeek.SUNDAY);
            webtoon.setUrl("https://comic.naver.com/webtoon/list?titleId=748105&weekday=sun");
            em.persist(webtoon);
        }

        public void dbInit2(){
            Webtoon webtoon = new Webtoon();
            webtoon.setTitle("판사 이한영");
            webtoon.setPlatform("네이버");
            webtoon.setWriter("문성호");
            webtoon.setThumbnailUrl("https://shared-comic.pstatic.net/thumb/webtoon/759567/thumbnail/thumbnail_IMAG10_6cee56e9-d777-4c30-9500-503d6b7a879d.jpg");
            webtoon.setUpdateDate(DayOfWeek.SUNDAY);
            webtoon.setUrl("https://comic.naver.com/webtoon/list?titleId=759567&weekday=sun");
            em.persist(webtoon);
        }
    }
}
