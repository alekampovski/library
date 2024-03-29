package mk.ukim.finki.emt.library.config.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.library.config.JWTAuthConstants;
import mk.ukim.finki.emt.library.model.User;
import mk.ukim.finki.emt.library.model.dto.UserDetailsDto;
import mk.ukim.finki.emt.library.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.emt.library.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        User credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (credentials == null) throw new UsernameNotFoundException("Invalid credentials!");

        UserDetails userDetails = userService.loadUserByUsername(credentials.getUsername());
        if (!passwordEncoder.matches(credentials.getPassword(),
                userDetails.getPassword())) throw new PasswordsDoNotMatchException();

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(), userDetails.getAuthorities()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {

        User userDetails = (User) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(new ObjectMapper().writeValueAsString(UserDetailsDto.of(userDetails)))
                .withExpiresAt(new Date(System.currentTimeMillis()+ JWTAuthConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(JWTAuthConstants.SECRET_KEY));

        response.addHeader(JWTAuthConstants.HEADER_STRING,JWTAuthConstants.TOKEN_PREFIX+token);
        response.getWriter().append(token);
    }
}
