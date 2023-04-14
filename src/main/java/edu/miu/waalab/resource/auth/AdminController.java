package edu.miu.waalab.resource.auth;

import edu.miu.waalab.domain.auth.dto.request.LoginRequest;
import edu.miu.waalab.domain.auth.dto.request.RefreshTokenRequest;
import edu.miu.waalab.domain.auth.dto.response.LoginResponse;
import edu.miu.waalab.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/")
    public ResponseEntity<?> adminPageController() {
        return ResponseEntity.ok("ADMIN PAGE ACCESSED");
    }

}
