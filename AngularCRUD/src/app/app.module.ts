import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddRoomComponent } from './components/add-room/add-room.component';
import { RoomDetailsComponent } from './components/room-details/room-details.component';
import { RoomsListComponent } from './components/rooms-list/rooms-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AddRoomComponent,
    RoomDetailsComponent,
    RoomsListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
