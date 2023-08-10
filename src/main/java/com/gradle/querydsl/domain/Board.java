package com.gradle.querydsl.domain;

import static javax.persistence.GenerationType.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board {

    @Id
    //native query에서 id로하면 인식하지 못해서 board_id로 column name을 지정
    @Column(name = "board_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "boardName")
    private String boardName;

    @OneToMany
    @JoinTable(name = "board_comments", //조인테이블명
        joinColumns = @JoinColumn(name="board_id"),  //외래키
        inverseJoinColumns = @JoinColumn(name="comments_id") //반대 엔티티의 외래키
    )
    private List<Comments> comments = new ArrayList<>();

    public void addComment(Comments comments) {
        this.comments.add(comments);
    }

}
