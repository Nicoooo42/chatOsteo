import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalController } from '@ionic/angular';
import { RoomService } from 'src/app/services/api/room.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {

  name: string = 'Bienvenue sur chatOsteo';

  open_new_chat = false;

  @ViewChild('new_chat') modal: ModalController;

  date = new Date().toISOString();

  constructor(
    private roomService: RoomService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  sendMessage() {}

  newChat() {
    this.open_new_chat = true;
  }

  onWillDismiss(event: any) {}

  cancel() {
    this.modal.dismiss();
    this.open_new_chat = false;
  }

  createRoom() {
    this.roomService.createRoom(this.date.substring(0,16)).subscribe(room =>{
        this.cancel()
        this.router.navigate(['/', 'room', 'chats', room?.id]);
    });
  }

  toRooms() {
    this.router.navigate(['/', 'room']);
  }


}
