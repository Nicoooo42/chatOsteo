import { ChatBoxComponent } from './../../../components/chat-box/chat-box.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ChatPageRoutingModule } from './chat-routing.module';

import { ChatPage } from './chat.page';
import { MessageService } from 'src/app/services/api/message.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ChatPageRoutingModule
  ],
  declarations: [ChatPage, ChatBoxComponent],
  providers: [MessageService],
})
export class ChatPageModule {}
