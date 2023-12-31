import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Message } from 'src/app/models/message.model';


import { Observable } from 'rxjs';
import { MessageService } from 'src/app/services/api/message.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.page.html',
  styleUrls: ['./chat.page.scss'],
})
export class ChatPage implements OnInit {

  name: string = 'Chat';
  message: string;
  isLoading = false;
  currentUserId = 1;

  room: number;


  messages$: Observable<Message[]> = this.messageService.messages;

  constructor(
    private route: ActivatedRoute,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.room = this.route.snapshot.params['id'];
    this.messageService.getMessages('' + this.room);
  }

  sendMessage() {
    this.messageService.addMessage({content: this.message, sender: 2, room: this.room})
  }

}
