import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {
  username?:string
  constructor(private authService:AuthService,
    private router: Router){}
  ngOnInit(): void {
    this.username = this.authService.tokenDecoded()?.sub
  }
  logout() {
this.authService.logOut();
this.router.navigate(['/login']);    
    }

}
