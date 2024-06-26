package io.github.singhalmradul.followservice.configuration;

import static org.springframework.web.servlet.function.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.followservice.handlers.UserFollowHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor_ = @Autowired)
@Configuration
public class RouterConfiguration {

    private final UserFollowHandler handler;

    @Bean
    RouterFunction<ServerResponse> userFollowRouter() {
        return (
            route()
            .path("/users/{userId}",builder -> builder
                .GET("/followers", handler::getFollowers)
                .GET("/following", handler::getFollowing)
                .GET("/follow/{followId}", handler::isFollowing)
                .POST("/follow/{followId}", handler::followUser)
                .DELETE("/follow/{followId}", handler::unfollowUser)
            )
            .build()
        );
    }
}
