import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ChoicePageRoutingModule } from './choice-routing.module';

import { ChoicePage } from './choice.page';
import { HttpClientModule } from '@angular/common/http';
import { RoomService } from 'src/app/services/api/room.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ChoicePageRoutingModule,
    HttpClientModule
  ],
  declarations: [ChoicePage],
  providers: [RoomService],
})
export class ChoicePageModule {}

