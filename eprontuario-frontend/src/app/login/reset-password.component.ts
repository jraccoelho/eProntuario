import { ToastrService } from 'ngx-toastr';
import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

export interface DialogData {
  password: string;
  reEnterPassword: string;
}

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})

export class ResetPasswordComponent {

  hide: boolean = true;
  hideReenter: boolean = true;


  //TODO: adjust the RegEx pattern for passwords. 
  form: FormGroup = this.fb.group(
    {
      password:
        [
          '',
          [
            Validators.required,
            Validators.minLength(12),
            Validators.maxLength(25),
            // Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{12,})')
          ]
        ],
      reEnterPassword:
        [
          '',
          [
            Validators.required,
            Validators.minLength(12),
            Validators.maxLength(25),
            // Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{12,})')
          ]
        ]
    });

  constructor(
    private fb: FormBuilder,
    private toaster: ToastrService,
    private dialogRef: MatDialogRef<ResetPasswordComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) { }

  ngOnInit() {
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
  save(): void {
    if (this.form.controls['password'].value !== this.form.controls['reEnterPassword'].value) {
      this.toaster.error('As senhas nao conferem.');
      this.form.reset();
      return
    }
    ///TODO: finalize the service to save the new password.
    this.toaster.success('Senha alterada com suce sso.');
    this.dialogRef.close();
  }
}
