import { Component, OnInit } from '@angular/core';
import { ErrorResponse, SignUpResponse } from '../../model/auth/auth';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent implements OnInit {
  signUpForm?: FormGroup;
    successSignUp?: SignUpResponse;
  constructor(private authService:AuthService,
    private builder: FormBuilder){}
  ngOnInit(): void {
    this.signUpForm = this.builder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required]
    })
  }
signUp() {
if(this.signUpForm?.invalid) return;
this.authService.signUp(this.signUpForm?.value).subscribe({
  next: data=> this.successSignUp = data
})
}

}
