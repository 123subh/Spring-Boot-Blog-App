package com.blog.blogapp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String Password;
   @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
           @JoinTable(name="user_roles",
                   joinColumns={ @JoinColumn(name = "user_id", referencedColumnName = "id")},
                   inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}
           )
    List<Role> roles = new ArrayList<>();
}
