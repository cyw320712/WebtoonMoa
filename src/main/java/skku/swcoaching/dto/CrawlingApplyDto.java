package skku.swcoaching.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import skku.swcoaching.domain.crawling.CrawlingTarget;
import skku.swcoaching.domain.webtoon.Tag;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@NoArgsConstructor
public class CrawlingApplyDto {
    private String title;
    private String writer;
    private String thumbnailUrl;
    private int updateDate;
    private String platform;
    private String url;

    private List<Tag> tags;
    private List<CrawlingTarget> crawlingTargets;

    public CrawlingApplyDto(String title, String writer, String thumbnailUrl, int updateDate, String platform, String url, List<Tag> tags, List<CrawlingTarget> crawlingTargets) {
        this.title = title;
        this.writer = writer;
        this.thumbnailUrl = thumbnailUrl;
        this.updateDate = updateDate;
        this.platform = platform;
        this.url = url;
        this.tags = tags;
        this.crawlingTargets = crawlingTargets;
    }
}
