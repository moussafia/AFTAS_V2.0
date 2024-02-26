import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ErrorResponse, ErrorResponseAuth, SignInResponse } from '../../model/auth/auth';
import { Router } from '@angular/router';
import { RoleEnum } from '../../model/enum/role';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent implements OnInit {
  readonly rolesEnum = RoleEnum
    signInForm?: FormGroup;
    errorSignIn?: ErrorResponse;
    SuccessSignIn?: SignInResponse;
  constructor(private authService:AuthService,
    private builder: FormBuilder,
    private router: Router){}
  ngOnInit(): void {
    this.signInForm = this.builder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    })
  }

  signIn(){
    if(this.signInForm?.invalid)
    return;
    this.authService.signIn(this.signInForm?.value).subscribe({
      next: data => {
        if(data.roles.includes(this.rolesEnum.JURY.toString()) || data.roles.includes(this.rolesEnum.MANAGER.toString())){
          this.router.navigate(['/dashboard']);
        }else if(data.roles.includes(this.rolesEnum.MEMBER.toString())){
          this.router.navigate(['/competitions']);
        }
      },
      error: err=>{
        this.errorSignIn = err
        console.log(err);
      }
    });
  

  }

}
