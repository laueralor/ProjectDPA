import { Component, OnInit } from '@angular/core';
import { Room } from 'src/app/models/room.model';
import { RoomService } from 'src/app/services/room.service';

@Component({
    selector: 'app-rooms-list',
    templateUrl: './rooms-list.component.html',
    styleUrls: ['./rooms-list.component.css']
})
export class RoomsListComponent implements OnInit {
    rooms?: Room[];
    currentRoom: Room = {};
    currentIndex = -1;
    name = '';

    constructor(private roomService: RoomService) { }

    ngOnInit(): void {
    this.retrieveRooms();
    }

    retrieveRooms(): void {
        this.roomService.getAll()
        .subscribe({
            next: (data) => {
                this.rooms = data;
                console.log(data);
            },
            error: (e) => console.error(e)
        });
    }

    refreshList(): void {
        this.retrieveRooms();
        this.currentRoom = {};
        this.currentIndex = -1;
    }

    setActiveRoom(room: Room, index: number): void {
        this.currentRoom = room;
        this.currentIndex = index;
    }

    removeAllRooms(): void {
        this.roomService.deleteAll()
        .subscribe({
        next: (res) => {
            console.log(res);
            this.refreshList();
        },
        error: (e) => console.error(e)
        });
    }

    searchByName(): void {
        this.currentRoom = {};
        this.currentIndex = -1;

        this.roomService.findByName(this.name)
        .subscribe({
            next: (data) => {
                this.rooms = data;
                console.log(data);
            },
            error: (e) => console.error(e)
        });
    }
}