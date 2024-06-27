package com.example.simple_board.post.db;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name ="post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long board_id;

    private String user_name;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT") //속성 맞추기
    private String content;

    private LocalDateTime posted_at;
}
