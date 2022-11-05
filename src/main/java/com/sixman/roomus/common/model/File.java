package com.sixman.roomus.common.model;


import lombok.*;

import javax.persistence.*;
import java.net.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "file_type")
@Table(name = "tbl_file")
@SequenceGenerator(
        name = "FILE_SEQUENCE", // 식별자 생성 이름
        sequenceName = "FILE_NO_SEQUENCE", // 데이터 베이스 시퀀스 이름 기본값은 hibernate_sequence 이다
        initialValue = 1, //ddl 생성 시에만 사용되며 ddl을 생성할 때 처음 시작하는 수로 기본 값은 1이다.
        allocationSize = 1  //allocationSize 시퀀스 한 번 호출로 증가하는 수로 기본값은 50이다.
        // catalog, schema 데이터 베이스 catelog, schema 이름
        // 매핑할 ddl
)
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "FILE_SEQUENCE")
    @Column(name = "FILE_NO")
    private Long fileNo;

    @Column(name = "FILE_PATH")
    private URL path;
}

