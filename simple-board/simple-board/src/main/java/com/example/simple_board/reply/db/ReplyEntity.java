package com.example.simple_board.reply.db;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name="reply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long post_id;

    private String user_name;

    private String password;

    private String status;

    @Column(columnDefinition = "TEXT") //속성 맞추기
    private String content;

    private LocalDateTime replied_at;
}
