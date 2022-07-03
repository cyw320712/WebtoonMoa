package skku.swcoaching.domain.webtoon;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Tag {

    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    private String name;

    public Tag(String name){
        this.name = name;
    }

    // tag 비교를 위한 contain 메소드 사용을 위해 equals 메소드 구현
    @Override
    public boolean equals(Object object){
        Tag tag = (Tag) object;

        if (tag.getName().equals(this.name)){
            return true;
        }

        return false;
    }

    @ManyToMany
    @JoinTable(name = "tag_webtoon",
        joinColumns = @JoinColumn(name = "tag_id"),
        inverseJoinColumns = @JoinColumn(name = "webtoon_id")
    )
    private List<Webtoon> webtoons = new ArrayList<>();
}
