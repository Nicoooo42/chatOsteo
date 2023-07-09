import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { ChatBoxComponent } from 'src/app/components/chat-box/chat-box.component';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.page.html',
  styleUrls: ['./chat.page.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule, ChatBoxComponent]
})
export class ChatPage implements OnInit {

  name: string = 'Sender';
  message: string | undefined;
  isLoading = false;

  currentUserId = 1;

  chats = [
    {id:1, sender: 1, message: 'hi'},
    {id:2, sender: 2, message: 'hi there!'}
  ]

  constructor() { }

  ngOnInit() {
  }

  sendMessage(){}

}
