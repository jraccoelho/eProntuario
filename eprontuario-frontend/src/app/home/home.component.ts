import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Util } from '../util/util';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  private util: Util = new Util();

  constructor(
    private router: Router
  ) {
    if (!this.util.validateTk()) {
      this.router.navigate(['login']);
    }
   }

  ngOnInit() {
  }

}
