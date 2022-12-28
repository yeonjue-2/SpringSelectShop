package com.example.springselectshop.entity;

import com.example.springselectshop.dto.ProductMypriceRequest;
import com.example.springselectshop.dto.ProductRequest;
import com.example.springselectshop.naver.ItemDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity // DB 테이블 역할을 합니다.
@NoArgsConstructor
public class Product extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID가 자동으로 생성 및 증가합니다.
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    @Column(nullable = false)
    private Long userId;

    @ManyToMany
    private List<Folder> folderList = new ArrayList<>();

    public Product(ProductRequest request, Long userId) {
        this.title = request.getTitle();
        this.image = request.getImage();
        this.link = request.getLink();
        this.lprice = request.getLprice();
        this.myprice = 0;
        this.userId = userId;
    }

    public void update(ProductMypriceRequest requestDto) {
        this.myprice = requestDto.getMyprice();
    }

    public void updateByItemDto(ItemDto itemDto) {
        this.lprice = itemDto.getLprice();
    }

    public void addFolder(Folder folder) {
        this.folderList.add(folder);
    }
}
