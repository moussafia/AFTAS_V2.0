import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { RoleEnum } from '../model/enum/role';

export const juryGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const roleEnum = RoleEnum; 
  return checkLogIn(authService, router, roleEnum);
};

function checkLogIn(authService: AuthService, router: Router, roleEnum: typeof RoleEnum): boolean {
  const roles = authService.tokenDecoded()?.roles.split(" ");  
  if (authService.isLogIn && roles && (roles.includes(roleEnum.JURY.toString()) || roles.includes(roleEnum.MANAGER.toString()))) {
    return true;
  }
  router.navigate(['/login']);
  return false;
}