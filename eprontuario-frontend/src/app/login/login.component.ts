import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { LoginServiceService } from './../services/login-service.service';
import { User } from './../domain/user';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { ResetPasswordComponent } from './reset-password.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent {

  hide: boolean = true;
  loggedUser: User = new User();
  login: any;
  doLogin: any;
  httpCode: string = "";
  httpMessage: string = "";
  errorMsg: string = "";
  password: string = "";
  reEnterPassword: string = "";

  constructor(private fb: FormBuilder,
    private service: LoginServiceService,
    private router: Router,
    private toaster: ToastrService,
    public dialog: MatDialog) {
  }

  ngOnInit() {
    this.loginForm.reset();
    localStorage.clear();
  }

  loginForm: FormGroup = this.fb.group({
    userName: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ],
    password:
      [
        '',
        [
          Validators.required,
          Validators.minLength(12),
          Validators.maxLength(25)
        ]
      ]
  });

  onLogin() {
    if (this.loginForm.valid) {
      this.loggedUser.userName = this.loginForm.controls['userName'].value;
      this.loggedUser.password = this.loginForm.controls['password'].value;

      this.service.signin(this.loggedUser).subscribe(res => {
        localStorage.setItem('tk', (res as any).body.token);
        this.router.navigate(['home']);
      },
        (error: any) => this.getServerErrorMessage(error));
    }
    else {
      this.toaster.warning('Usuario ou senha invalidos.');
    }
  }

  openDialog(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.height = '330px';
    dialogConfig.width = '420px';

    let dialogRef = this.dialog.open(ResetPasswordComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // = result;
    });
  }

  private getServerErrorMessage(error: HttpErrorResponse): void {
    let msg = "";

    switch (error.status) {
      case 400: {
        msg = 'Usuario ou senha invalidos.';
        break;
      }
      case 404: {
        msg = `Not Found: ${error.message}`;
        break;
      }
      case 403: {
        msg = `Access Denied: ${error.message}`;
        break;
      }
      case 500: {
        msg = `Internal Server Error: ${error.message}`;
        break;
      }
      default: {
        msg = `Unknown Server Error: ${error.message}`;
        break;
      }
    }

    this.toaster.error(msg);
  }
}
