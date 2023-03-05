import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { ResetPasswordComponent } from './reset-password.component';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [
    LoginComponent,
    ResetPasswordComponent
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatDialogModule,
    ReactiveFormsModule,
    ToastrModule.forRoot()
  ]
})
export class LoginModule { }
