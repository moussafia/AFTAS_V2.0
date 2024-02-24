package ma.youcode.cmspringboot.security.util;

import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.security.AuthoritiesConstants;
import ma.youcode.cmspringboot.service.authentication.AppUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class SecurityUtil {
    private static AppUserService appUserService;
    private SecurityUtil(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user.
     */
    public static UserDetails getCurrentUser() {
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        return extractPrincipal(authentication);
    }

    /**
     * Get the login of the current user email.
     *
     * @return the login of the current user email.
     */
    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = extractPrincipal(authentication);
        return userDetails == null ? null : userDetails.getUsername();
    }

    private static UserDetails extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return appUserService.userDetailsService().loadUserByUsername(jwt.getClaim("sub"));
        }
    }

    /**
     * Get the JWT of the current user.
     *
     * @return the JWT of the current user.
     */
    public static String getCurrentUserJWT() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof String jwt) {
            return jwt;
        } else {
            return null;
        }
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().stream()
                .noneMatch(auth -> AuthoritiesConstants.ROLE_ANONYMOUS.equals(auth.getAuthority()));
    }

    /**
     * Checks if the current user has any of the authorities.
     *
     * @param authorities the authorities to check.
     * @return true if the current user has any of the authorities, false otherwise.
     */
    public static boolean hasCurrentUserAnyOfAuthorities(String... authorities) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                    .anyMatch(auth -> Arrays.asList(authorities).contains(auth.getAuthority()));
        }
        return false;
    }

    /**
     * Checks if the current user has none of the authorities.
     *
     * @param authorities the authorities to check.
     * @return true if the current user has none of the authorities, false otherwise.
     */
    public static boolean hasCurrentUserNoneOfAuthorities(String... authorities) {
        return !hasCurrentUserAnyOfAuthorities(authorities);
    }

    /**
     * Checks if the current user has a specific authority.
     *
     * @param authority the authority to check.
     * @return true if the current user has the authority, false otherwise.
     */
    public static boolean hasCurrentUserThisAuthority(String authority) {
        return hasCurrentUserAnyOfAuthorities(authority);
    }
}
