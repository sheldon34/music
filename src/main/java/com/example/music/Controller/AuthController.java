package com.example.music.Controller;
import com.example.music.Security.AuthOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt; // IMPORT THIS for Resource Server
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthOService userService;

    // REMOVED: @GetMapping("/")
    // Why? Your Frontend handles the "Home" page. The backend only provides data.

//    // OPTIONAL: If you need an endpoint to see "Who am I?" based on the token
//    @GetMapping("/me")
//    public ResponseEntity<Map<String, Object>> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
//        return ResponseEntity.ok(jwt.getClaims());
//    }
@PreAuthorize("hasRole('ADMIN')") // This works perfectly with the Converter we wrote earlier
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully: " + id);
    }
    @PreAuthorize("hasRole('ADMIN')") // This works perfectly with the Converter we wrote earlier

    @PatchMapping("/users/{id}") // Fixed typo: changed "user" to "users" to match DELETE
    public ResponseEntity<Map<String, Object>> updateUser(
            @PathVariable String id,
            @RequestBody
            Map<String, Object> updates) {
        Map<String, Object> updatedUser = userService.updateUser(id, updates);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')") // This works perfectly with the Converter we wrote earlier
    @GetMapping("/users/search") // Changed URL to be more RESTful
    public ResponseEntity<Map<String, Object>> getUserByEmail(@RequestParam String email) {
        Map<String, Object> user = userService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
}
