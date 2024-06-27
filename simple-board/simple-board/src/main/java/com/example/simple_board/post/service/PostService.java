package com.example.simple_board.post.service;

import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.post.model.PostReqeust;
import com.example.simple_board.post.model.PostVeiwRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostEntity create(
            PostReqeust postReqeust
    ){
        var entity= PostEntity.builder() // 클라이언트로 부터 받은 데이터를 통해 PostEntity 객체를 만들고
                .board_id(1L) // 게시판 id를 1로 설정
                .user_name(postReqeust.getUserName())
                .password(postReqeust.getPassword())
                .email(postReqeust.getEmail())
                .status("REGISTERED")
                .title(postReqeust.getTitle())
                .content(postReqeust.getContent())
                .posted_at(LocalDateTime.now())
                .build();
        return postRepository.save(entity);
    }

    /*
    * 1. 게시글이 있는가 ?
    * 2. 비밀번호가 맞는가 ?
    * */
    public PostEntity view(PostVeiwRequest postVeiwRequest) {

        return postRepository.findFirstByIdAndStatusOrderByIdDesc(postVeiwRequest.getPostId(), "REGISTERED") //옵셔널
                .map(it->{// Optional의 값이 존재할 때 람다 함수로 값을 변환하여 리턴, 존재하지 않다면 빈 optional을 리턴
                    // entity 존재
                    if(!it.getPassword().equals(postVeiwRequest.getPassword())){ //비밀번호 맞는지
                        var format="패스워드가 맞지 않습니다 %s vs %s";

                        throw new RuntimeException(String.format(format, it.getPassword(), postVeiwRequest.getPassword()));
                    }
                    return it; //해당 엔티티 리턴
                }).orElseThrow(
                        ()->{
                            return new RuntimeException("해당 게시글이 존재하지 않습니다."+postVeiwRequest.getPostId()); //데이터(아이디에 해당하는 엔티티가 없다)
                        }
                );
    }

    public List<PostEntity> all() {
        return postRepository.findAll();
    }

    public void delete(PostVeiwRequest postVeiwRequest) {
         postRepository.findById(postVeiwRequest.getPostId()) //옵셔널
                .map(it->{
                    // entity 존재
                    if(!it.getPassword().equals(postVeiwRequest.getPassword())){ //비밀번호 맞는지
                        var format="패스워드가 맞지 않습니다 %s vs %s";

                        throw new RuntimeException(String.format(format, it.getPassword(), postVeiwRequest.getPassword()));
                    }
                    //비밀번호가 맞는 경우
                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    return it; //해당 엔티티 리턴 Map일 때는 리턴이 필요

                }).orElseThrow(
                        ()->{
                            return new RuntimeException("해당 게시글이 존재하지 않습니다."+postVeiwRequest.getPostId()); //데이터(아이디에 해당하는 엔티티가 없다)
                        }
                );
    }
}
