import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ErrorResponceAuth, SignInResponse } from '../../model/auth/auth';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent implements OnInit {
    signInForm?: FormGroup;
    errorSignIn?: ErrorResponceAuth;
    SuccessSignIn?: SignInResponse;
  constructor(private authService:AuthService,private builder: FormBuilder){}
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
      next: data => console.log(data),
      error :err=>console.log(err.error.status)
    });

  }

}
