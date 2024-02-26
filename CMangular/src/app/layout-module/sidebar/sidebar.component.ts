import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { RoleEnum } from '../../model/enum/role';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  readonly roleEnum = RoleEnum
  constructor(public authService: AuthService){}
  managerOrJury():boolean | undefined{
    return (this.authService.tokenDecoded()?.roles?.includes(this.roleEnum.MANAGER.toString())
     || this.authService.tokenDecoded()?.roles?.includes(this.roleEnum.JURY.toString()))
  }
  Manger():boolean | undefined{
    return this.authService.tokenDecoded()?.roles?.includes(this.roleEnum.MANAGER.toString())
  }
  Member():boolean | undefined{
    return (
      this.authService.tokenDecoded()?.roles?.includes(this.roleEnum.MEMBER.toString()) ||
      this.authService.tokenDecoded()?.roles?.includes(this.roleEnum.JURY.toString()) || 
      this.authService.tokenDecoded()?.roles?.includes(this.roleEnum.MANAGER.toString())
      )
  }

}
