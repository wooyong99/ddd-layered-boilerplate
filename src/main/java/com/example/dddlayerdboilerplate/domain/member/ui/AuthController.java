package com.example.dddlayerdboilerplate.domain.member.ui;

import com.example.dddlayerdboilerplate.common.dto.ResponseDto;
import com.example.dddlayerdboilerplate.domain.member.application.service.LoginService;
import com.example.dddlayerdboilerplate.domain.member.application.service.JoinService;
import com.example.dddlayerdboilerplate.domain.member.application.command.JoinServiceCommand;
import com.example.dddlayerdboilerplate.domain.member.application.command.LoginServiceCommand;
import com.example.dddlayerdboilerplate.domain.member.model.aggregate.Member;
import com.example.dddlayerdboilerplate.domain.member.ui.request.JoinRequest;
import com.example.dddlayerdboilerplate.domain.member.ui.request.LoginRequest;
import com.example.dddlayerdboilerplate.domain.member.ui.response.JoinResponse;
import com.example.dddlayerdboilerplate.domain.member.ui.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JoinService joinService;
    private final LoginService loginService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/join")
    public ResponseEntity<ResponseDto<JoinResponse>> join(@RequestBody JoinRequest request){
        // JoinService에 전달하기 위한 Command 객체 생성
        JoinServiceCommand command = new JoinServiceCommand(request.getEmail(), request.getPassword(), request.getDescription(),request.getAddress());

        // JoinService 호출
        Member member = joinService.join(command);
        
        // 응답 객체 생성
        JoinResponse response = new JoinResponse(member.getId());

        // 응답
        return ResponseEntity.ok(ResponseDto.ok(response));
    }
    
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<TokenResponse>> login(@RequestBody LoginRequest request) {
        // LoginService에 전달하기 위한 Command 객체 생성
        LoginServiceCommand command = new LoginServiceCommand(request.getEmail(), request.getPassword());
        
        // LoginService 호출
        String token = loginService.login(command);

        // 응답 객체 생성
        TokenResponse response = new TokenResponse(token);
        // 응답
        return ResponseEntity.ok(ResponseDto.ok(response));
    }
}
