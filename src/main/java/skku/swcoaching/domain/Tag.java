package skku.swcoaching.domain;

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

    @ManyToMany
    @JoinTable(name = "tag_webtoon",
        joinColumns = @JoinColumn(name = "tag_id"),
        inverseJoinColumns = @JoinColumn(name = "webtoon_id")
    )
    private List<Webtoon> webtoons = new ArrayList<>();

}
