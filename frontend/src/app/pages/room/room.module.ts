import { UserListComponent } from '../../components/user-list/user-list.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { RoomPageRoutingModule } from './room-routing.module';

import { RoomPage } from './room.page';
import { HttpClientModule } from '@angular/common/http';
import { RoomService } from 'src/app/services/api/room.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RoomPageRoutingModule,
    HttpClientModule
  ],
  declarations: [RoomPage, UserListComponent],
  providers: [RoomService],
})
export class RoomPageModule {}
