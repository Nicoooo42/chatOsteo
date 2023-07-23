import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {

  name: string = 'register';

  createSuccess = false;
  registerRoom = { email: '', password: '' };

  constructor(
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
  }

  sendMessage() {}

  register() {}

}
