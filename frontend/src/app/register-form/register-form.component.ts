import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  message: string;
  hidePassword = true;
  
  regForm = this.fb.group({
    username: ["", [Validators.required]],
    name: ["", [Validators.required]],
    password: ["", [Validators.required]]
  });

  constructor(
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder,) { }

  ngOnInit() {
  }

  get username() {
    return this.regForm.get("username");
  }

  get name() {
    return this.regForm.get("name");
  }

  get password() {
    return this.regForm.get("password");
  }

  async onSubmit() {
    try {
      this.message = null;
      await this.authService.signin(
        this.username.value,
        this.name.value,
        this.password.value
      );
      if (this.authService.redirectUrl) {
        this.router.navigate(["/login"]);
      } else {
        this.router.navigate(["/login"]);
      }
    } catch (e) {
      this.message = "Cannot Register!";
    }
  }

}
