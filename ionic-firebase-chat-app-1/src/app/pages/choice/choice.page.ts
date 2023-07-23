import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ModalController } from '@ionic/angular';
import { RoomService } from 'src/app/services/api/room.service';


@Component({
  selector: 'app-choice',
  templateUrl: './choice.page.html',
  styleUrls: ['./choice.page.scss'],
})
export class ChoicePage implements OnInit {

  name: string = 'Bienvenue sur chatOsteo';

  open_new_chat = false;

  @ViewChild('new_chat') modal: ModalController;

  date = new Date().toISOString();

  constructor(
    private route: ActivatedRoute,
    private roomService: RoomService
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
    console.log('cre')
    this.roomService.createRoom(this.date)
  }


}
