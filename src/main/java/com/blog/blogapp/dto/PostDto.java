package com.blog.blogapp.dto;
import com.blog.blogapp.entity.Comment;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long id;
    @NotEmpty(message="Post title can not be empty")
    private String title;
    private String url;
    @NotEmpty(message="Post content can not be empty")
    private String content;
    @NotEmpty(message="Post shortDescription can not be empty")
    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private Set<CommentDto> comments;
}
