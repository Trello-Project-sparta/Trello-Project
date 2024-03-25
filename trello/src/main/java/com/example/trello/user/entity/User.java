package com.example.trello.user.entity;

import com.example.trello.user.dto.ProfileRequestDto;
import com.example.trello.user.dto.SignupRequestDto;
import com.example.trello.userBoard.entity.UserBoard;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userInfo = "자기소개 해주세요";

    @Column(nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserBoard> userBoards = new HashSet<>();

    public User(SignupRequestDto request, String password) {
        this.username = request.getUsername();
        this.email = request.getEmail();
        this.password = password;
    }
    public User(Long userId, SignupRequestDto request) {
        this.userId = userId;
        this.username = request.getUsername();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }

    public User(SignupRequestDto request) {
        this.username = request.getUsername();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }

    public void updateProfile(ProfileRequestDto request) {
        this.username = request.getUsername();
        this.email = request.getEmail();
        this.userInfo = request.getUserInfo();
    }

    public void inActiveUser() {
        this.active = false;
    }
}
