import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalController, PopoverController } from '@ionic/angular';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import OktaAuth from '@okta/okta-auth-js';
import { Observable } from 'rxjs';
import { Room } from 'src/app/models/room.model';
import { RoomService } from 'src/app/services/api/room.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-room',
  templateUrl: './room.page.html',
  styleUrls: ['./room.page.scss'],
})
export class RoomPage implements OnInit {

  @ViewChild('new_chat') modal: ModalController;
  @ViewChild('popover') popover: PopoverController;
  segment = 'chats';
  open_new_chat = false;

  chatRooms$: Observable<Room[]> = this.roomService.rooms;


  constructor(
    private router: Router,
    private roomService: RoomService,
    private _oktaStateService: OktaAuthStateService, @Inject(OKTA_AUTH) private _oktaAuth: OktaAuth
  ) { }

  ngOnInit() {
    this.roomService.getRooms();
  }

  async logout() {
    await this._oktaAuth.signOut();
  }

  onSegmentChanged(event: any) {
    console.log(event);
  }

  newChat() {
    this.open_new_chat = true;
  }

  onWillDismiss(event: any) {}

  cancel() {
    this.modal.dismiss();
    this.open_new_chat = false;
  }

  startChat(item) {

  }

  getChat(item) {
    this.router.navigate(['/', 'room', 'chats', item?.id]);
  }

}
