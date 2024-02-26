import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  return checkLogIn(authService, router);
};

function checkLogIn(authService: AuthService, router: Router): boolean {  
  if (authService.isLogIn) {
    return true;
  }
  router.navigate(['/login']);
  return false;
}
