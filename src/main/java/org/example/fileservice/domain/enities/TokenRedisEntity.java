package org.example.fileservice.domain.enities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("tokens")
@Getter
@Setter
@AllArgsConstructor
public class TokenRedisEntity {
    @Id
    private String token;
    @TimeToLive
    private Long expiration;
    private String userId;
    private String tokenType;
}