package skku.swcoaching.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import skku.swcoaching.domain.crawling.CrawlingTarget;
import skku.swcoaching.domain.crawling.CrawlingType;
import skku.swcoaching.domain.webtoon.Tag;
import skku.swcoaching.domain.webtoon.Webtoon;
import skku.swcoaching.repository.CrawlingRepository;
import skku.swcoaching.repository.WebtoonRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

// unit test는 DB까지 포함해서 테스트하지 말고 Mock 사용하기
// DB 테스트는 전체 integration test에서 진행하기
@ExtendWith(MockitoExtension.class)
class CrawlingServiceTest {

    // crawling url 은 내부 리소스로 지정할 수도 있음
    
    @Test
    void makeCrawlingTargetTest() {
        //given
        CrawlingRepository crawlingRepository = mock(CrawlingRepository.class);
        WebtoonRepository webtoonRepository = mock(WebtoonRepository.class);

        Webtoon webtoon = createWebtoon();
        LocalDate today = LocalDate.now();
        DayOfWeek todayOfWeek = today.getDayOfWeek();

        //when
        CrawlingService crawlingService = new CrawlingService(crawlingRepository, webtoonRepository);
        Long crawlingId = crawlingService.makeCrawlingTarget(webtoon, "테스트", "localhost", "#thumbnail", todayOfWeek, CrawlingType.IMAGE);

        //then
        CrawlingTarget getCT = crawlingRepository.findOne(crawlingId);

//        assertEquals(CrawlingType.IMAGE, getCT.getType());
//        assertEquals("테스트", getCT.getName());
    }

//    @Test
//    public void postTest() throws Exception {
//        BoardService boardService = mock(BoardService.class);
//
//        when(boardService.findBoards(1))
//                .thenReturn(List.of(new Board()));
//
//        List<Board> boards = boardService.findBoards(1);
//
//        // Mocking
//        // DB 를 직접 가져오지말고 Mocking 해서 가져온 척 해서 처리를 하자
//        // SpringBootTest 는 통합 테스트에 가깝다
//        // UnitTest 에서는 Mock 을 사용해서 test 하자.
//        // 테스트 주도 개발 (구판이 자바)
//
//        for (Board board : boards) {
//            System.out.println(board.getId() + board.getTitle() + board.getAuthor());
//        }
//    }

    private Webtoon createWebtoon(){
        Webtoon webtoon = new Webtoon();
        webtoon.setWriter("작가");
        webtoon.setPlatform("플랫폼");
        webtoon.setTitle("웹툰1");
        webtoon.setThumbnailUrl("localhost/image");
        webtoon.setUrl("localhost");

        LocalDate today = LocalDate.now();
        DayOfWeek todayOfWeek = today.getDayOfWeek();
        webtoon.setUpdateDate(todayOfWeek);

        List<Tag> tags = new ArrayList<>();
        Tag tag1 = new Tag("테스트");
        Tag tag2 = new Tag("테스트");
        tags.add(tag1);
        tags.add(tag2);
        webtoon.setTags(tags);

        return webtoon;
    }
}